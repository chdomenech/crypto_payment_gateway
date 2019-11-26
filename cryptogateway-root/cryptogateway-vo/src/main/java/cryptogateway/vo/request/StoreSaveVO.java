package cryptogateway.vo.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	

	@Email
	@NotNull
	private String email;
	
	@NotNull
	@Size(min= 5, max= 30)
	private String storeName;
	
	@NotNull
	@Size(min= 8, max = 12)
	private String password;
	
	@NotNull
	@Size(min= 8, max = 12)
	private String repeatPassword;
	
	@NotNull
	@Size(min= 6, max = 10)
	private String user;	
}
