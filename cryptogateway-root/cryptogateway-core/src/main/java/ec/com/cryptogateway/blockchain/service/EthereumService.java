package ec.com.cryptogateway.blockchain.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Convert.Unit;
import org.web3j.utils.Numeric;

import cryptogateway.vo.request.TransactionsVO;
import cryptogateway.vo.response.WalletVO;
import ec.com.cryptogateway.service.ITransactionService;
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

	@Autowired
	ITransactionService transactionService;
	
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


	/**
	 * checkTransaction
	 * 
	 */
	public void checkTransaction(Collection<TransactionsVO> transactions) {
		 Web3j web3 = Web3j.build(new HttpService(CryptoGatewayConstants.URL_INFURA_API_ETHEREUM));
		 log.debug("Successfuly connected to Ethereum");
		
		transactions.forEach(data->{
			
			BigDecimal balance= BigDecimal.ZERO;
			
			try {
				
				if(StringUtils.isEmpty(data.getSmartContract())) {					
					 balance = getBalanceEther(web3, data.getWallet());
				}else {
					balance = getBalanceTokens(web3, data);				
				}
				
				data.setWalletBalance(balance);
				
				transactionService.updateTransaction(data);
				
			} catch (IOException e) {
				 log.debug("Exception IOException");
			} catch (Exception e) {
				 log.debug("Exception");
			}
			
		});
	}

	/**
	 * Get Balance ether
	 * 
	 * @param web3
	 * @param wallet
	 * @return
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private BigDecimal getBalanceEther(Web3j web3, String wallet) throws IOException, InterruptedException, ExecutionException {
		EthGetBalance balanceWei = web3.ethGetBalance(wallet, DefaultBlockParameterName.LATEST)
				.sendAsync()
                .get();
		return Convert.fromWei(balanceWei.getBalance().toString(), Unit.ETHER);
	}

	/**
	 * Get Balance Tokens Ethereum
	 * 
	 * @param web3
	 * @param wallet
	 * @return
	 * @throws Exception 
	 */
	private BigDecimal getBalanceTokens(Web3j web3, TransactionsVO data) throws Exception {
		
		Credentials credentials = Credentials.create(data.getPrivateKey(), data.getPublicKey());
		ERC20 javaToken = ERC20.load(data.getSmartContract(), web3, credentials, new DefaultGasProvider());		
		
		BigInteger balance1 = javaToken.balanceOf(data.getWallet()).sendAsync().get();
		
		BigDecimal balance = new BigDecimal(balance1);
		
		return Convert.fromWei(balance.toString(), Unit.ETHER);
	}

	
	/**
	 * 
	 * Send Coins
	 */
	@Override
	public void sendCoins() {
		// TODO Auto-generated method stub
		
	}

}
