package common.Utils.Md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Md5 {	
	/**
	 * salt
	 */
	private static String salt = "thisisapieceofsalt";
	
	public Md5(){
	}
	
	public Md5(String salt){
		Md5.setSalt(salt);
	}


	/**
	 * md5 make
	 * @author Hyacinth
	 * @date February 23,2012
	 * @param String
	 * @return String
	 * @modified Hyacinth
	 */	
	public static String encryptByMD5(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bts = md.digest(str.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(bts);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}		
	}
	
	public static String getMd5WithSalt(String str){
		return encryptByMD5(encryptByMD5(str+salt)+salt);
	}
	
	public static boolean compareMd5(String test,String res){
		return getMd5WithSalt(test).equals(res) ? true : false ;
	}
	
	public static String getSalt() {
		return salt;
	}

	public static void setSalt(String salt) {
		Md5.salt = salt;
	}
	
	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args){
		String str = "abc";
		Md5.setSalt("salt");
		String[] test = {"abc","ABC","NOTABC"};
		String result;
		//make
		result = getMd5WithSalt(str);
		//check
		for(String mtest : test){
		System.out.println(mtest);
		System.out.println(compareMd5(mtest, result)?"true":"false");
		}
	}
}
