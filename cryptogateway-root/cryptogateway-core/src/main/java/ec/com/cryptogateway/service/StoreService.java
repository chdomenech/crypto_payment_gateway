package ec.com.cryptogateway.service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
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
import cryptogateway.vo.response.CoingeckoApiVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import ec.com.cryptogateway.entity.StoreEntity;
import ec.com.cryptogateway.repository.ICryptoCurrencyStoreRepository;
import ec.com.cryptogateway.repository.IStoreRepository;
import ec.com.cryptogateway.repository.vo.CryptoCurrencyVO;

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
    
    public CoingeckoApiVO getCryptoCurrencyInfo(String urlWebService) {
    		HttpHeaders headers = new HttpHeaders();
        	headers.setContentType(MediaType.APPLICATION_JSON);
            RestTemplate restTemplate = new RestTemplate();
            String url = urlWebService;
            HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
            ResponseEntity<CoingeckoApiVO> responseEntity = restTemplate. exchange(url, HttpMethod.GET, requestEntity, CoingeckoApiVO.class, 1);
            CoingeckoApiVO object = responseEntity.getBody();
            
            return object;
    }

	@Override
	public List<StoreCryptoCurrenciesVO> findCrytptoCurrenciesForUIstore(StoreQueryVO storeQueryVO) {

		Collection<CryptoCurrencyVO> apis = cryptoCurrencyStoreRepository.getCryptoCurrencysApis(storeQueryVO.getStoreUI());
		
		BigDecimal totalPrice = storeQueryVO.getTotalPayment();
		
		if(!CollectionUtils.isEmpty(apis)){
			ArrayList<StoreCryptoCurrenciesVO> cryptos = new ArrayList<>();
			apis.forEach(data -> {
				CoingeckoApiVO coingeckoApiVO= getCryptoCurrencyInfo(data.getApi1()); 	
				if(coingeckoApiVO!=null) {					
					StoreCryptoCurrenciesVO storeCryptoCurrencysVO = new StoreCryptoCurrenciesVO();
					storeCryptoCurrencysVO.setCryptoCurrencyName(coingeckoApiVO.getName());
					storeCryptoCurrencysVO.setCoindId(data.getCoinId());
					storeCryptoCurrencysVO.setId(data.getId());
					storeCryptoCurrencysVO.setCryptoCurrencyLogo(coingeckoApiVO.getImage().get("thumb"));
					storeCryptoCurrencysVO.setTotalPayment(totalPrice);
					
					if(coingeckoApiVO.getMarketData()!=null) {			
						
						@SuppressWarnings("unchecked")
						LinkedHashMap<String, Double> marketPrice = 
						(LinkedHashMap<String, Double>) coingeckoApiVO.getMarketData().get("current_price");
						
						Double priceUSD = marketPrice.get("usd");
						BigDecimal priceCoin = new BigDecimal(priceUSD).setScale(2, RoundingMode.HALF_DOWN);						
						storeCryptoCurrencysVO.setCryptoCurrencyPrice(priceCoin);
						
						BigDecimal total = totalPrice.divide(priceCoin, 4, RoundingMode.HALF_DOWN);
						storeCryptoCurrencysVO.setCryptoCurrencyConversion(total);
					}
					cryptos.add(storeCryptoCurrencysVO);
				}
			});	
			
			return cryptos;
		}
		
		return null;
	}

	
}
