package cryptogateway.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Christian
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreSaveVO {
	
	/* Campos not null */
	private String email;
	private String storeName;
	private String password;
	private String user;	
}
