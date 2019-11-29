package ec.com.cryptogateway.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.ResponseVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import cryptogateway.vo.response.TransactionVO;
import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.blockchain.service.IEthereumService;
import ec.com.cryptogateway.entity.StoreEntity;
import ec.com.cryptogateway.entity.TransactionEntity;
import ec.com.cryptogateway.entity.TransactionStatusEntity;
import ec.com.cryptogateway.entity.WalletsEntity;
import ec.com.cryptogateway.repository.ICryptoCurrencyRepository;
import ec.com.cryptogateway.repository.IStoreRepository;
import ec.com.cryptogateway.repository.ITransactionRepository;
import ec.com.cryptogateway.repository.ITransactionStatusRepository;
import ec.com.cryptogateway.repository.IWalletsRepository;
import ec.com.cryptogateway.utils.CryptoGatewayConstants;
import ec.cryptogateway.utils.CoreUtils;
import ec.cryptogateway.utils.WalletUtil;

/**
 * 
 * @author Christian
 *
 */
@Service
@Transactional
public class TransactionService implements ITransactionService{
	
	@Autowired
	IStoreService storeService;
	
	@Autowired
	IEthereumService ethereumService;
	
	@Autowired
	ITransactionRepository transactionRepository;
	
	@Autowired
	IWalletsRepository walletsRepository;
	
	@Autowired
	IStoreRepository storeRepository;
	
	@Autowired
	ICryptoCurrencyRepository cryptoCurrencyRepository;
	
	@Autowired
	ITransactionStatusRepository transactionStatusRepository;

	/**
	 * 
	 */
	@Override
	public TransactionVO createTransactionT(StoreQueryVO storeQueryVO) {	
		
		TransactionVO transactionVO = new TransactionVO();
		
		 List<StoreCryptoCurrenciesVO> coinInfo=  storeService.findCoinsForUIstore(storeQueryVO);
		 if(!CollectionUtils.isEmpty(coinInfo) && coinInfo.size()==1) {
			 
			 StoreCryptoCurrenciesVO dataTransaction = coinInfo.get(0);
			 
			 StoreEntity storeEntity = storeRepository.findById(dataTransaction.getIdStore());
			 
			 if(storeEntity!=null) {
				 
				 Optional <TransactionStatusEntity> transactionStatus= transactionStatusRepository.
						 findById(CryptoGatewayConstants.STATUS_TRANSACTION_WAITING);
				 
				 WalletVO walletVO = WalletUtil.generateWallet(dataTransaction);			 
				 
				 WalletsEntity walletEntity = new WalletsEntity();
				 walletEntity.setPrivateKey(walletVO.getPrivateKey());
				 walletEntity.setWallet(walletVO.getWalletAddress());
				 walletEntity.setStoreId(storeEntity.getId());
				 walletEntity.setBlockchainId(dataTransaction.getBlockchainId());
				 walletEntity.setPublicKey(walletVO.getPublicKey());
				 walletsRepository.save(walletEntity);
				 
				 TransactionEntity transactionEntity = new TransactionEntity();
				 String transactionID = CoreUtils.createIdentifierRandom(23).toUpperCase();
				 transactionEntity.setWalletId(walletEntity.getId());
				 transactionEntity.setTransactionId(transactionID);
				 transactionEntity.setStoreId(storeEntity.getId());
				 transactionEntity.setCryptoCurrencyId(dataTransaction.getIdCoin());
				 
				 if(transactionStatus.isPresent()) {
					 transactionEntity.setTransactionStatusId(transactionStatus.get().getId());
				 }						 
				 
				 transactionEntity.setBlockchainId(dataTransaction.getBlockchainId());
				 transactionEntity.setCoinPrice(dataTransaction.getCryptoCurrencyPrice());
				 transactionEntity.setCoinsAmount(dataTransaction.getCryptoCurrencyConversion());				 
				 transactionEntity.setCreationTime(new Date());		
				 transactionEntity.setTimeoutTransaction(CoreUtils.addTimeToDate(dataTransaction.getTimeoutMinuts(), 
						 transactionEntity.getCreationTime()));
				 transactionEntity.setTotalPayment(dataTransaction.getTotalPayment());
				 		 
				 transactionRepository.save(transactionEntity);		 
				 
				 transactionVO.setCoinId(dataTransaction.getCoinId());
				 transactionVO.setCoinLogo(dataTransaction.getCryptoCurrencyLogo());
				 transactionVO.setCoinName(dataTransaction.getCryptoCurrencyName());
				 transactionVO.setCoinsAmount(dataTransaction.getCryptoCurrencyConversion());
				 transactionVO.setWalletAddress(walletVO.getWalletAddress());
				 transactionVO.setId(transactionEntity.getId());
				 transactionVO.setCreationTime(transactionEntity.getCreationTime());
				 transactionVO.setTransactionId(transactionID);
				 
			 }
		 }
		 
		 return transactionVO;		
	}	
	

	@Override
	public ResponseVO createTransaction(StoreQueryVO storeQueryVO) {
		TransactionVO transactionVO = createTransactionT(storeQueryVO);
		return new ResponseVO(CryptoGatewayConstants.STATUS_SUCCESSFULL, null, transactionVO);
	}
	

	@Override
	public Collection<TransactionVO> showHistory(StoreQueryVO storeQueryVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
