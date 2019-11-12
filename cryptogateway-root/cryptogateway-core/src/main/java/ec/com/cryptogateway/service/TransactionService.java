package ec.com.cryptogateway.service;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
	
	private static char[] VALID_CHARACTERS =
		    "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456879".toCharArray();

	/**
	 * 
	 * @param numChars
	 * @return
	 */
	public static String createTransactionID(int numChars) {
	    SecureRandom srand = new SecureRandom();
	    Random rand = new Random();
	    char[] buff = new char[numChars];

	    for (int i = 0; i < numChars; ++i) {
	      if ((i % 10) == 0) {
	          rand.setSeed(srand.nextLong());
	      }
	      buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
	    }
	    return new String(buff);
	}

	@Override
	public TransactionVO createTransaction(StoreQueryVO storeQueryVO) {	
		
		TransactionVO transactionVO = new TransactionVO();
		
		 List<StoreCryptoCurrenciesVO> coinInfo=  storeService.findCoinsForUIstore(storeQueryVO);
		 if(!CollectionUtils.isEmpty(coinInfo) && coinInfo.size()==1) {
			 StoreCryptoCurrenciesVO dataTransaction = coinInfo.get(0);
			 
			 StoreEntity storeEntity = storeRepository.findById(dataTransaction.getIdStore());
			 if(storeEntity!=null) {
				 
				 Optional<CryptoCurrencyEntity> cryptoCurrency = cryptoCurrencyRepository.findById(dataTransaction.getIdCoin());
				 
				 Optional <TransactionStatusEntity> transactionStatus= transactionStatusRepository.
						 findById(CryptoGatewayConstants.STATUS_TRANSACTION_WAITING);
				 
				 WalletVO walletVO = generateWallet(dataTransaction);			 
				 
				 WalletsEntity walletEntity = new WalletsEntity();
				 walletEntity.setPrivateKey(walletVO.getPrivateKey());
				 walletEntity.setWallet(walletVO.getWalletAddress());
				 walletEntity.setStoreId(storeEntity.getId());
				 walletEntity.setBlockchain(dataTransaction.getBlockchain());
				 walletEntity.setPublicKey(walletVO.getPublicKey());
				 walletsRepository.save(walletEntity);
				 
				 TransactionEntity transactionEntity = new TransactionEntity();
				 String transactionID = createTransactionID(23).toUpperCase();
				 transactionEntity.setWalletId(walletEntity.getId());
				 transactionEntity.setTransactionId(transactionID);
				 transactionEntity.setStoreId(storeEntity.getId());
				 if(cryptoCurrency.isPresent()) {	
					 transactionEntity.setCryptoCurrencyId(cryptoCurrency.get().getId());
				 }
				 if(transactionStatus.isPresent()) {
					 transactionEntity.setTransactionStatusId(transactionStatus.get().getId());
				 }						 
				 
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
				 transactionVO.setWalletAddress(walletVO.getWalletAddress());
				 transactionVO.setId(transactionEntity.getId());
				 transactionVO.setCreationTime(transactionEntity.getCreationTime());
				 transactionVO.setTransactionId(transactionID);
				 
			 }
		 }
		 
		 return transactionVO;		
	}


	/**
	 * Generate a Wallet with private key
	 * it depend of type blockchain 
	 * 
	 * @param dataTransaction
	 * @return
	 */
	private WalletVO generateWallet(StoreCryptoCurrenciesVO dataTransaction) {
		WalletVO walletVO = null;
		
		if(dataTransaction.getBlockchain().equals(CryptoGatewayConstants.ETHEREUM_BLOCKCHAIN)){
			 walletVO = ethereumService.createWallet();		 
		 }
		
		return walletVO;		
	}


	@Override
	public Collection<TransactionVO> showHistory(StoreQueryVO storeQueryVO) {
		// TODO Auto-generated method stub
		return null;
	}


}
