package ec.com.cryptogateway.repository;

import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.dml.DeleteClause;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPADeleteClause;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.CryptoCurrencyVO;
import ec.com.cryptogateway.base.JPAQueryDslBaseRepository;
import ec.com.cryptogateway.entity.CryptoCurrencyStoreEntity;
import ec.com.cryptogateway.entity.QBlockchainEntity;
import ec.com.cryptogateway.entity.QCryptoCurrencyEntity;
import ec.com.cryptogateway.entity.QCryptoCurrencyStoreEntity;
import ec.com.cryptogateway.entity.QStoreEntity;

/**
 * CryptoCurrencyStoreRepository
 * 
 * @author Christian Domenech
 *
 */
@Lazy
@Repository
public class CryptoCurrencyStoreRepository extends JPAQueryDslBaseRepository<CryptoCurrencyStoreEntity> implements ICryptoCurrencyStoreRepository {
	
	/**
	 * Constructor
	 * 
	 */
	public CryptoCurrencyStoreRepository() {
		super(CryptoCurrencyStoreEntity.class);
	}
	
	/**
	 * Get all cryptocurrencies configured by the store
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	public Collection<CryptoCurrencyVO> getCryptoCurrencyStore(StoreQueryVO storeQueryVO){
		
		 QCryptoCurrencyStoreEntity qCryptoCurrencyStoreEntity = QCryptoCurrencyStoreEntity.cryptoCurrencyStoreEntity;
		 QStoreEntity qStoreEntity = QStoreEntity.storeEntity;
		 QCryptoCurrencyEntity qCryptoCurrencyEntity = QCryptoCurrencyEntity.cryptoCurrencyEntity;
		 QBlockchainEntity qBlockchainEntity = QBlockchainEntity.blockchainEntity;
	        
	     JPQLQuery<CryptoCurrencyVO> query = from(qCryptoCurrencyStoreEntity).select(Projections.bean(CryptoCurrencyVO.class,
	    		 qCryptoCurrencyStoreEntity.storeId.as("idStore"), qCryptoCurrencyEntity.apiUrl,qCryptoCurrencyEntity.apiUrl1, 
	    		 qCryptoCurrencyEntity.apiUrl2, qCryptoCurrencyEntity.id.as("idCoin"), qCryptoCurrencyEntity.coinId, 
	    		 qBlockchainEntity.id.as("blockchainId"), qBlockchainEntity.javaClass, qBlockchainEntity.name.as("blockchainName"),
	    		 qBlockchainEntity.timeoutMinuts));
	     
	     query.innerJoin(qCryptoCurrencyStoreEntity.storeEntity, qStoreEntity);
	     query.innerJoin(qCryptoCurrencyStoreEntity.cryptocurrencyEntity, qCryptoCurrencyEntity);
	     query.innerJoin(qCryptoCurrencyEntity.blockchain, qBlockchainEntity);
	     
	     BooleanBuilder where = new BooleanBuilder();
	     
	     where.and(qCryptoCurrencyEntity.status.eq(Boolean.TRUE));
	     where.and(qStoreEntity.storeUI.eq(storeQueryVO.getStoreUI()));
	     
	     if(storeQueryVO.getCoinId()!=null) {
	    	 where.and(qCryptoCurrencyEntity.coinId.eq(storeQueryVO.getCoinId()));
	     }
	     
	     query.where(where);
	     
	     return query.fetch();
	}
	
	
	/**
	 * Get all cryptocurrencies configured by the store
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	public Collection<CryptoCurrencyVO> getAllCryptoCurrencyByStore(StoreQueryVO storeQueryVO){
		
		 QCryptoCurrencyStoreEntity qCryptoCurrencyStoreEntity = QCryptoCurrencyStoreEntity.cryptoCurrencyStoreEntity;
		 QStoreEntity qStoreEntity = QStoreEntity.storeEntity;
		 QCryptoCurrencyEntity qCryptoCurrencyEntity = QCryptoCurrencyEntity.cryptoCurrencyEntity;
		 QBlockchainEntity qBlockchainEntity = QBlockchainEntity.blockchainEntity;
	        
	     JPQLQuery<CryptoCurrencyVO> query = from(qCryptoCurrencyStoreEntity).select(Projections.bean(CryptoCurrencyVO.class,
	    		 qCryptoCurrencyEntity.id.as("idCoin"), qCryptoCurrencyEntity.coinId, 
	    		 qBlockchainEntity.id.as("blockchainId"), qBlockchainEntity.name.as("blockchainName")));
	     
	     query.innerJoin(qCryptoCurrencyStoreEntity.storeEntity, qStoreEntity);
	     query.innerJoin(qCryptoCurrencyStoreEntity.cryptocurrencyEntity, qCryptoCurrencyEntity);
	     query.innerJoin(qCryptoCurrencyEntity.blockchain, qBlockchainEntity);
	     
	     BooleanBuilder where = new BooleanBuilder();
	     
	     where.and(qCryptoCurrencyEntity.status.eq(Boolean.TRUE));
	     where.and(qStoreEntity.storeUI.eq(storeQueryVO.getStoreUI()));
     
	     query.where(where);
	     
	     return query.fetch();
	}

	/**
	 * Delete al crypto currencys of the store
	 * 
	 */
	@Override
	public boolean deleteAllCryptoCurrencyStore(Integer storeId) {
		
		QCryptoCurrencyStoreEntity qCryptoCurrencyStoreEntity = QCryptoCurrencyStoreEntity.cryptoCurrencyStoreEntity;
		DeleteClause<JPADeleteClause> query = delete(qCryptoCurrencyStoreEntity);
		BooleanBuilder where = new BooleanBuilder();	     
	    where.and(qCryptoCurrencyStoreEntity.storeEntity.id.eq(storeId));
	    query.where(where);
	    return query.execute()>0;
	}

