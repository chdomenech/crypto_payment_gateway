package ec.com.cryptogateway.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.StoreVO;
import ec.com.cryptogateway.base.JPAQueryDslBaseRepository;
import ec.com.cryptogateway.entity.QStoreEntity;
import ec.com.cryptogateway.entity.StoreEntity;

/**
 * Store Repository
 * 
 * @author Christian
 *
 */
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
        where.and(qStoreEntity.email.eq(credentialsVO.getEmail())).or(qStoreEntity.user.eq(credentialsVO.getUser()));
        where.and(qStoreEntity.password.eq(credentialsVO.getPassword()));
        query.where(where);
        return query.fetchOne();
	}
	
	/**
	 * Find store by some field
	 * 
	 */
	@Override
	public Boolean isExist(StoreQueryVO storeVO) {
		Boolean result = false;
		QStoreEntity qStoreEntity = QStoreEntity.storeEntity;		
		 JPQLQuery<StoreVO> query = from(qStoreEntity).select(Projections.bean(StoreVO.class, 
				 qStoreEntity.email.as("email")));
		 
        BooleanBuilder where = new BooleanBuilder();
        
        if(!StringUtils.isEmpty(storeVO.getEmail())){
        	where.and(qStoreEntity.email.eq(storeVO.getEmail()));
        }
        if(!StringUtils.isEmpty(storeVO.getUser())){
        	where.and(qStoreEntity.user.eq(storeVO.getUser()));
        }
        if(!StringUtils.isEmpty(storeVO.getStoreName())){
        	where.and(qStoreEntity.storeName.eq(storeVO.getStoreName()));
        }
        query.where(where);
        if(query.fetchCount()>0) {
        	result= true;
        }
        
        return result;
	}

	@Override
	public StoreEntity findStoreByParams(StoreQueryVO storeQuery) {
		QStoreEntity qStoreEntity = QStoreEntity.storeEntity;		
		 JPQLQuery<StoreEntity> query = from(qStoreEntity);
		 
       BooleanBuilder where = new BooleanBuilder();
       
       if(!StringUtils.isEmpty(storeQuery.getEmail())){
       	where.and(qStoreEntity.email.eq(storeQuery.getEmail()));
       }
       if(!StringUtils.isEmpty(storeQuery.getUser())){
       	where.and(qStoreEntity.user.eq(storeQuery.getUser()));
       }
       if(!StringUtils.isEmpty(storeQuery.getStoreName())){
       	where.and(qStoreEntity.storeName.eq(storeQuery.getStoreName()));
       }       
       if(!StringUtils.isEmpty(storeQuery.getId())){
          	where.and(qStoreEntity.id.eq(storeQuery.getId()));
       }
       if(!StringUtils.isEmpty(storeQuery.getStoreUI())){
         	where.and(qStoreEntity.storeUI.eq(storeQuery.getStoreUI()));
      }
      query.where(where);
      return query.fetchFirst();
	}

}
