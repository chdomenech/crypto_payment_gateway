package ec.com.cryptogateway.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import ec.com.cryptogateway.entity.StoreEntity;
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
	 
	@GetMapping("getStoreById/{storeId}")
    public Mono<StoreEntity> getById(@PathVariable Integer storeId) {
        return Mono.justOrEmpty(storeService.findStoreByID(storeId));
    }

	@PostMapping("getCryptosPayment")
    public Mono<List<StoreCryptoCurrenciesVO>>  getCryptos(@RequestBody StoreQueryVO storeQueryVO) {
        return Mono.justOrEmpty(storeService.findCrytptoCurrenciesForUIstore(storeQueryVO));
    }	
}
