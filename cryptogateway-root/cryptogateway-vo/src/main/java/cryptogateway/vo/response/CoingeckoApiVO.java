package cryptogateway.vo.response;

import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Christian Domenech
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoingeckoApiVO {
	
	private String id;
	private String symbol;
	private String name;
	private LinkedHashMap<String, String> image;	
	
	@JsonProperty("market_data")
	private LinkedHashMap<Object, Object> marketData;
}
