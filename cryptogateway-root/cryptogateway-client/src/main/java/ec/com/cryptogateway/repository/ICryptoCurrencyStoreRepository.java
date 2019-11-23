package ec.com.cryptogateway.repository;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true) 
public interface ICryptoCurrencyStoreRepository extends IQueryDslBaseRepository<CryptoCurrencyStoreEntity>{

	/**
	 * Get all cryptocurrencies configured by the store
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	Collection<CryptoCurrencyVO> getCryptoCurrencyStore(StoreQueryVO storeQueryVO);
	
}
