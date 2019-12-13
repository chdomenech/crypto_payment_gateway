package ec.com.cryptogateway.repository;

import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import cryptogateway.vo.request.TransactionsVO;
import ec.com.cryptogateway.base.JPAQueryDslBaseRepository;
import ec.com.cryptogateway.entity.QCryptoCurrencyEntity;
import ec.com.cryptogateway.entity.QStoreEntity;
import ec.com.cryptogateway.entity.QTransactionEntity;
import ec.com.cryptogateway.entity.QWalletsEntity;
import ec.com.cryptogateway.entity.TransactionEntity;
import ec.com.cryptogateway.utils.CryptoGatewayConstants;

/**
 * Transaction Repository
 * 
 * @author Christian
 *
 */
@Lazy
@Repository
public class TransactionRepository extends JPAQueryDslBaseRepository<TransactionEntity> implements ITransactionRepository  {

	/**
	 * 
	 */
	public TransactionRepository() {
		super(TransactionEntity.class);
	}

	/**
	 * Find all transactions
	 */
	@Override
	public Collection<TransactionsVO> findAllTransactions() {
		
		QTransactionEntity qTransactionEntity = QTransactionEntity.transactionEntity;
		QWalletsEntity qWalletEntity = QWalletsEntity.walletsEntity;
		QCryptoCurrencyEntity qCryptoCurrencyEntity = QCryptoCurrencyEntity.cryptoCurrencyEntity;
		QStoreEntity qStoreEntity = QStoreEntity.storeEntity;
		
		JPQLQuery<TransactionsVO> query = from(qTransactionEntity).select(Projections.bean(TransactionsVO.class,
				  qTransactionEntity.id.as("transactionId"), qTransactionEntity.blockchainId,
				  qTransactionEntity.coinsAmount, qTransactionEntity.timeoutTransaction, 
				  qTransactionEntity.numberOfChecks, qWalletEntity.wallet, 
				  qWalletEntity.privateKey, qWalletEntity.publicKey,
				  qCryptoCurrencyEntity.smartContract, qStoreEntity.email, qStoreEntity.id.as("storeId")));
		     
	     query.innerJoin(qTransactionEntity.wallet, qWalletEntity);
	     query.innerJoin(qTransactionEntity.cryptoCurrency, qCryptoCurrencyEntity);
	     query.innerJoin(qTransactionEntity.store, qStoreEntity);
	     query.orderBy(qTransactionEntity.blockchainId.desc());
	     BooleanBuilder where = new BooleanBuilder();
	    
	     where.and(qTransactionEntity.transactionStatusId.eq(CryptoGatewayConstants.STATUS_TRANSACTION_WAITING));
	     query.where(where);
	     
	     return query.fetch();
		
	}

	/**
	 * Update transaction
	 * 
	 */
    @Override
    public void updateTransaction(TransactionEntity transactionEntity) {
        QTransactionEntity qTransactionEntity = QTransactionEntity.transactionEntity;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qTransactionEntity.id.eq(transactionEntity.getId()));
        update(qTransactionEntity).where(where)
        .set(qTransactionEntity.coinsReceived, transactionEntity.getCoinsReceived())
        .set(qTransactionEntity.lastCheckDate, transactionEntity.getLastCheckDate())
        .set(qTransactionEntity.numberOfChecks, transactionEntity.getNumberOfChecks())       
         .set(qTransactionEntity.transactionStatusId, transactionEntity.getTransactionStatusId()).execute();

        
    }
}
