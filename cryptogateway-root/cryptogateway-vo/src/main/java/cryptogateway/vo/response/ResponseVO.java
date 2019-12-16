package cryptogateway.vo.response;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @author Christian Domenech
 *
 */
@Data
@AllArgsConstructor
public class ResponseVO {
	
	private HttpStatus httpStatus;
	private Integer status;
	private Integer internalStatus;
	private String message;
	private String debugMessage;	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
	private Collection<String> errorsList;
	private Collection<String> warningList;		
	private Collection<?> listObjects;	
	private Object object;
	
	private ResponseVO() {
        timestamp = LocalDateTime.now();
    }

	public ResponseVO(HttpStatus httpStatus) {
        this();
        this.httpStatus = httpStatus;
    }

    public ResponseVO(HttpStatus httpStatus, Throwable ex) {
        this();
        this.httpStatus = httpStatus;
        this.message = "Unexpected error";
    }

    public ResponseVO(HttpStatus httpStatus, Integer status, String message, Throwable ex) {
        this();
        this.httpStatus = httpStatus;
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

	public ResponseVO(Integer internalStatus, String message,
			Collection<String> errorsList, Collection<String> warningList) {
		super();
		this.internalStatus = internalStatus;
		this.message = message;
		this.errorsList = errorsList;
		this.warningList = warningList;
	}

	public ResponseVO(Integer internalStatus, String message) {
		super();
		this.internalStatus = internalStatus;
		this.message = message;
	}
    
	
	public ResponseVO(Integer internalStatus, String message, Throwable ex) {
		super();
		this.internalStatus = internalStatus;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	public ResponseVO(Integer internalStatus, String message,Collection<?> listObjects) {
		super();
		this.internalStatus = internalStatus;
		this.message = message;
		this.listObjects = listObjects;
	}

	public ResponseVO(Integer internalStatus, String message, Object object) {
		super();
		this.internalStatus = internalStatus;
		this.message = message;
		this.object = object;
	}
	 
    
}
