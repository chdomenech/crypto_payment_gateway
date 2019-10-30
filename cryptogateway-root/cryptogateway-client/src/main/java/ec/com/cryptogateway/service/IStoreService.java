package ec.com.cryptogateway.service;

import java.util.Optional;

import ec.com.cryptogateway.entity.StoreEntity;

/**
 * Store Service
 * 
 * @author Christian
 *
 */
public interface IStoreService{
	
    Optional<StoreEntity> encuentratodo(Integer id);
}
