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
@Slf4j
@Service
public class EthereumService implements IEthereumService{

	/**
	 * Generate an Ethereum Wallet
	 * 
	 */
	@Override
	public WalletVO generateWalletEthereum() {
		
		WalletVO walletVO = null;
		
		ECKeyPair keyPair;
		try {
			keyPair = Keys.createEcKeyPair();
			BigInteger publicKey = keyPair.getPublicKey();
			String publicKeyHex = Numeric.toHexStringWithPrefix(publicKey);
			
			BigInteger privateKey = keyPair.getPrivateKey();
			String privateKeyHex = Numeric.toHexStringWithPrefix(privateKey);
			
			// create credentials + address from private/public key pair
			Credentials credentials = Credentials.create(new ECKeyPair(privateKey, publicKey));
			String address = credentials.getAddress();
			
			walletVO = new WalletVO();
			walletVO.setPrivateKey(privateKeyHex);
			walletVO.setWalletAddress(address);
			walletVO.setBlockchain(CryptoGatewayConstants.ETHEREUM_BLOCKCHAIN);
			walletVO.setPublicKey(publicKeyHex);
			
		} catch (InvalidAlgorithmParameterException e) {
			log.error(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			log.error(e.getMessage());
		} catch (NoSuchProviderException e) {
			log.error(e.getMessage());
		}
		
		return walletVO;
	}

}
