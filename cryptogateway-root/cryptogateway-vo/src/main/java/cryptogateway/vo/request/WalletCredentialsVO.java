package cryptogateway.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletCredentialsVO {
	
	private String wallet;
	private String publicKey;
	private Integer atmPassword;
	private String walletType;

}
