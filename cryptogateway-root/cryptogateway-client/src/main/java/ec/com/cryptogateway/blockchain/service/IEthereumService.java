package ec.com.cryptogateway.blockchain.service;

import cryptogateway.vo.response.WalletVO;

/**
 * Interface Ethereum Service
 * 
 * @author Christian
 *
 */
public interface IEthereumService {
	
	/**
	 * Generate Ethereum Wallet with private key
	 * 
	 * @return
	 */
	WalletVO generateWalletEthereum();
}
