package ec.com.cryptogateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.cryptogateway.entity.TransactionStatusEntity;


/**
 * TransactionStatusRepository Repository
 * 	
 * @author Christian
 *
 */
public interface ITransactionStatusRepository extends JpaRepository<TransactionStatusEntity,Integer>{

}
