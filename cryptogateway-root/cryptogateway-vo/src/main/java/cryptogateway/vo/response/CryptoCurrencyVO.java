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
public class CryptoCurrencyVO {
	
	String coinId;
	String apiUrl;
	String apiUrl1;
	String apiUrl2;
	Integer idCoin;
	Integer idStore;
	Integer blockchainId;
	String blockchainName;
	String javaClass;
}
