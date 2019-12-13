package cryptogateway.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdateVO {
	
	private String storeName;	
	private String password;	
	private String repeatPassword;	
	private String storeUID;		
	private Integer id;

}
