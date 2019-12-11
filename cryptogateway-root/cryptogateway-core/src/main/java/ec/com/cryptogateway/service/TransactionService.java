package ec.com.cryptogateway.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.request.TransactionCheckVO;
import cryptogateway.vo.request.TransactionsVO;
import cryptogateway.vo.response.ResponseVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import cryptogateway.vo.response.TransactionVO;
import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.entity.StoreEntity;
import ec.com.cryptogateway.entity.TransactionEntity;
import ec.com.cryptogateway.entity.TransactionStatusEntity;
import ec.com.cryptogateway.entity.WalletsEntity;
import ec.com.cryptogateway.repository.IBlockchainRepository;
import ec.com.cryptogateway.repository.ICryptoCurrencyRepository;
import ec.com.cryptogateway.repository.IStoreRepository;
import ec.com.cryptogateway.repository.ITransactionRepository;
import ec.com.cryptogateway.repository.ITransactionStatusRepository;
import ec.com.cryptogateway.repository.IWalletsRepository;
import ec.com.cryptogateway.utils.CryptoGatewayConstants;
import ec.cryptogateway.utils.CoreUtils;
import ec.cryptogateway.utils.WalletUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Christian
 *
 */
@Slf4j
@Service
@Transactional
public class TransactionService implements ITransactionService{
	
	@Autowired
	IStoreService storeService;
	
	@Autowired
	ITransactionRepository transactionRepository;
	
	@Autowired
	IWalletsRepository walletsRepository;
	
	@Autowired
	IStoreRepository storeRepository;
	
	@Autowired
	IBlockchainRepository blockchainRepository;
	
	@Autowired
	ICryptoCurrencyRepository cryptoCurrencyRepository;
	
	@Autowired
	ITransactionStatusRepository transactionStatusRepository;
	
	static String classPackage = "ec.com.cryptogateway.blockchain.service.";

	/**
	 * 
	 */
	@Override
	public ResponseVO createTransaction(StoreQueryVO storeQueryVO) {	
		
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
				 
			 }else {
				 return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_ERROR_STORE_NOT_FOUND);				 
			 }
		 }else {
			 
			 return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_STORE_COINS_CONFIGURATION);
		 }
		 
		 return new ResponseVO(CryptoGatewayConstants.STATUS_SUCCESSFULL, null, transactionVO);
	}	
	
	/**
	 * Show history
	 */
	@Override
	public Collection<TransactionVO> showHistory(StoreQueryVO storeQueryVO) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Check transaction
	 */
	@Override
	public void checkTransaction() {
		
		Collection<TransactionCheckVO> blockchains = blockchainRepository.findAllBlockchain();
		Collection<TransactionsVO>  transactionsVO = transactionRepository.findAllTransactions();
		
		Collection<TransactionCheckVO>  transactionsDeleteVO = new ArrayList<>();
		
		blockchains.forEach(data->{
			
			Collection<TransactionsVO>  transactionVO = 
					transactionsVO.stream()
					.filter(transact->transact.getBlockchainId().equals(data.getBlockchainId()))
					.collect(Collectors.toCollection(ArrayList::new));
			
			if(!CollectionUtils.isEmpty(transactionVO)) {
				data.setTransactionsVO(transactionVO);				
			}else {
				transactionsDeleteVO.add(data);				
			}	
		});
		
		if(!CollectionUtils.isEmpty(transactionsDeleteVO)) {
			blockchains.removeAll(transactionsDeleteVO);			
		}		
		
		blockchains.forEach(data->{
			try {
				Class<?> clase = Class.forName(classPackage.concat(data.getJavaClass()));
				Constructor<?> cons1 = clase.getConstructor();
				Method method = clase.getDeclaredMethod("checkTransaction",Collection.class);
				method.setAccessible(true);
				method.invoke(cons1.newInstance(),data.getTransactionsVO());
			}catch (Exception e) {
				String error= "Error al chequear las transacciones de ".concat(data.getJavaClass()).concat(" {}");
				log.error(error, e.getMessage());
			}			
		});
		
	}

	/**
	 * Update Transactions
	 */
	@Override
	public void updateTransaction(TransactionsVO transactionsVO) {
		
		Date actualDate = new Date();
		TransactionEntity transaction = new TransactionEntity();
		
	   transaction.setCoinsReceived(transactionsVO.getWalletBalance());
       transaction.setId(transactionsVO.getTransactionId());
       transaction.setLastCheckDate(actualDate);
       transaction.setNumberOfChecks(transactionsVO.getNumberOfChecks()+1);
		
       
        if(transactionsVO.getWalletBalance().compareTo(BigDecimal.ZERO)==0 && 
                (transactionsVO.getTimeoutTransaction().compareTo(actualDate)==0 
                || transactionsVO.getTimeoutTransaction().compareTo(actualDate)==1)) {            
        	updateTransactionStatus(transactionsVO,transaction,CryptoGatewayConstants.STATUS_TRANSACTION_TIMEOUT);
        }
        
        else if(transactionsVO.getWalletBalance().compareTo(BigDecimal.ZERO)==0 && 
                (transactionsVO.getTimeoutTransaction().compareTo(actualDate)==-1)) {    
        	//STATUS_TRANSACTION_WAITING
        	transactionRepository.update(transaction);
         } 
        
        else if(transactionsVO.getWalletBalance().compareTo(BigDecimal.ZERO)==1 && 
        		transactionsVO.getWalletBalance().compareTo(transactionsVO.getCoinsAmount())==-1) {   
        	updateTransactionStatus(transactionsVO,transaction,CryptoGatewayConstants.STATUS_TRANSACTION_INCOMPLETE);

        }
        

        else if(transactionsVO.getWalletBalance().compareTo(transactionsVO.getCoinsAmount())==0 
        		|| transactionsVO.getWalletBalance().compareTo(transactionsVO.getCoinsAmount())==1){            
        	transaction.setEndTransaction(actualDate);
        	updateTransactionStatus(transactionsVO,transaction,CryptoGatewayConstants.STATUS_TRANSACTION_SUCCESSFULL);
       }       
	}
	
	/**
	 * Update Transaction Status
	 * 
	 * @param transactionsVO
	 * @param transaction
	 * @param status
	 */
	private void updateTransactionStatus(TransactionsVO transactionsVO,TransactionEntity transaction,Integer status) {		
		transaction.setTransactionStatusId(status);
		transactionRepository.update(transaction);
		sendEmailTransaction(transactionsVO,transaction);
	}
	
	/**
	 * 
	 * @param transactionsVO
	 * @param transaction
	 */
	private void sendEmailTransaction(TransactionsVO transactionsVO,TransactionEntity transaction) {
		
		log.debug("Envio email {}",transaction.getTransactionStatusId());
		
	}

}
