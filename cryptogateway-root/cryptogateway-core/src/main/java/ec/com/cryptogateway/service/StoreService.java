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
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.CoingeckoApiVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import ec.com.cryptogateway.entity.StoreEntity;
import ec.com.cryptogateway.repository.ICryptoCurrencyStoreRepository;
import ec.com.cryptogateway.repository.IStoreRepository;
import ec.com.cryptogateway.repository.vo.CryptoCurrencyVO;
import lombok.extern.slf4j.Slf4j;

/**
 * Store Service
 * 
 * @author Christian
 *
 */
@Slf4j
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
    
    /**
     * Rest template for coingecko
     * 
     * @param urlWebService
     * @return
     */
    public CoingeckoApiVO getCryptoCurrencyInfo(String urlWebService) {    	
    		try{
    			HttpHeaders headers = new HttpHeaders();
    		
    			headers.setContentType(MediaType.APPLICATION_JSON);
    			RestTemplate restTemplate = new RestTemplate();
    			String url = urlWebService;
    			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
    			ResponseEntity<CoingeckoApiVO> responseEntity = restTemplate. exchange(url, HttpMethod.GET, requestEntity, CoingeckoApiVO.class, 1);
    			CoingeckoApiVO object = responseEntity.getBody();
            
    			return object;
    		}catch (Exception e) {
    			log.error(e.getCause().getMessage());
				throw e;				
			}	
    }

    /**
     * Find all information of the cryptoCurrency
     * 
     */
	@Override
	public List<StoreCryptoCurrenciesVO> findCrytptoCurrenciesForUIstore(StoreQueryVO storeQueryVO) {

		Collection<CryptoCurrencyVO> apis = null;
		
		if(StringUtils.isEmpty(storeQueryVO.getStoreUI())) {
			apis = cryptoCurrencyStoreRepository.getCryptoCurrencysApis(storeQueryVO.getStoreUI());	
		}else {
			apis = cryptoCurrencyStoreRepository.getCryptoCurrencysApisByCoinID(storeQueryVO.getStoreUI(),storeQueryVO.getCoinId());			
		}
		 	
		if(!CollectionUtils.isEmpty(apis)){
			ArrayList<StoreCryptoCurrenciesVO> cryptos = new ArrayList<>();
			apis.forEach(data -> {
				CoingeckoApiVO coingeckoApiVO= getCryptoCurrencyInfo(data.getApi1()); 
				if(coingeckoApiVO!=null) {
					coinGeckoApiInformation(coingeckoApiVO, data, storeQueryVO, cryptos);
				}
			});	
			
			return cryptos;
		}
		
		return null;
	}
	
	/**
	 * Method for return the coingecko api information
	 * 
	 * @param coingeckoApiVO
	 * @param data
	 * @param storeQueryVO
	 * @param cryptos
	 */
	public void coinGeckoApiInformation(CoingeckoApiVO coingeckoApiVO, CryptoCurrencyVO data, StoreQueryVO storeQueryVO, 
			ArrayList<StoreCryptoCurrenciesVO> cryptos ) {
		
		StoreCryptoCurrenciesVO storeCryptoCurrencysVO = new StoreCryptoCurrenciesVO();
		storeCryptoCurrencysVO.setCryptoCurrencyName(coingeckoApiVO.getName());
		storeCryptoCurrencysVO.setCoinId(data.getCoinId());
		storeCryptoCurrencysVO.setIdCoin(data.getIdCoin());
		storeCryptoCurrencysVO.setCryptoCurrencyLogo(coingeckoApiVO.getImage().get("thumb"));
		storeCryptoCurrencysVO.setTotalPayment(storeQueryVO.getTotalPayment());
		storeCryptoCurrencysVO.setBlockchain(data.getBlockchain());
		storeCryptoCurrencysVO.setIdStore(data.getIdStore());
		
		if(coingeckoApiVO.getMarketData()!=null) {			
			
			@SuppressWarnings("unchecked")
			LinkedHashMap<String, Double> marketPrice = 
			(LinkedHashMap<String, Double>) coingeckoApiVO.getMarketData().get("current_price");
			
			Double priceUSD = marketPrice.get("usd");
			BigDecimal priceCoin = new BigDecimal(priceUSD).setScale(2, RoundingMode.HALF_DOWN);						
			storeCryptoCurrencysVO.setCryptoCurrencyPrice(priceCoin);
			
			BigDecimal total = storeQueryVO.getTotalPayment().divide(priceCoin, 4, RoundingMode.HALF_DOWN);
			storeCryptoCurrencysVO.setCryptoCurrencyConversion(total);
		}
		cryptos.add(storeCryptoCurrencysVO);
		
	}

	
}
