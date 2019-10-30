package ec.com.cryptogateway.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import ec.com.cryptogateway.entity.TransactionEntity;

/**
 * TransactionRepository Repository
 * 	
 * @author Christian
 *
 */
public interface ITransactionRepository extends QuerydslPredicateExecutor<TransactionEntity>{

}
