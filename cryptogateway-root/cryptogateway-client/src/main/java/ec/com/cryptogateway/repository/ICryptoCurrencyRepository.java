package ec.com.cryptogateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.cryptogateway.entity.CryptoCurrencyEntity;

/**
 * CryptoCurrency Repository
 *  
 * @author Christian
 *
 */
public interface ICryptoCurrencyRepository extends JpaRepository<CryptoCurrencyEntity, Integer> {

}
