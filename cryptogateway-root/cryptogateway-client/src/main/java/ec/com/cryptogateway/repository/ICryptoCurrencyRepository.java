package ec.com.cryptogateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.cryptogateway.entity.CryptoCurrencyEntity;

public interface ICryptoCurrencyRepository extends JpaRepository<CryptoCurrencyEntity, Integer> {

}
