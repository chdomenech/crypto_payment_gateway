package cryptogateway.vo.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionATMVO {

	@NotBlank
	private String wallet;
	
	@NotBlank
	private String publicKey;

	@NotBlank
	private Integer atmPassword;
	
	@NotBlank
	private String storeUI;

	@NotBlank
	private BigDecimal amount;

	@NotBlank
	private String coindId;
	
	
}