	/**
	 * Check if the store accept this type cryptocurrency
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	@Override
	public CryptoCurrencyVO checkStoreAcceptCoin(StoreQueryVO storeQueryVO) {
		
		QCryptoCurrencyStoreEntity qCryptoCurrencyStoreEntity = QCryptoCurrencyStoreEntity.cryptoCurrencyStoreEntity;
		QStoreEntity qStoreEntity = QStoreEntity.storeEntity;
		QCryptoCurrencyEntity qCryptoCurrencyEntity = QCryptoCurrencyEntity.cryptoCurrencyEntity;
		QBlockchainEntity qBlockchainEntity = QBlockchainEntity.blockchainEntity;
		
		 JPQLQuery<CryptoCurrencyVO> query = from(qCryptoCurrencyStoreEntity).select(Projections.bean(CryptoCurrencyVO.class,
	    		 qCryptoCurrencyStoreEntity.storeId.as("idStore"), qCryptoCurrencyEntity.smartContract, qCryptoCurrencyEntity.blockchainId,
	    		 qCryptoCurrencyEntity.id.as("idCoin"), qCryptoCurrencyEntity.coinId,qBlockchainEntity.javaClass));
	     
	     query.innerJoin(qCryptoCurrencyStoreEntity.storeEntity, qStoreEntity);
	     query.innerJoin(qCryptoCurrencyStoreEntity.cryptocurrencyEntity, qCryptoCurrencyEntity);
	     query.innerJoin(qCryptoCurrencyEntity.blockchain, qBlockchainEntity);
	    
		 BooleanBuilder where = new BooleanBuilder();	     
		 where.and(qStoreEntity.storeUI.eq(storeQueryVO.getStoreUI()));
		 where.and(qCryptoCurrencyEntity.coinId.eq(storeQueryVO.getCoinId()));
		 query.where(where);
		 
		 return query.fetchFirst();
		
	}

}
