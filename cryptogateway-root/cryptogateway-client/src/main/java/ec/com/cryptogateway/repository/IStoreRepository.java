package ec.com.cryptogateway.repository;

import org.springframework.transaction.annotation.Transactional;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.StoreVO;
import ec.com.cryptogateway.base.IQueryDslBaseRepository;
import ec.com.cryptogateway.entity.StoreEntity;

/**
 * Store Repository
 * 	
 * @author Christian
 *
 */
@Transactional(readOnly = true) 
public interface IStoreRepository extends IQueryDslBaseRepository<StoreEntity> {

	/**
	 * Find store by credentials
	 * 
	 * @param credentialsVO
	 * @return
	 */
	StoreVO  findStoreByCredentials(CredentialsVO credentialsVO);
	
	/**
	 * Find store by some field
	 * 
	 */
	Boolean isExist(StoreQueryVO storeVO) ;	
	
	
	/**
	 * 
	 * @param storeVO
	 * @return
	 */
	StoreEntity findStoreByParams(StoreQueryVO storeQuery);
	
}
