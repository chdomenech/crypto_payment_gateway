package ec.com.cryptogateway.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import ec.com.cryptogateway.entity.CryptoCurrencyEntity;

public interface ICryptoCurrencyRepository extends QuerydslPredicateExecutor<CryptoCurrencyEntity> {

}
