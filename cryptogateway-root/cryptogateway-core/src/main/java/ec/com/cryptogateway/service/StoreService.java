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
    public Optional<StoreEntity> encuentratodo(Integer id) {
        return storeRepository.findById(id);
    }

	
}
