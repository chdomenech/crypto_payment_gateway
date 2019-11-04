package cryptogateway.vo.response;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Christian
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoingeckoApiVO {
	
	String id;
	String symbol;
	String name;
	LinkedHashMap<String, String> image;	
	
	@JsonProperty("market_data")
	LinkedHashMap<Object, Object> marketData;
}
