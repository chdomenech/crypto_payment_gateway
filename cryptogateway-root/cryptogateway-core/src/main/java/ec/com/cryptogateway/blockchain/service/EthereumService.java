package ec.com.cryptogateway.blockchain.service;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.utils.CryptoGatewayConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * Ethereum Service 
 * 
 * @author Christian
 *
 */
@Service
@Slf4j
public class EthereumService implements IEthereumService{

	/**
	 * Generate an Ethereum Wallet
	 * 
	 */
	@Override
	public WalletVO createWallet() {

		WalletVO walletVO = null;
		
		ECKeyPair keyPair;
		try {
			keyPair = Keys.createEcKeyPair();
			BigInteger publicKey = keyPair.getPublicKey();
			String publicKeyHex = Numeric.toHexStringWithPrefix(publicKey);
			
			BigInteger privateKey = keyPair.getPrivateKey();
			String privateKeyHex = Numeric.toHexStringWithPrefix(privateKey);
			
			Credentials credentials = Credentials.create(new ECKeyPair(privateKey, publicKey));
			String address = credentials.getAddress();
			
			walletVO = new WalletVO();
			walletVO.setPrivateKey(privateKeyHex);
			walletVO.setWalletAddress(address);
			walletVO.setBlockchainId(CryptoGatewayConstants.ETHEREUM_BLOCKCHAIN);
			walletVO.setPublicKey(publicKeyHex);
			
		} catch (InvalidAlgorithmParameterException e) {
			log.error("Error InvalidAlgorithmParameterException {}",e.getMessage());			
		} catch (NoSuchAlgorithmException e) {
			log.error("Error NoSuchAlgorithmException {}",e.getMessage());			
		} catch (NoSuchProviderException e) {
			log.error("Error NoSuchProviderException {}",e.getMessage());			
		}
		
		return walletVO;
	}



	@Override
	public void checkTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendCoins() {
		// TODO Auto-generated method stub
		
	}

}
