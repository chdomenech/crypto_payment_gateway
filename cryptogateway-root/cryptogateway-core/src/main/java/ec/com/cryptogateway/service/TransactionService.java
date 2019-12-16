package ec.com.cryptogateway.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import cryptogateway.vo.request.MailVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.request.TransactionATMVO;
import cryptogateway.vo.request.TransactionCheckVO;
import cryptogateway.vo.request.TransactionsVO;
import cryptogateway.vo.request.WalletCredentialsVO;
import cryptogateway.vo.response.CryptoCurrencyVO;
import cryptogateway.vo.response.ResponseVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import cryptogateway.vo.response.TransactionVO;
import cryptogateway.vo.response.WalletBalance;
import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.entity.StoreEntity;
import ec.com.cryptogateway.entity.TransactionEntity;
import ec.com.cryptogateway.entity.TransactionStatusEntity;
import ec.com.cryptogateway.entity.WalletsEntity;
import ec.com.cryptogateway.repository.IBlockchainRepository;
import ec.com.cryptogateway.repository.ICryptoCurrencyRepository;
import ec.com.cryptogateway.repository.ICryptoCurrencyStoreRepository;
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
 * @author Christian Domenech
 *
 */
@Slf4j
@Transactional
@Service
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
	ICryptoCurrencyStoreRepository cryptoCurrencyStoreRepository;
	
	@Autowired
	ITransactionStatusRepository transactionStatusRepository;
	
	@Autowired
	IEmailService emailService;
	
	static String classPackage = "ec.com.cryptogateway.blockchain.service.";
	
	private static final String METHOD_CHECK_BALANCE ="checkTransaction";
	
	private static final String METHOD_CHECK_BALANCE_ATM ="checkTransactionATM";

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
				 walletEntity.setWalletType(CryptoGatewayConstants.TYPE_WALLET_PAYMENT_BUTTON);
				 walletEntity.setCreationDate(new Date());
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
		Collection<TransactionsVO> transactionsVO = transactionRepository.findAllTransactions();
		
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
                Object object = checkBalanceWallet(data.getJavaClass(),data.getTransactionsVO(),METHOD_CHECK_BALANCE);
				updateTransactions(object);
			}catch (Exception e) {
				log.error("Error to check transactions {}", e);
			}			
		});		
	}
	
	/**
	 * Check balance of a wallet
	 * 
	 * @param javaClass
	 * @param transactionCheckVO
	 * @return
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	private Object checkBalanceWallet(String javaClass, Collection<TransactionsVO> transactionCheckVO, String methodName) throws ClassNotFoundException, 
	NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, 
	InvocationTargetException, InstantiationException {
		
		Class<?> clase = Class.forName(classPackage.concat(javaClass));
		Constructor<?> cons1 = clase.getConstructor();
		Method method = clase.getDeclaredMethod(methodName,Collection.class);
		method.setAccessible(true);
        return method.invoke(cons1.newInstance(),transactionCheckVO);		
	}
	
	/**
	 * Update transactions
	 * 
	 * @param object
	 */
	@SuppressWarnings("unchecked")
    private void updateTransactions(Object object) {
	    Collection<TransactionsVO> transactionsVO = null;
	    if(object!=null) {
	        transactionsVO = (Collection<TransactionsVO>) object;	 
	        transactionsVO.forEach(data->{ 
	            updateTransaction(data);
	        });	        
	    }
	}

	/**
	 * Update Transactions
	 */
	@Override
	public void updateTransaction(TransactionsVO transactionsVO) {
		
		Date actualDate = new Date();
		MailVO mailVO = null;
		TransactionEntity transaction = new TransactionEntity();
		
	   transaction.setCoinsReceived(transactionsVO.getWalletBalance());
       transaction.setId(transactionsVO.getTransactionId());
       transaction.setLastCheckDate(actualDate);
       transaction.setEndTransaction(actualDate);
       
       try {
           transaction.setNumberOfChecks(transactionsVO.getNumberOfChecks()+1);
       }catch (Exception e) {
           log.error("Exeption for to set NumberOfChecks {} "+transactionsVO.getTransactionId(), e);
       }		
       
        if(transactionsVO.getWalletBalance().compareTo(BigDecimal.ZERO)==0 && 
                (transactionsVO.getTimeoutTransaction().compareTo(actualDate)==0 
                || transactionsVO.getTimeoutTransaction().compareTo(actualDate)>0)) {            
            transaction.setTransactionStatusId(CryptoGatewayConstants.STATUS_TRANSACTION_TIMEOUT); 
            mailVO = createEmailContent("",transactionsVO);
        }
        
        else if(transactionsVO.getWalletBalance().compareTo(BigDecimal.ZERO)==0 && 
                (transactionsVO.getTimeoutTransaction().compareTo(actualDate)<0)) {    
            transaction.setTransactionStatusId(CryptoGatewayConstants.STATUS_TRANSACTION_WAITING);
            transaction.setEndTransaction(null);
         } 
        
        else if(transactionsVO.getWalletBalance().compareTo(BigDecimal.ZERO)>0 && 
        		transactionsVO.getWalletBalance().compareTo(transactionsVO.getCoinsAmount())<0) {   
            transaction.setTransactionStatusId(CryptoGatewayConstants.STATUS_TRANSACTION_INCOMPLETE);
            mailVO = createEmailContent("",transactionsVO);
        }        

        else if(transactionsVO.getWalletBalance().compareTo(transactionsVO.getCoinsAmount())==0 
        		|| transactionsVO.getWalletBalance().compareTo(transactionsVO.getCoinsAmount())>0){ 
        	transaction.setTransactionStatusId(CryptoGatewayConstants.STATUS_TRANSACTION_SUCCESSFULL);
        	mailVO = createEmailContent("",transactionsVO);
       } 
        
        transactionRepository.updateTransaction(transaction); 
        
        sendEmailTransaction(mailVO);
	}
	
	/**
	 * Create content email transaction Timeout
	 * 
	 * @param template
	 * @param transactionsVO
	 * @return
	 */
	private MailVO createEmailContent(String template,TransactionsVO transactionsVO) {
	    HashMap<String, Object> parametersMessage = new HashMap<>();
	    parametersMessage.put("transactiondate", transactionsVO.getTimeoutTransaction());//formato de fecha
        parametersMessage.put("transactionID", transactionsVO.getTransactionId()); 
        parametersMessage.put("payment", transactionsVO.getCoinsAmount()); //falta la moneda el simbolo
        parametersMessage.put("received", transactionsVO.getWalletBalance());
        parametersMessage.put("coinId", transactionsVO.getBlockchainId());//Coin ID
	    return null;
	}
	
	/**
	 * Send email transaction
	 * 
	 * @param transactionsVO
	 * @param transaction
	 */
	private void sendEmailTransaction(MailVO mailVO) {
		try {
		    if(mailVO!=null) {
		        emailService.sendMail(mailVO);
		    }
        } catch (Exception e) {
            log.error("Error to sendEmailTransaction {}",e);
        } 
	}
	/**
	 * Create an transaction ATM
	 * 
	 */
	@Override
	public ResponseVO createTransactionATM(TransactionATMVO transactionATMVO) {
		
		WalletCredentialsVO walletCredentialsVO = new WalletCredentialsVO();
		walletCredentialsVO.setAtmPassword(transactionATMVO.getAtmPassword());
		walletCredentialsVO.setPublicKey(transactionATMVO.getPublicKey());
		walletCredentialsVO.setWallet(transactionATMVO.getWallet());
		walletCredentialsVO.setWalletType(CryptoGatewayConstants.TYPE_WALLET_CARD);	
		
		WalletVO walletVO = walletsRepository.findWalletByCredentials(walletCredentialsVO);
		if(walletVO==null) {
			 return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.ERROR_ATM_ACCOUNT_NOT_FOUND);			
		}
		
		StoreQueryVO storeQueryVO = new StoreQueryVO();
		storeQueryVO.setCoinId(transactionATMVO.getCoindId());
		storeQueryVO.setStoreUI(transactionATMVO.getStoreUI());
		
		CryptoCurrencyVO cryptoCurrencyVO = cryptoCurrencyStoreRepository.checkStoreAcceptCoin(storeQueryVO);
		if(cryptoCurrencyVO==null){
			 return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.ERROR_ATM_STORE_NOT_ACCEPT_COIN);		
		}		
		
		TransactionsVO transactionCheckVO = new TransactionsVO();
		transactionCheckVO.setWallet(walletVO.getWalletAddress());
		transactionCheckVO.setPrivateKey(walletVO.getPrivateKey());
		transactionCheckVO.setPublicKey(walletVO.getPublicKey());
		transactionCheckVO.setSmartContract(cryptoCurrencyVO.getSmartContract());
		
		Collection<TransactionsVO> transactionList= new ArrayList<>();
		transactionList.add(transactionCheckVO);

		try {
		
			Object object = checkBalanceWallet(cryptoCurrencyVO.getJavaClass(),transactionList, METHOD_CHECK_BALANCE_ATM);
			
			WalletBalance walletBalance = null;
		    if(object!=null) {
		    	walletBalance = (WalletBalance) object;	 
		    	
		    	//Si es eth solamente debe tener los eth mas el fee 
		    	if(walletBalance.getFee()!=null &&  
		    			StringUtils.isEmpty(cryptoCurrencyVO.getSmartContract()) && 
		    			walletBalance.getBalance().add(walletBalance.getFee())
		    			.compareTo(transactionATMVO.getAmount())>=0) {
		    		
		    		//withdrawl();
		    		
		    	}else {
		    		
		    		return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.ERROR_ATM_WALLET_NOT_HAVE_FUNDS);
		    	}
		    	
		    	//Si es token debe tener tokens mas fee
		    	if(walletBalance.getFee()!=null &&  
		    			!StringUtils.isEmpty(cryptoCurrencyVO.getSmartContract()) && 
		    			walletBalance.getBalanceTokens().compareTo(transactionATMVO.getAmount())>=0 
		    			&& walletBalance.getBalance().compareTo(walletBalance.getFee())>=0) {
		    		
		    		//withdrawl();
		    		
		    	}else {
		    		
		    		return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.ERROR_ATM_WALLET_NOT_HAVE_FUNDS);
		    	}
    
		    }else {
		    	return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.ERROR_TO_CHECK_BALANCE);		    	
		    }
			
		} catch (Exception e) {			
			log.error("Exception to check wallet balance in createTransactionATM {}",e);
			return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.ERROR_TO_CHECK_BALANCE);
		}
			

		return null;		
	}

	
}
