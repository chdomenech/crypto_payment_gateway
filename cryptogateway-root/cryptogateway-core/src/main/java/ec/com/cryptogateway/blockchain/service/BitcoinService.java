package ec.com.cryptogateway.blockchain.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import cryptogateway.vo.request.TransactionsVO;
import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.blockchain.service.IBitcoinService;

/**
 * 
 * @author Christian
 *
 */
@Service
public class BitcoinService implements IBitcoinService{

	@Override
	public WalletVO createWallet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendCoins() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkTransaction(Collection<TransactionsVO> transactions) {
		// TODO Auto-generated method stub
		
	}

	

}
