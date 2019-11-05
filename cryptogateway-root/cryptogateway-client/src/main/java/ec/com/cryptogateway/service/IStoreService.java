package ec.com.cryptogateway.service;

import java.util.List;
import java.util.Optional;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import ec.com.cryptogateway.entity.StoreEntity;

/**
 * Store Service
 * 
 * @author Christian
 *
 */
public interface IStoreService{
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<StoreEntity> findStoreByID(Integer id);
	
	/**
	 * 
	 * @param storeQueryVO
	 * @return
	 */
    List<StoreCryptoCurrenciesVO> findCrytptoCurrenciesForUIstore(StoreQueryVO storeQueryVO);
}
