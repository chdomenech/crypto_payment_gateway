package ec.com.cryptogateway.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.request.StoreSaveVO;
import cryptogateway.vo.response.CryptoCurrencyVO;
import cryptogateway.vo.response.ResponseVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import cryptogateway.vo.response.StoreVO;
import ec.com.cryptogateway.entity.StoreEntity;
import ec.com.cryptogateway.repository.ICryptoCurrencyStoreRepository;
import ec.com.cryptogateway.repository.IStoreRepository;
import ec.com.cryptogateway.utils.ApisUtil;
import ec.com.cryptogateway.utils.CryptoGatewayConstants;
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
	 * Find user by credentials
	 * 
	 */
	@Override
	public ResponseVO findUserByCredentials(CredentialsVO credentialsVO) {	
		StoreVO storeVO = storeRepository.findStoreByCredentials(credentialsVO);		
		ResponseVO responseVO;
		if(storeVO!=null) {			
			responseVO = CoreUtils.getResponseVO(CryptoGatewayConstants.MESSAGE_SUCCESSFULL_USER_FOUND, CryptoGatewayConstants.STATUS_SUCCESSFULL);						
		}else {
			responseVO = CoreUtils.getResponseVO(CryptoGatewayConstants.MESSAGE_SUCCESSFULL_USER_NOT_FOUND, CryptoGatewayConstants.STATUS_ERROR);			
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
		
		
		//valida email, usuario, tamaño de password, tamaño de nombre de tienda
				
		StoreEntity storeEntity = new StoreEntity();
		storeEntity.setCreationDate(new Date());
		storeEntity.setEmail(storeSaveVO.getEmail());
		storeEntity.setUser(storeSaveVO.getUser());
		storeEntity.setPassword(storeSaveVO.getPassword());
		storeEntity.setStoreName(storeSaveVO.getStoreName());
		storeEntity.setStoreUI(CoreUtils.createTransactionID(12).toUpperCase());			
		storeRepository.save(storeEntity);
		
		//Send email de confirmation		
		try {		
			return CoreUtils.getResponseVO(CryptoGatewayConstants.MESSAGE_SUCCESSFULL_STORE_SAVED, CryptoGatewayConstants.STATUS_SUCCESSFULL);	
		
		}catch (Exception e) {
			return CoreUtils.getResponseVO(e.getMessage(), CryptoGatewayConstants.STATUS_ERROR);	
		}
	}

	@Override
	public ResponseVO resendPassword(CredentialsVO credentialsVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseVO savePassword(CredentialsVO credentialsVO) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResponseVO updateStore(StoreVO storeVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseVO saveCoinsConfiguration(Collection<CryptoCurrencyVO> coins) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
