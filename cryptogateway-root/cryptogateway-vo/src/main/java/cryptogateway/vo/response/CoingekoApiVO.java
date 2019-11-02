package cryptogateway.vo.response;

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
public class CoingekoApiVO {
	
	String id;
	String symbol;

}
