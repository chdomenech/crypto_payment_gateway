package ec.com.cryptogateway.service;

import java.util.Collection;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.response.ResponseVO;
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
	TransactionVO createTransactionT(StoreQueryVO storeQueryVO);
	
	/**
	 * 
	 * @param ResponseVO
	 * @return
	 */
	ResponseVO createTransaction(StoreQueryVO storeQueryVO);
	
	/**
	 * Show history of transactions
	 * 
	 * @param storeQueryVO
	 * @return
	 */
	Collection<TransactionVO> showHistory(StoreQueryVO storeQueryVO);
}
