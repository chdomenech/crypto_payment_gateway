package ec.com.cryptogateway.service;

import java.util.List;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.request.StoreSaveVO;
import cryptogateway.vo.request.StoreUpdateVO;
import cryptogateway.vo.response.CryptoCurrencyVO;
import cryptogateway.vo.response.ResponseVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;

/**
 * Store Service
 * 
 * @author Christian
 *
 */
public interface IStoreService{
	
	
	/**
	 * List all cryptocurrencys of store
	 * 
	 * @param storeQueryVO
	 * @return
	 */
    List<StoreCryptoCurrenciesVO> findCoinsForUIstore(StoreQueryVO storeQueryVO);
    
    /**
     * List all cryptocurrencys of the store
     * 
     * @param storeQueryVO
     * @return
     */
    ResponseVO findAllCoinsForUIstore(StoreQueryVO storeQueryVO);
    
    /**
     * Find a user by credentials
     * 
     * @param credentialsVO
     * @return
     */
    ResponseVO findUserByCredentials(CredentialsVO credentialsVO);
    
    /**
     * Save the store
     * 
     * @param storeSaveVO
     */
    ResponseVO saveStore(StoreSaveVO storeSaveVO);
    
   
    /**
     * Resend the password
     * 
     * @param credentialsVO
     * @return
     */
    ResponseVO resendPassword(StoreQueryVO storeQueryVO);
    
    /**
     * Save configuration of coins for the store
     * 
     * @param coins
     * @return
     */
    ResponseVO saveCoinsConfiguration(CryptoCurrencyVO coins);
    
    /**
     * Update the store
     * 
     * @param storeUpdateVO
     * @return
     */
    ResponseVO updateStore(StoreUpdateVO storeUpdateVO);
}
