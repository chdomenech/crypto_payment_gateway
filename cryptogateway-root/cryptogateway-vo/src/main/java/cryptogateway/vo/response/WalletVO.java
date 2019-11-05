package cryptogateway.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WalletVO {
	
	private String walletAddress;
	private String privateKey;
	private String blockchain;
}
