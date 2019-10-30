package ec.com.cryptogateway.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.cryptogateway.entity.StoreEntity;
import ec.com.cryptogateway.repository.IStoreRepository;

/**
 * Store Service
 * 
 * @author Christian
 *
 */
@Service
public class StoreService implements IStoreService{
    
	@Autowired
    private IStoreRepository storeRepository;

	@Override
	public Boolean saveStore(StoreEntity store) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<StoreEntity> finById(Integer storeId) {
		return storeRepository.findById(storeId);
	}

	@Override
	public long count() {
		return storeRepository.count();
	}
	
	@Override
	public Iterable<StoreEntity> findAll() {
		return storeRepository.findAll();
	}   
    
}
