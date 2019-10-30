package ec.com.cryptogateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.cryptogateway.entity.TransactionEntity;

/**
 * TransactionRepository Repository
 * 	
 * @author Christian
 *
 */
public interface ITransactionRepository extends JpaRepository<TransactionEntity,Integer>{

}
