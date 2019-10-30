package ec.com.cryptogateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.cryptogateway.entity.StoreEntity;

/**
 * Store Repository
 * 	
 * @author Christian
 *
 */
public interface IStoreRepository extends JpaRepository<StoreEntity, Integer>{
	
    
    /**
     * Save Store
     * 
     * @param store
     * @return 
     */
    void create(StoreEntity store);

    
    /**
     * update Store
     * 
     * @param store
     * @return 
     */
    Boolean update(StoreEntity store);
    
    
    /**
     * Save Store
     * 
     * @param store
     * @return 
     */
    StoreEntity finById(Integer storeId);
    
    /*Declaration of a static delegate method 
Example :
 @QueryDelegate(User.class)
 public static Predicate like(QUser entity, User user) {
     BooleanBuilder builder = new BooleanBuilder();
     if (user.getFirstName() != null) {
         builder.and(entity.firstName.eq(user.getFirstName()));
     }
     if (user.getLastName() != null) {
         builder.and(entity.lastName.eq(user.getLastName()));
     }
     return builder.getValue();
 }
s*/
}
