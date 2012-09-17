package common.Utils.Md5;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * DES 对称加密
 * 
 * @author Hyacinth
 * 
 */
public class SymmetricEncryption {
	boolean encryptFlag = true;

	private String expressly;// 明文
	private String ciphertext;// 密文

	private static String keyBuff = "myemail";

	public SymmetricEncryption() {

	}

	/**
	 * 设置原文
	 * 
	 * @param expressly
	 *            原文
	 */
	public void setExpressly(String expressly) {
		this.expressly = expressly;
		// 加密
		this.ciphertext = encryptData(expressly);
	}

	/**
	 * 设置密文
	 * 
	 * @param ciphertext
	 *            密文
	 */
	public void setCiphertext(String ciphertext) {
		this.ciphertext = ciphertext;
		// 解密
		this.expressly = decryptData(ciphertext);
	}

	/**
	 * 获取原文
	 * 
	 * @return
	 */
	public String getExpressly() {
		return expressly;
	}

	/**
	 * 获取密文
	 * 
	 * @return
	 */
	public String getCiphertext() {
		return ciphertext;
	}

	/**
	 * 加密
	 * 
	 * @param strData
	 *            原文
	 * @return 密文
	 */
	public static String encryptData(String strData) {
		String returnData = "";
		try {
			String oriDataValue = strData;
			BASE64Decoder base64decoder = new BASE64Decoder();
			BASE64Encoder base64encoder = new BASE64Encoder();
			byte[] keyBytes = base64decoder.decodeBuffer(keyBuff);
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(keyBytes));
			Key key = generator.generateKey();
			Cipher cipher = Cipher.getInstance("DES" + "/" + "ECB" + "/"
					+ "PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptBytes = cipher
					.doFinal(oriDataValue.getBytes("UTF-8"));
			String encryptStr = base64encoder.encode(encryptBytes);
			returnData = encryptStr;
		} catch (Exception e) {
			System.out.println("ERROR IN ENCRIPT");
			// e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 解密
	 * 
	 * @param strData
	 *            密文
	 * @return 原文
	 */
	public static String decryptData(String strData) {
		String returnData = "";
		try {
			returnData = "";
			String encryptDataValue = strData;
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] keyBytes = base64decoder.decodeBuffer(keyBuff);
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			generator.init(new SecureRandom(keyBytes));
			Key key = generator.generateKey();
			Cipher cipher = Cipher.getInstance("DES" + "/" + "ECB" + "/"
					+ "PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] oriBytes = cipher.doFinal(base64decoder
					.decodeBuffer(encryptDataValue));
			returnData = new String(oriBytes, "UTF-8");
		} catch (Exception e) {
			System.out.println("ERROR IN DECRYPT");
			// e.printStackTrace();
		}
		return returnData;
	}

	public static String getKeyBuff() {
		return keyBuff;
	}

	public static void setKeyBuff(String keyBuff) {
		SymmetricEncryption.keyBuff = keyBuff;
	}

	public boolean isEncryptFlag() {
		return encryptFlag;
	}

	public void setEncryptFlag(boolean encryptFlag) {
		this.encryptFlag = encryptFlag;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			// method 0
			SymmetricEncryption se = new SymmetricEncryption();
			// 1.0
			se.setExpressly("HelloWord!");
			System.out.println("明文" + se.getExpressly());
			System.out.println("密文" + se.getCiphertext());
			// 1.1
			se.setCiphertext(se.getCiphertext());
			System.out.println("明文" + se.getExpressly());
			System.out.println("密文" + se.getCiphertext());

			// method 1
			System.out.println();
			System.out.println("C"
					+ SymmetricEncryption.encryptData("HelloWorld!"));
			System.out.println("E"
					+ SymmetricEncryption.decryptData(SymmetricEncryption
							.encryptData("HelloWorld!")));

			System.out.println("======================");
			SymmetricEncryption.setKeyBuff("new Key buff");
		}
	}

}
