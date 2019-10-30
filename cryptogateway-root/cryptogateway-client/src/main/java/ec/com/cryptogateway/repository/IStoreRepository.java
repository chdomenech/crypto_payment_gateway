package ec.com.cryptogateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ec.com.cryptogateway.entity.StoreEntity;

/**
 * Store Repository
 * 	
 * @author Christian
 *
 */
@Transactional(readOnly = true) 
public interface IStoreRepository extends JpaRepository<StoreEntity, Integer>{

}
