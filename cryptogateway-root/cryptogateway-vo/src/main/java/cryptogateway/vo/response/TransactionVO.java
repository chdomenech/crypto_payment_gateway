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
	private Integer walletId;
	private Date creationTime;
	private String qrCode;
	private Integer cryptoCurrencyId;
	private Integer storeId;
    private Integer transactionStatusId;
	private BigDecimal coinsAmount;
	private BigDecimal coinPrice;
	private BigDecimal totalPayment;

}
