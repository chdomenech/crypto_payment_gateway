package ec.com.cryptogateway.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cryptogateway.vo.response.CoingeckoApiVO;
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

	
}
