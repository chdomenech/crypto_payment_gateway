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
	
	public Boolean saveStore(StoreEntity store);
	
	public Optional<StoreEntity> finById(Integer storeId);
	
	public long count();
	
	public Iterable<StoreEntity> findAll();
}
