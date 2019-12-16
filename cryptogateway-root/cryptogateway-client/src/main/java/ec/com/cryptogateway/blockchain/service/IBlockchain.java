package ec.com.cryptogateway.blockchain.service;

import java.util.Collection;

import cryptogateway.vo.request.TransactionsVO;
import cryptogateway.vo.response.WalletVO;

/**
 * 
 * @author Christian
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
	Collection<TransactionsVO> checkBalanceWalletATM(Collection<TransactionsVO> transactions);
	
	/**
	 * Send coins to wallet
	 * 
	 */
	void sendCoins();

}
