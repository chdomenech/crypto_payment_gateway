package ec.com.cryptogateway.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.CoingekoApiVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import ec.com.cryptogateway.entity.StoreEntity;
import ec.com.cryptogateway.repository.ICryptoCurrencyStoreRepository;
import ec.com.cryptogateway.repository.IStoreRepository;

/**
 * Store Service
 * 
 * @author Christian
 *
 */
@Service
public class StoreService implements IStoreService{
    
    @Autowired
    private IStoreRepository storeRepository;
    
    @Autowired
    private ICryptoCurrencyStoreRepository cryptoCurrencyStoreRepository;

    @Override
    public Optional<StoreEntity> findStoreByID(Integer id) {
        return storeRepository.findById(id);
    }
    
    public Object getCryptoCurrencyInfo(String urlWebService) {
    		HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();
            String url = urlWebService;
            HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
            ResponseEntity<CoingekoApiVO> responseEntity = restTemplate. exchange(url, HttpMethod.GET, requestEntity, CoingekoApiVO	.class, 1);
            Object object = responseEntity.getBody();
            
            return object;
    }

	@Override
	public List<StoreCryptoCurrenciesVO> findCrytptoCurrenciesForUIstore(StoreQueryVO storeQueryVO) {
		
		Collection<String> apis = cryptoCurrencyStoreRepository.getCryptoCurrencysApis(storeQueryVO.getStoreUI());
		
		if(!CollectionUtils.isEmpty(apis)){
			ArrayList<StoreCryptoCurrenciesVO> cryptos = new ArrayList<>();
			apis.forEach(url -> {
				getCryptoCurrencyInfo(url); 					
			});
			
			
		}
		
		return null;
	}

	
}
