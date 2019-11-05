package cryptogateway.vo.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Send information about cryptocurrency 
 * accept for the store
 * 
 * @author Christian
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreCryptoCurrenciesVO {

	private String cryptoCurrencyLogo;
	private String cryptoCurrencyName;
	private BigDecimal cryptoCurrencyPrice;
	private BigDecimal cryptoCurrencyConversion;
	private BigDecimal totalPayment;	
	private String coinId;
	private String blockchain;
	private Integer idCoin;
	private Integer idStore;
}
