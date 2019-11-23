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
public class StoreQueryVO {
	
	private String storeUI;
	private BigDecimal totalPayment;
	private String coinId;
	private Integer id;
	private String email;
	private String storeName;
	private String password;
	private String user;
}
