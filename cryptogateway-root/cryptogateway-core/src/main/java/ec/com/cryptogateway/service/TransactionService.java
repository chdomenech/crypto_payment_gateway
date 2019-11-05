package ec.com.cryptogateway.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import cryptogateway.vo.response.TransactionVO;
import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.blockchain.service.IEthereumService;
import ec.com.cryptogateway.entity.CryptoCurrencyEntity;
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

/**
 * Transaction Service
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

	@Override
	public TransactionVO saveTransaction(StoreQueryVO storeQueryVO) {	
		
		TransactionVO transactionVO = new TransactionVO();
		
		 List<StoreCryptoCurrenciesVO> coinInfo=  storeService.findCrytptoCurrenciesForUIstore(storeQueryVO);
		 if(!CollectionUtils.isEmpty(coinInfo) && coinInfo.size()==1) {
			 StoreCryptoCurrenciesVO dataTransaction = coinInfo.get(0);
			 
			 Optional <StoreEntity> storeEntity = storeRepository.findById(dataTransaction.getIdStore());
			 if(storeEntity.isPresent()) {
				 
				 Optional<CryptoCurrencyEntity> cryptoCurrency = cryptoCurrencyRepository.findById(dataTransaction.getIdCoin());
				 
				 Optional <TransactionStatusEntity> transactionStatus= transactionStatusRepository.
						 findById(CryptoGatewayConstants.STATUS_TRANSACTION_WAITING);
				 
				 WalletVO walletVO = generateWallet(dataTransaction);			 
				 String qrCode = generateQRCode(walletVO.getWalletAddress());
				 
				 WalletsEntity walletEntity = new WalletsEntity();
				 walletEntity.setPrivateKey(walletVO.getPrivateKey());
				 walletEntity.setWallet(walletVO.getWalletAddress());
				 walletEntity.setStoreId(storeEntity.get().getId());
				 //walletEntity.setStore(storeEntity.get());
				 walletEntity.setBlockchain(dataTransaction.getBlockchain());
				 walletsRepository.save(walletEntity);
				 
				 TransactionEntity transactionEntity = new TransactionEntity();
				 //transactionEntity.setWallet(walletEntity);
				 transactionEntity.setWalletId(walletEntity.getId());
				 //transactionEntity.setStore(storeEntity.get());	
				 transactionEntity.setStoreId(storeEntity.get().getId());
				 if(cryptoCurrency.isPresent()) {
					 //transactionEntity.setCryptoCurrency(cryptoCurrency.get());	
					 transactionEntity.setCryptoCurrencyId(cryptoCurrency.get().getId());
				 }
				 if(transactionStatus.isPresent()) {
					 transactionEntity.setTransactionStatusId(transactionStatus.get().getId());
					 //transactionEntity.setTransactionStatus(transactionStatus.get());
				 }						 
				 
				 transactionEntity.setQrCode(qrCode);
				 transactionEntity.setBlockchain(dataTransaction.getBlockchain());
				 transactionEntity.setCoinPrice(dataTransaction.getCryptoCurrencyPrice());
				 transactionEntity.setCoinsAmount(dataTransaction.getCryptoCurrencyConversion());
				 transactionEntity.setTotalPayment(dataTransaction.getTotalPayment());
				 transactionEntity.setCreationTime(new Date());				 
				 transactionRepository.save(transactionEntity);		 
				 
				 transactionVO.setCoinId(dataTransaction.getCoinId());
				 transactionVO.setCoinLogo(dataTransaction.getCryptoCurrencyLogo());
				 transactionVO.setCoinName(dataTransaction.getCryptoCurrencyName());
				 transactionVO.setCoinsAmount(dataTransaction.getCryptoCurrencyConversion());
				 transactionVO.setQrCode(qrCode);
				 transactionVO.setWalletAddress(walletVO.getWalletAddress());
				 transactionVO.setId(transactionEntity.getId());
				 transactionVO.setCreationTime(transactionEntity.getCreationTime());
				 transactionVO.setTransactionId(createTransactionID(transactionEntity.getCreationTime(), walletVO.getWalletAddress()));
				 
			 }
		 }
		 
		 return transactionVO;		
	}
	
	/**
	 * Transaction creation
	 * 
	 * @param creationTime
	 * @param walletAddres
	 * @return
	 */
	private String createTransactionID(Date creationTime, String walletAddres) {
		
		return "";
	}

	/**
	 * Generate qrCode
	 * 
	 * @param walletAddress
	 * @return
	 */
	private String generateQRCode(String walletAddress) {
		// TODO Auto-generated method stub
		return "ADADADADA";
	}

	/**
	 * Generate a Wallet with private key
	 * it depend of type blockchain 
	 * 
	 * @param dataTransaction
	 * @return
	 */
	private WalletVO generateWallet(StoreCryptoCurrenciesVO dataTransaction) {
		/*WalletVO walletVO = null;
		
		if(dataTransaction.getBlockchain().equals(CryptoGatewayConstants.ETHEREUM_BLOCKCHAIN)){
			 walletVO = ethereumService.generateWalletEthereum();		 
		 }*/
		
		WalletVO walletVO = new WalletVO();
		walletVO.setBlockchain(CryptoGatewayConstants.ETHEREUM_BLOCKCHAIN);
		walletVO.setPrivateKey("ABC");
		walletVO.setWalletAddress("0x123");
		
		return walletVO;
	}


}
