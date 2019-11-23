package ec.cryptogateway.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import cryptogateway.vo.response.StoreCryptoCurrenciesVO;
import cryptogateway.vo.response.WalletVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Christian
 *
 */
@Slf4j
public class WalletUtil {

	/**
	 * Generate a Wallet with private key
	 * it depend of type blockchain 
	 * 
	 * @param dataTransaction
	 * @return
	 */
	public static WalletVO generateWallet(StoreCryptoCurrenciesVO dataTransaction) {
		WalletVO walletVO = null;
		String classPackage = "ec.com.cryptogateway.blockchain.service.";
		classPackage= classPackage.concat(dataTransaction.getJavaClass());
		try {
			Class<?> clase = Class.forName(classPackage);
			Constructor<?> cons1 = clase.getConstructor();
			Method method = clase.getDeclaredMethod("createWallet");
			method.setAccessible(true);
			walletVO = (WalletVO) method.invoke(cons1.newInstance());
		}catch (Exception e) {
			log.error("Error al generar la wallet {}", e.getMessage());
		}
		return walletVO;		
	}
}
