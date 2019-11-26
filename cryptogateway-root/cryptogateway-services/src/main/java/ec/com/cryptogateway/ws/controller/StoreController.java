package ec.com.cryptogateway.ws.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.request.StoreSaveVO;
import cryptogateway.vo.response.ResponseVO;
import ec.com.cryptogateway.service.IStoreService;
import ec.cryptogateway.utils.CoreUtils;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Store Controller
 *
 * @author Christian Domenech
 *
 */
@Slf4j
@RequestMapping("store")
@RestController
public class StoreController {

	@Autowired
	private final IStoreService storeService;
	
	 public StoreController(IStoreService storeService) {
	        this.storeService = storeService;
	 }
	 
	/**
	 * Get all cryptos by store
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	@PostMapping("getCryptosPayment")
    public Mono<ResponseVO> getCryptos(@Valid @RequestBody StoreQueryVO storeQueryVO) {
        try {		
			return Mono.justOrEmpty(CoreUtils.responseSuccessfull(storeService.findAllCoinsForUIstore(storeQueryVO)));		
		}catch(Exception e) {
			log.error("Exception thown in getCryptos {}",e);			
			 return Mono.justOrEmpty(CoreUtils.responseException(e));			
		} 
    }	
	
	/**
	 * find User by credentials
	 * 
	 * @param credentialsVO
	 * @return
	 */
	@PostMapping("authenticate")
    public Mono<ResponseVO> findUserByCredentials(@Valid @RequestBody CredentialsVO credentialsVO) {
		
		try {		
			return Mono.justOrEmpty(CoreUtils.responseSuccessfull(storeService.findUserByCredentials(credentialsVO)));		
		}catch(Exception e) {
			log.error("Exception thown in findUserByCredentials {}",e);			
			 return Mono.justOrEmpty(CoreUtils.responseException(e));			
		}        
    }
	
	/**
	 * Save the store
	 * 
	 * @param storeSaveVO
	 * @return
	 */
	@PostMapping("saveStore")
    public Mono<ResponseVO> saveStore(@Valid @RequestBody StoreSaveVO storeSaveVO) {
		try {		
			return Mono.justOrEmpty(CoreUtils.responseSuccessfull(storeService.saveStore(storeSaveVO)));			
		}catch(Exception e) {
			
			log.error("Exception thown in saveStore {}",e);
			
			 return Mono.justOrEmpty(CoreUtils.responseException(e));			
		}        
    }

}
