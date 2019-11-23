package cryptogateway.vo.response;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO {
	
	private String message;
	private Integer status;
	
	private Collection<String> errorsList;
	private Collection<String> warningList;
}
