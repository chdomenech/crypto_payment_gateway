package ec.com.cryptogateway.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cryptogateway.vo.response.ResponseVO;
import ec.com.cryptogateway.utils.CryptoGatewayConstants;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, 
    		HttpStatus status, WebRequest request) {
    	
    	log.error("Exepcion generada in handleExceptionInternal {}",ex);
    	
       return buildResponseEntity(new ResponseVO(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(),
    		   CryptoGatewayConstants.INTERNAL_ERROR, ex));
	}    
    
    private ResponseEntity<Object> buildResponseEntity(ResponseVO responseVO) {
        return new ResponseEntity<>(responseVO, responseVO.getHttpStatus());
    }    
}