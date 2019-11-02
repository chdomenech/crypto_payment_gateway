package ec.com.cryptogateway.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.com.cryptogateway.entity.CryptoCurrencyEntity;

/**
 * CryptoCurrencyStore Repository
 *  
 * @author Christian
 *
 */
public interface ICryptoCurrencyStoreRepository  extends JpaRepository<CryptoCurrencyEntity, Integer> {

	@Query(value = "SELECT c.api_url FROM cryptocurrency_store cs " + 
			"inner join store st on cs.store_id= st.id " + 
			"inner join cryptocurrency c on cs.cryptocurrency_id= c.id " + 
			"where cs.status=1 and st.store_ui =  ?1", nativeQuery = true)
	Collection<String> getCryptoCurrencysApis(String storeUI);
}
