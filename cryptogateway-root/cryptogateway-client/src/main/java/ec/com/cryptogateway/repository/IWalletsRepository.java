package ec.com.cryptogateway.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import ec.com.cryptogateway.entity.WalletsEntity;

/**
 * WalletsRepository Repository
 * 	
 * @author Christian
 *
 */
public interface IWalletsRepository extends QuerydslPredicateExecutor<WalletsEntity> {

}
