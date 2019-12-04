package ec.com.cryptogateway.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.request.StoreSaveVO;
import cryptogateway.vo.request.StoreUpdateVO;
import cryptogateway.vo.response.CryptoCurrencyVO;
import cryptogateway.vo.response.ResponseVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import cryptogateway.vo.response.StoreVO;
import ec.com.cryptogateway.entity.CryptoCurrencyEntity;
import ec.com.cryptogateway.entity.CryptoCurrencyStoreEntity;
import ec.com.cryptogateway.entity.StoreEntity;
import ec.com.cryptogateway.repository.ICryptoCurrencyRepository;
import ec.com.cryptogateway.repository.ICryptoCurrencyStoreRepository;
import ec.com.cryptogateway.repository.IStoreRepository;
import ec.com.cryptogateway.utils.ApisUtil;
import ec.com.cryptogateway.utils.CryptoGatewayConstants;
import ec.cryptogateway.utils.AES256;
import ec.cryptogateway.utils.CoreUtils;

/**
 * Store Service
 * 
 * @author Christian
 *
 */
@Transactional
@Service
public class StoreService implements IStoreService{
    
    @Autowired
    private IStoreRepository storeRepository;
    
    @Autowired
    private ICryptoCurrencyRepository cryptoCurrencyRepository;
    
    @Autowired
    private ICryptoCurrencyStoreRepository cryptoCurrencyStoreRepository;

   
    /**
     * Find all information of the cryptoCurrency
     * of the stores
     * 
     */
	@Override
	public List<StoreCryptoCurrenciesVO> findCoinsForUIstore(StoreQueryVO storeQueryVO) {
		ArrayList<StoreCryptoCurrenciesVO> storeCryptoCurrencies= new ArrayList<>();
		Collection<CryptoCurrencyVO> cryptoCurrency = cryptoCurrencyStoreRepository.getCryptoCurrencyStore(storeQueryVO);			 	
		if(!CollectionUtils.isEmpty(cryptoCurrency)){
			cryptoCurrency.forEach(data -> {
				ApisUtil.getApiInformation(data, storeQueryVO, storeCryptoCurrencies);
			});	
		}	
		
		return storeCryptoCurrencies;		
		
	}
	
    /**
     * List all cryptocurrencys of the store
     * 
     * @param storeQueryVO
     * @return
     */
	@Override
	public ResponseVO findAllCoinsForUIstore(StoreQueryVO storeQueryVO) {
			
		if(storeQueryVO==null || storeQueryVO.getStoreUI()== null)
			return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_ERROR_STORE_NOT_FOUND);	
		
		List<StoreCryptoCurrenciesVO> storeCryptoCurrencies= findCoinsForUIstore(storeQueryVO);
		
