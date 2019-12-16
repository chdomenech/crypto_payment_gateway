package ec.com.cryptogateway.blockchain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cryptogateway.vo.request.TransactionsVO;
import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.blockchain.service.IBitcoinService;

/**
 * 
 * @author Christian Domenech
 *
 */
@Service
public class BitcoinService implements IBitcoinService{

	@Override
	public WalletVO createWallet() {
		return null;
	}

	@Override
	public void sendCoins() {
		
	}

	@Override
	public Collection<TransactionsVO> checkTransaction(Collection<TransactionsVO> transactions) {
		return null;
	}

	

}
