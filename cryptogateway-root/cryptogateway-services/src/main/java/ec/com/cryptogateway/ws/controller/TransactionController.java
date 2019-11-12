package ec.com.cryptogateway.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.TransactionVO;
import ec.com.cryptogateway.service.ITransactionService;
import reactor.core.publisher.Mono;

/**
 * Transaction Controller
 *
 * @author Christian Domenech
 *
 */
@RequestMapping(value="transaction")
@RestController
public class TransactionController {

	@Autowired
	private final ITransactionService transactionService;
    
	 public TransactionController(ITransactionService transactionService) {
	        this.transactionService = transactionService;
	 }
	 
	 @PostMapping("createTransaction")
	    public Mono<TransactionVO>  getCryptos(@RequestBody StoreQueryVO storeQueryVO) {
	        return Mono.justOrEmpty(transactionService.createTransaction(storeQueryVO));
	    }	
}
