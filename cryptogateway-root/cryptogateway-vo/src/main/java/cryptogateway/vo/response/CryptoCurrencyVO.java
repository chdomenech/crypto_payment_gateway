package cryptogateway.vo.response;

import java.util.Collection;

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
public class CryptoCurrencyVO {
	
	private String coinId;
	private String apiUrl;
	private String apiUrl1;
	private String apiUrl2;
	private Integer idCoin;
	private String smartContract;
	private Integer idStore;
	private Integer blockchainId;
	private String blockchainName;
	private String javaClass;
	private Integer timeoutMinuts;
	private Collection<String> coins;
}
