package cryptogateway.vo.response;

import java.math.BigDecimal;

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
public class WalletBalance {
	
	private BigDecimal balance;
	private BigDecimal balanceTokens;
	private BigDecimal fee;

}
