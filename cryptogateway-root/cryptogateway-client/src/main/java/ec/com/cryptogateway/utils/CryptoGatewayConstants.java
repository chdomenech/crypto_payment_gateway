package ec.com.cryptogateway.utils;

public class CryptoGatewayConstants {
	
	public static final Integer ETHEREUM_BLOCKCHAIN =1;
	public static final Integer BITCOIN_BLOCKCHAIN =2;

	public static final Integer STATUS_TRANSACTION_WAITING = 1;
	
	public static final String MESSAGE_SUCCESSFULL_USER_FOUND= "Usuario puede autenticarse";
	public static final String MESSAGE_ERROR_USER_NOT_FOUND= "Usuario o password incorrecto";
	
	public static final String MESSAGE_ERROR_STORE_NOT_FOUND= "Los datos de la tienda no existen";
	
	public static final String MESSAGE_ERROR_DATOS_REQUERIDOS= "Debe autenticarse nuevamente, no se han enviado datos requeridos";
	
	public static final String MESSAGE_SUCCESSFULL_STORE_SAVED= "Datos registrados exitosamente";
	
	public static final String MESSAGE_SUCCESSFULL_STORE_UPDATED= "Datos actualizados exitosamente";
	
	public static final String MESSAGE_SUCCESSFULL_PASSWORD_SENT= "Password reenviado exitosamente";
	
	public static final String MESSAGE_ERROR_COINS_NOT_FOUND= "Las criptomonedas no fueron encontradas";
	
	public static final String MESSAGE_SUCCESSFULL_COIN_STORE_SAVED= "Criptomonedas guardadas exitosamente";
	
	public static final String URL_INFURA_API_ETHEREUM = "https://mainnet.infura.io/v3/aaedff1ac6064304bee0c5245fbd1a4e";	
	
	public static final Integer STATUS_SUCCESSFULL= 1;
	public static final Integer STATUS_WARNING= 2;
	public static final Integer STATUS_ERROR= 3;
	public static final Integer EMPTY_LIST= 4;
	public static final Integer FULL_LIST= 5;
	
	public static final String EMAIL_EXIST= "El email esta registrado por otro usuario";
	public static final String USER_EXIST= "El nombre de usuario ya existe";
	public static final String STORE_NAME_EXIST= "El nombre de la tienda ya existe";
	public static final String DIFFERENT_PASSWORD= "El password y la verificacion del password son diferentes";
	public static final String INVALID_EMAIL= "El email es invalido";
	public static final String INVALID_USER= "El nombre de usuario es invalido, solo se permiten caracteres alfabeticos y una longitud minima de 6 y maximo de 10";
	public static final String INVALID_STORE_NAME= "El nombre de la tienda es invalido, solo se permiten caracteres alfabeticos y una longitud minima de 6 y maximo de 30";
	public static final String  INVALID_PASSWORD ="Password invalido";

	public static final String MESSAGE_VALIDATION_WARNING = "Han sucedido errores de validacion";	
	public static final String MESSAGE_STORE_COINS_CONFIGURATION = "No se ha configurado ninguna criptomoneda";
	
	public static final String INTERNAL_ERROR = "Internal Error";
	
}
