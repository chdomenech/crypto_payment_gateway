package ec.com.cryptogateway.repository;

import java.util.Collection;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.CryptoCurrencyVO;
import ec.com.cryptogateway.base.IQueryDslBaseRepository;
import ec.com.cryptogateway.entity.CryptoCurrencyStoreEntity;

/**
 * CryptoCurrencyStore Repository
 *  
 * @author Christian
 *
 */
public interface ICryptoCurrencyStoreRepository extends IQueryDslBaseRepository<CryptoCurrencyStoreEntity>{

	/**
	 * Get all cryptocurrencies configured by the store
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	Collection<CryptoCurrencyVO> getCryptoCurrencyStore(StoreQueryVO storeQueryVO);
	
	/**
	 * Delete all cryptocurrency of the store
	 * 
	 * @param storeId
	 * @return
	 */
	boolean deleteAllCryptoCurrencyStore(Integer storeId);
	
}
