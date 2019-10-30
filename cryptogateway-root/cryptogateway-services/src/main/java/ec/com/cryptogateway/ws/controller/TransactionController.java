package ec.com.cryptogateway.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.cryptogateway.entity.TransactionEntity;
import ec.com.cryptogateway.service.ITransactionService;
import reactor.core.publisher.Mono;

/**
 * Transaction Controller
 *
 * @author Christian Domenech
 *
 */
@RequestMapping("transaction")
@RestController
public class TransactionController {

    @Autowired
    private final ITransactionService transactionService;

    public TransactionController(ITransactionService transactionService) {
        super();
        this.transactionService = transactionService;
    }    
    
    @GetMapping("getAll")
    public Mono<List<TransactionEntity>> getAll() {
        return Mono.justOrEmpty(transactionService.encuentratodo());
    }
}
