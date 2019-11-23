package ec.com.cryptogateway.service;

import java.util.Collection;
import java.util.List;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.response.CryptoCurrencyVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.request.StoreSaveVO;
import cryptogateway.vo.response.ResponseVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import cryptogateway.vo.response.StoreVO;

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
    ResponseVO resendPassword(CredentialsVO credentialsVO);
    
    /**
     * Save the password
     * 
     * @param credentialsVO
     * @return
     */
    ResponseVO savePassword(CredentialsVO credentialsVO);
    
    
    /**
     * Save configuration of coins for the store
     * 
     * @param coins
     * @return
     */
    ResponseVO saveCoinsConfiguration(Collection<CryptoCurrencyVO> coins);
    
    /**
     * Update the store
     * 
     * @param coins
     * @return
     */
    ResponseVO updateStore(StoreVO storeVO);
}
