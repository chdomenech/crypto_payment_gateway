package ec.com.cryptogateway.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 
	@GetMapping("getStore/{storeId}")
    public Mono<StoreEntity> getById(@PathVariable Integer storeId) {
        return Mono.justOrEmpty(storeService.finById(storeId));
    }

	@GetMapping("count")
    public Mono<Long> count() {
        return Mono.justOrEmpty(storeService.count());
    }
	
	@GetMapping("all")
    public Mono<Iterable<StoreEntity>> all() {
        return Mono.justOrEmpty(storeService.findAll());
    }
}
