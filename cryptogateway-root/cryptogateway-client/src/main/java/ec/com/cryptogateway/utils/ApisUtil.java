package ec.com.cryptogateway.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.CoingeckoApiVO;
import cryptogateway.vo.response.CryptoCurrencyVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApisUtil {

    /**
     * Rest template for coingecko
     * 
     * @param urlWebService
     * @return
     */
    public static CoingeckoApiVO getCryptoCurrencyInfo(String urlWebService) {
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
	 * Method for return the coingecko api information
	 * 
	 * @param coingeckoApiVO
	 * @param data
	 * @param storeQueryVO
	 * @param cryptos
	 */
	public static void getApiInformation(CryptoCurrencyVO data, StoreQueryVO storeQueryVO, 
			ArrayList<StoreCryptoCurrenciesVO> cryptos) {
		
		CoingeckoApiVO coingeckoApiVO= ApisUtil.getCryptoCurrencyInfo(data.getApiUrl()); 
		if(coingeckoApiVO!=null) {
			 coinGeckoApiInformation(coingeckoApiVO, data, storeQueryVO, cryptos);
		}
	}
    
    
    /**
	 * Method for return the coingecko api information
	 * 
	 * @param coingeckoApiVO
	 * @param data
	 * @param storeQueryVO
	 * @param cryptos
	 */
	public static void coinGeckoApiInformation(CoingeckoApiVO coingeckoApiVO, CryptoCurrencyVO data, StoreQueryVO storeQueryVO, 
			ArrayList<StoreCryptoCurrenciesVO> cryptos) {
		
		StoreCryptoCurrenciesVO storeCryptoCurrencysVO = new StoreCryptoCurrenciesVO();
		storeCryptoCurrencysVO.setCryptoCurrencyName(coingeckoApiVO.getName());
		storeCryptoCurrencysVO.setCoinId(data.getCoinId());
		storeCryptoCurrencysVO.setIdCoin(data.getIdCoin());
		storeCryptoCurrencysVO.setCryptoCurrencyLogo(coingeckoApiVO.getImage().get("thumb"));
		storeCryptoCurrencysVO.setTotalPayment(storeQueryVO.getTotalPayment());
		storeCryptoCurrencysVO.setBlockchainId(data.getBlockchainId());
		storeCryptoCurrencysVO.setBlockchainName(data.getBlockchainName());
		storeCryptoCurrencysVO.setJavaClass(data.getJavaClass());
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
