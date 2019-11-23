package ec.cryptogateway.utils;

import java.security.SecureRandom;
import java.util.Random;

import cryptogateway.vo.response.ResponseVO;

/**
 * 
 * @author Christian
 *
 */
public class CoreUtils {
	
	private static char[] VALID_CHARACTERS =
		    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();

	/**
	 * Get response of request
	 * 
	 * @param message
	 * @param status
	 * @return
	 */
	public static ResponseVO getResponseVO(String message, Integer status) {
		ResponseVO responseVO = new ResponseVO();
		responseVO.setMessage(message);
		responseVO.setStatus(status);
		return  responseVO;
	}

	/**
	 * Create a ramdon identifier
	 * 
	 * @param numChars
	 * @return
	 */
	public static String createIdentifierRandom(int numChars) {
	    SecureRandom srand = new SecureRandom();
	    Random rand = new Random();
	    char[] buff = new char[numChars];

	    for (int i = 0; i < numChars; ++i) {
	      if ((i % 10) == 0) {
	          rand.setSeed(srand.nextLong());
	      }
	      buff[i] = VALID_CHARACTERS[rand.nextInt(VALID_CHARACTERS.length)];
	    }
	    return new String(buff);
	}
}
