package ec.com.cryptogateway.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cryptogateway.vo.request.CredentialsVO;
import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.request.StoreSaveVO;
import cryptogateway.vo.response.ResponseVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import ec.com.cryptogateway.service.IStoreService;
import reactor.core.publisher.Mono;

/**
 * Store Controller
 *
 * @author Christian Domenech
 *
 */
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
    public Mono<List<StoreCryptoCurrenciesVO>> getCryptos(@RequestBody StoreQueryVO storeQueryVO) {
        return Mono.justOrEmpty(storeService.findCoinsForUIstore(storeQueryVO));
    }	
	
	/**
	 * find User by credentials
	 * 
	 * @param credentialsVO
	 * @return
	 */
	@PostMapping("authenticate")
    public Mono<ResponseVO> findUserByCredentials(@RequestBody CredentialsVO credentialsVO) {
        return Mono.justOrEmpty(storeService.findUserByCredentials(credentialsVO));
    }
	
	/**
	 * Save the store
	 * 
	 * @param storeSaveVO
	 * @return
	 */
	@PostMapping("saveStore")
    public Mono<ResponseVO> saveStore(@RequestBody StoreSaveVO storeSaveVO) {
        return Mono.justOrEmpty(storeService.saveStore(storeSaveVO));
    }

}
