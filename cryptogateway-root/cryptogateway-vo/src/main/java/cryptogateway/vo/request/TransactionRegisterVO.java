package cryptogateway.vo.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRegisterVO {	
	
	private String storeUI;
	private BigDecimal totalPayment;
	private String coindId;
	private BigDecimal tokensAmount;	

}
