package com.employee.pkg.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.employee.pkg.entity.Employee;

@Component
public class EmpUtil {
	
	

//	@Value("${encr.decr.keyField}")
//	private String keyField;
	
	LocalDateTime dateTime = LocalDateTime.now();
	private static byte[] key;
	private static SecretKeySpec secretKey;
	private String keyField = "AMMU123!@#";

	public boolean validate(Employee emp) {

		if ((emp.getEmail()) == null) {
			System.err.println("Email is null");
			return false;
		}
		if (emp.getName() == null) {
			System.err.println("Name is null");
			return false;
		}
		if (emp.getLastName() == null) {
			System.err.println("LastName is null");
			return false;
		}
		if (emp.getPhoneNo() == null || emp.getPhoneNo().length() != 10) {
			System.err.println("PhoneNo is null");
			return false;
		}
		return true;
	}

	public int createRandNo() {
		Random randem = new Random();
		int nextInt = randem.nextInt(1000);
		return nextInt;
	}

	public String setDate() {
		String valueOf = String.valueOf(dateTime);
		String date = valueOf.substring(0, 10);
		return date;
	}

	public String setTime() {
		String valueOf = String.valueOf(dateTime);
		String time = valueOf.substring(11, 19);
		return time;
	}

	

	private void setKey(String myKey) {
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			secretKey = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String decrypt(String strToDecrypt) {
		System.out.println("KEYFIELD : "+keyField);
		try {
			setKey(keyField);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	public String encrypt(String strToEncrypt) {
		System.out.println("KEYFIELD : "+keyField);
		try {
			setKey(keyField);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		EmpUtil dt =new EmpUtil();
		String encrypt = dt.encrypt("Abhishek");
		System.out.println(encrypt);
		String decrypt = dt.decrypt("/rwBx9SpJcMDcMVzKXoIBQ==");
		System.out.println(decrypt);
	}

}

