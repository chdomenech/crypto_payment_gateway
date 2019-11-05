package ec.com.cryptogateway.service;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.TransactionVO;

/**
 * TransactionService Service
 * 
 * @author Christian Domenech
 *
 */
public interface ITransactionService {
	
	/**
	 * 
	 * @param transactionVO
	 * @return
	 */
	TransactionVO saveTransaction(StoreQueryVO storeQueryVO);

}
