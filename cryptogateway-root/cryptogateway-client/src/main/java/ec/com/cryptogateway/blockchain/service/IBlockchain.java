package ec.com.cryptogateway.blockchain.service;

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
	void checkTransaction();
	
	/**
	 * Send coins to wallet
	 * 
	 */
	void sendCoins();

}
