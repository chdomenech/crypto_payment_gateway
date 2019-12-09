package ec.com.cryptogateway.service;

import java.util.Collection;

import cryptogateway.vo.request.StoreQueryVO;
import cryptogateway.vo.request.TransactionsVO;
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
	
	/**
	 * update the transaction
	 * 
	 * @param transactionsVO
	 */
	void updateTransaction(TransactionsVO transactionsVO);
	
	/**
	 * Check transaction 
	 */
	void checkTransaction();
}
