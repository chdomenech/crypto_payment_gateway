package ec.com.cryptogateway.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.response.StoreVO;
import ec.com.cryptogateway.base.JPAQueryDslBaseRepository;
import ec.com.cryptogateway.entity.QStoreEntity;
import ec.com.cryptogateway.entity.StoreEntity;

@Lazy
@Repository
public class StoreRepository extends JPAQueryDslBaseRepository<StoreEntity> implements IStoreRepository {

	/**
	 * 
	 */
	public StoreRepository() {
		super(StoreEntity.class);
	}
	
	/**
	 * Find the store by credentials
	 */
	@Override
	public StoreVO findStoreByCredentials(CredentialsVO credentialsVO) {
		QStoreEntity qStoreEntity = QStoreEntity.storeEntity;
		
		 JPQLQuery<StoreVO> query = from(qStoreEntity).select(Projections.bean(StoreVO.class, 
				 qStoreEntity.email.as("email")));
        BooleanBuilder where = new BooleanBuilder();
        where.and(qStoreEntity.email.eq(credentialsVO.getEmail()));
        where.and(qStoreEntity.password.eq(credentialsVO.getPassword()));
        query.where(where);
        return query.fetchOne();
	}

}
