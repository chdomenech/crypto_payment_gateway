package ec.cryptogateway.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;

import cryptogateway.vo.response.ResponseVO;
import ec.com.cryptogateway.utils.CryptoGatewayConstants;

/**
 * 
 * @author Christian
 *
 */
public class CoreUtils {
	
	private static final char[] VALID_CHARACTERS =
		    "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456879".toCharArray();
	
	private static final String EMAIL_REGEX = "^(.+)@(.+)$";
	private static final String USER_REGEX = "^[a-zA-Z0-9]{6,10}$";
	private static final String NAME_STORE_REGEX = "^[a-zA-Z0-9]{5,30}$";
	private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

	
	/**
	 * Send response suscessfull
	 * 
	 * @param responseVO
	 * @return
	 */
	public static ResponseVO responseSuccessfull(ResponseVO responseVO) {
		ResponseVO response = responseVO;
		response.setHttpStatus(HttpStatus.OK);
		response.setStatus(HttpStatus.OK.value());	
		return response;
	}
	
	/**
	 * Send response with error
	 * 
	 * @param responseVO
	 * @return
	 */
	public static ResponseVO responseException(Exception ex) {
		
		return new ResponseVO(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(),
				CryptoGatewayConstants.INTERNAL_ERROR, ex);
	}



	/**
	 * Validate Pattern
	 * 
	 * @param email
	 * @return
	 */
	private static boolean validatePattern(String email, String regex) {
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	/**
	 * Validate an email
	 * 
	 * @param email
	 * @return
	 */
	public static boolean validateEmail(String email) {		
		return validatePattern(email, EMAIL_REGEX);
	}
	
	/**
	 * Validate an user
	 * 
	 * @param user
	 * @return
	 */
	public static boolean validateUser(String user) {		
		return validatePattern(user, USER_REGEX);
	}
	
	/**
	 * Validate store name
	 * 
	 * @param nameStore
	 * @return
	 */
	public static boolean validateStoreName(String nameStore) {		
		return validatePattern(nameStore, NAME_STORE_REGEX);
	}
	
	/**
	 * Validate password
	 * 
	 * @param password
	 * @return
	 */
	public static boolean validatePassword(String password) {	
		/*
	    	At least 8 chars
	    	Contains at least one digit
	    	Contains at least one lower alpha char and one upper alpha char
	    	Contains at least one char within a set of special chars (@#%$^ etc.)
	    	Does not contain space, tab, etc.
		 */
		return validatePattern(password, PASSWORD_REGEX);
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
