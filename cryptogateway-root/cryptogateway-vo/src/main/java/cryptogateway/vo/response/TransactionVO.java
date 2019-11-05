package cryptogateway.vo.response;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVO {
 
	private Integer id;	
	private String transactionId;
	private String walletAddress;
	private Date creationTime;
	private String qrCode;
	private BigDecimal coinsAmount;
	private String coinId;
	private String coinName;
	private String coinLogo;
}
