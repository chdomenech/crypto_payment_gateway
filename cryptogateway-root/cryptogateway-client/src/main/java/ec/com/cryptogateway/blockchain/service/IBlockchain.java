package ec.com.cryptogateway.blockchain.service;

import java.util.Collection;

import cryptogateway.vo.request.TransactionsVO;
import cryptogateway.vo.response.WalletBalance;
import cryptogateway.vo.response.WalletVO;

/**
 * 
 * @author Christian Domenech
 *
 */
public interface IBlockchain {
	
	/**
	 * Create wallet
	 * 
	 * @return
	 */
	WalletVO createWallet();
	
	/**
	 * Check transaction
	 * 
	 */
	Collection<TransactionsVO> checkTransaction(Collection<TransactionsVO> transactions);
	
	
	/**
	 * Check transaction ATM
	 * 
	 */
	WalletBalance checkBalanceWalletATM(TransactionsVO transaction) throws Exception;
	
	/**
	 * Send coins to wallet
	 * 
	 */
	void sendCoins();

}