		if(storeCryptoCurrencies.size()>0) {		
			return new ResponseVO(CryptoGatewayConstants.FULL_LIST, null, storeCryptoCurrencies);	
		}else {	
			return new ResponseVO(CryptoGatewayConstants.EMPTY_LIST, CryptoGatewayConstants.MESSAGE_STORE_COINS_CONFIGURATION, storeCryptoCurrencies);
		}

	}
	
	

	/**
	 * Find user by credentials
	 * 
	 */
	@Override
	public ResponseVO findUserByCredentials(CredentialsVO credentialsVO) {	
		
		StoreVO storeVO = storeRepository.findStoreByCredentials(credentialsVO);		
		ResponseVO responseVO;
		if(storeVO!=null) {			
			responseVO =  new ResponseVO( CryptoGatewayConstants.STATUS_SUCCESSFULL,CryptoGatewayConstants.MESSAGE_SUCCESSFULL_USER_FOUND);						
		}else {
			responseVO =  new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_ERROR_USER_NOT_FOUND);			
		}
		return responseVO;
	}

	/**
	 * Save the store
	 * 
	 * @param storeSaveVO
	 * @return
	 */
	@Override
	public ResponseVO saveStore(StoreSaveVO storeSaveVO) {
		
		ResponseVO responseValidation = validateStorePatterns(storeSaveVO);
		if(responseValidation!=null) {
			return responseValidation;
		}
		
		ResponseVO responseExistStore = validateIfExistStore(storeSaveVO);
		if(responseExistStore!=null) {
			return responseExistStore;
		}
				
		StoreEntity storeEntity = new StoreEntity();
		storeEntity.setCreationDate(new Date());
		storeEntity.setEmail(storeSaveVO.getEmail());
		storeEntity.setUser(storeSaveVO.getUser());
		storeEntity.setStoreName(storeSaveVO.getStoreName());
		storeEntity.setPassword(AES256.encrypt(storeSaveVO.getPassword(), AES256.SECRET_KEY));
		storeEntity.setStoreUI(CoreUtils.createIdentifierRandom(12).toUpperCase());			
		storeRepository.save(storeEntity);
		
		//send Email
		sendEmail(storeEntity);
	
		return new ResponseVO(CryptoGatewayConstants.STATUS_SUCCESSFULL, CryptoGatewayConstants.MESSAGE_SUCCESSFULL_STORE_SAVED);
	}
	
	/**
	 * Send email
	 * 
	 * @param storeEntity
	 */
	private void sendEmail(StoreEntity storeEntity) {
		
		
	}
	
	/**
	 * Validate if store exist
	 * 
	 * @param storeSaveVO
	 */
	private ResponseVO validateIfExistStore(StoreSaveVO storeSaveVO) {
		
		ResponseVO responseVO = null;
		
		Collection<String> errorsList = new ArrayList<String>();
		
		/* Validate if the store exist  */
		StoreQueryVO storeQueryVO = new StoreQueryVO();
		storeQueryVO.setEmail(storeSaveVO.getEmail());		
		if(storeRepository.isExist(storeQueryVO)) {
			errorsList.add(CryptoGatewayConstants.EMAIL_EXIST);			
		}
		
		storeQueryVO = new StoreQueryVO();
		storeQueryVO.setUser(storeSaveVO.getUser());
		if(storeRepository.isExist(storeQueryVO)) {
			errorsList.add(CryptoGatewayConstants.USER_EXIST);					
		}
		
		storeQueryVO = new StoreQueryVO();
		storeQueryVO.setStoreName(storeSaveVO.getStoreName());
		if(storeRepository.isExist(storeQueryVO)) {
			errorsList.add(CryptoGatewayConstants.STORE_NAME_EXIST);		
		}
		
		if(errorsList.size()>0) {
			responseVO =  new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_VALIDATION_WARNING,null,errorsList);
		}
		
		return responseVO;
	}

	/**
	 * Validate patterns of fields
	 * 
	 * @param storeSaveVO
	 */
	private ResponseVO validateStorePatterns(StoreSaveVO storeSaveVO) {
		
		ResponseVO responseVO = null;
		
		Collection<String> warningList = new ArrayList<String>();
		
		/* Validate patterns */
		if(StringUtils.isEmpty(storeSaveVO.getPassword()) || !CoreUtils.validatePassword(storeSaveVO.getPassword())) {
			warningList.add(CryptoGatewayConstants.INVALID_PASSWORD);
		}
		
		if(!storeSaveVO.getPassword().equals(storeSaveVO.getRepeatPassword())){
			warningList.add(CryptoGatewayConstants.DIFFERENT_PASSWORD);
		}
		
		if(StringUtils.isEmpty(storeSaveVO.getEmail()) || !CoreUtils.validateEmail(storeSaveVO.getEmail())) {
			warningList.add(CryptoGatewayConstants.INVALID_EMAIL);
		}
		
		if(StringUtils.isEmpty(storeSaveVO.getUser()) || !CoreUtils.validateUser(storeSaveVO.getUser())) {
			warningList.add(CryptoGatewayConstants.INVALID_USER);
		}
		
		if(StringUtils.isEmpty(storeSaveVO.getStoreName()) ||  !CoreUtils.validateStoreName(storeSaveVO.getStoreName())) {
			warningList.add(CryptoGatewayConstants.INVALID_STORE_NAME);
		}
		
		if(warningList.size()>0) {
			responseVO =  new ResponseVO(CryptoGatewayConstants.STATUS_WARNING,	CryptoGatewayConstants.MESSAGE_VALIDATION_WARNING,warningList,null);
		}
		
		return responseVO;
	}


	/**
	 * Resend the password
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	@Override
	public ResponseVO resendPassword(StoreQueryVO storeQueryVO) {
		
		StoreEntity storeEntity = storeRepository.findStoreByParams(storeQueryVO);
		if(storeEntity==null) {
			return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_ERROR_STORE_NOT_FOUND);	
		}else {
			
			String password = AES256.decrypt(storeEntity.getPassword(), AES256.SECRET_KEY);						
			sendEmail(null);			
			return new ResponseVO(CryptoGatewayConstants.STATUS_SUCCESSFULL, 
					CryptoGatewayConstants.MESSAGE_SUCCESSFULL_STORE_UPDATED);
		}

	}

	/**
	 * Save coins to the store
	 * 
	 * @param coins
	 * @return
	 */
	@Override
	public ResponseVO saveCoinsConfiguration(CryptoCurrencyVO cryptoCurrencyVO) {
		
		/* Id sera tomado de la sesion cuando se implement spring security*/
		StoreQueryVO storeQueryVO = new StoreQueryVO();
		storeQueryVO.setId(cryptoCurrencyVO.getIdStore());
		
		StoreEntity storeEntity = storeRepository.findStoreByParams(storeQueryVO);
		if(storeEntity==null) {
			return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_ERROR_STORE_NOT_FOUND);	
		}
		
		if(CollectionUtils.isEmpty(cryptoCurrencyVO.getCoins())) {
			return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_ERROR_COINS_NOT_FOUND);			
		}
		
		Collection<CryptoCurrencyEntity> cryptoCurrency = cryptoCurrencyRepository.findCryptoCurrencys(cryptoCurrencyVO.getCoins());
		if(CollectionUtils.isEmpty(cryptoCurrency)) {
			return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_ERROR_COINS_NOT_FOUND);			
		}else{
			
			cryptoCurrencyStoreRepository.deleteAllCryptoCurrencyStore(storeEntity.getId());
			cryptoCurrency.forEach(data->{
				CryptoCurrencyStoreEntity cryptoCurrencyStoreEntity = new CryptoCurrencyStoreEntity();
				cryptoCurrencyStoreEntity.setCryptoCurrencyId(data.getId());
				cryptoCurrencyStoreEntity.setStoreId(storeEntity.getId());
				cryptoCurrencyStoreEntity.setStatus(Boolean.TRUE);
				cryptoCurrencyStoreRepository.save(cryptoCurrencyStoreEntity);				
			});
			
			return new ResponseVO(CryptoGatewayConstants.STATUS_SUCCESSFULL, CryptoGatewayConstants.MESSAGE_SUCCESSFULL_COIN_STORE_SAVED);
		}
	}

	/**
	 * Actualiza la tienda
	 * 
	 * @param storeUpdateVO
	 * @return
	 */
	@Override
	public ResponseVO updateStore(StoreUpdateVO storeUpdateVO) {
		
		/* Id sera tomado de la sesion cuando se implement spring security*/
		StoreQueryVO storeQueryVO = new StoreQueryVO();
		storeQueryVO.setId(storeUpdateVO.getId());
		
		if(storeUpdateVO == null || storeUpdateVO.getId() == null) {
			return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_ERROR_DATOS_REQUERIDOS);	
		}
		
		StoreEntity storeEntity = storeRepository.findStoreByParams(storeQueryVO);
		if(storeEntity==null) {
			return new ResponseVO(CryptoGatewayConstants.STATUS_ERROR, CryptoGatewayConstants.MESSAGE_ERROR_STORE_NOT_FOUND);	
		}else {
			StoreSaveVO storeSaveVO =new StoreSaveVO();
			storeSaveVO.setEmail(storeEntity.getEmail());
			
			storeSaveVO.setPassword(storeUpdateVO.getPassword());
			storeSaveVO.setRepeatPassword(storeUpdateVO.getRepeatPassword());
			
			storeSaveVO.setStoreName(storeUpdateVO.getStoreName());
			storeSaveVO.setUser(storeEntity.getUser());		
			
			ResponseVO responseValidation = validateStorePatterns(storeSaveVO);
			if(responseValidation!=null) {
				return responseValidation;
			}
			
			storeEntity.setStoreName(storeUpdateVO.getStoreName());
			storeSaveVO.setPassword(AES256.encrypt(storeUpdateVO.getPassword(), AES256.SECRET_KEY));
			
			storeRepository.update(storeEntity);
			
			//send Email
			sendEmail(storeEntity);
				
			return new ResponseVO(CryptoGatewayConstants.STATUS_SUCCESSFULL, CryptoGatewayConstants.MESSAGE_SUCCESSFULL_STORE_UPDATED);
			
		}
	}

}
