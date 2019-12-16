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
import cryptogateway.vo.request.StoreUpdateVO;
import cryptogateway.vo.response.CryptoCurrencyVO;
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
	 * Get cryptos by store
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	@PostMapping("getCryptosPayment")
    public Mono<ResponseVO> getCryptosPayment(@Valid @RequestBody StoreQueryVO storeQueryVO) {
        try {		
			return Mono.justOrEmpty(CoreUtils.responseSuccessfull(storeService.findCoinsByUIstore(storeQueryVO)));		
		}catch(Exception e) {
			log.error("Exception thown in getCryptos {}",e);			
			 return Mono.justOrEmpty(CoreUtils.responseException(e));			
		} 
    }	
	
	/**
	 * Get all cryptos by store
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	@PostMapping("getAllCryptosFromStore")
    public Mono<ResponseVO> getAllCryptos(@Valid @RequestBody StoreQueryVO storeQueryVO) {
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
	
	/**
	 * update the store
	 * 
	 * @param storeSaveVO
	 * @return
	 */
	@PostMapping("updateStore")
    public Mono<ResponseVO> updateStore(@Valid @RequestBody StoreUpdateVO storeUpdateVO) {
		try {		
			return Mono.justOrEmpty(CoreUtils.responseSuccessfull(storeService.updateStore(storeUpdateVO)));			
		}catch(Exception e) {
			
			log.error("Exception thown in updateStore {}",e);
			
			 return Mono.justOrEmpty(CoreUtils.responseException(e));			
		}        
    }
	
	/**
	 * update the store
	 * 
	 * @param storeSaveVO
	 * @return
	 */
	@PostMapping("resendPassword")
    public Mono<ResponseVO> resendPassword(@Valid @RequestBody StoreQueryVO storeQueryVO) {
		try {		
			return Mono.justOrEmpty(CoreUtils.responseSuccessfull(storeService.resendPassword(storeQueryVO)));			
		}catch(Exception e) {
			
			log.error("Exception thown in resendPassword {}",e);
			
			 return Mono.justOrEmpty(CoreUtils.responseException(e));			
		}        
    }

	
	/**
	 * update the store
	 * 
	 * @param storeSaveVO
	 * @return
	 */
	@PostMapping("saveCoinsSelected")
    public Mono<ResponseVO> saveCoinsConfiguration(@Valid @RequestBody CryptoCurrencyVO cryptoCurrencyVO) {
		try {		
			
			return Mono.justOrEmpty(CoreUtils.responseSuccessfull(storeService.saveCoinsConfiguration(cryptoCurrencyVO)));	
			
		}catch(Exception e) {
			
			log.error("Exception thown in saveCoinsSelected {}",e);
			
			 return Mono.justOrEmpty(CoreUtils.responseException(e));			
		}        
    }
}
