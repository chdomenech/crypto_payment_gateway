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
	void checkTransaction(Collection<TransactionsVO> transactions);
	
	/**
	 * Send coins to wallet
	 * 
	 */
	void sendCoins();

}
