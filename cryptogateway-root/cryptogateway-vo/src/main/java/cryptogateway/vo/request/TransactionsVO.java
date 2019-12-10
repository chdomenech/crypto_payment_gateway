package cryptogateway.vo.request;

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
public class TransactionsVO {
    private Integer blockchainId;
    private String wallet;
    private String smartContract;
    private String privateKey;
    private String publicKey;
    private BigDecimal coinsAmount;
    private Integer transactionId;
    private String email;
    private Integer storeId;
    private Date timeoutTransaction;
    private Integer numberOfChecks;    
    private BigDecimal walletBalance;
}
