package ec.com.cryptogateway.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ec.com.cryptogateway.entity.CryptoCurrencyEntity;
import ec.com.cryptogateway.repository.vo.CryptoCurrencyVO;

/**
 * CryptoCurrencyStore Repository
 *  
 * @author Christian
 *
 */
public interface ICryptoCurrencyStoreRepository  extends JpaRepository<CryptoCurrencyEntity, Integer> {

	@Query(value = "SELECT c.coin_id as coinId,  c.api_url as api1, c.api_url_1 as api2, " + 
			"c.api_url_2 as api3, c.id as id FROM cryptocurrency_store cs " + 
			"inner join store st on cs.store_id= st.id " + 
			"inner join cryptocurrency c on cs.cryptocurrency_id= c.id " + 
			"where cs.status=1 and st.store_ui = ?1", nativeQuery = true)
	Collection<CryptoCurrencyVO> getCryptoCurrencysApis(String storeUI);
}
