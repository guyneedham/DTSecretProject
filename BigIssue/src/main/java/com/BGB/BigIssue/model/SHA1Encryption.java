package com.BGB.BigIssue.model;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
/**
 * Passwords will be encrypted using this class.
 * 
 * 
 * @author guy.needham
 * @version 1.0
 * 
 */
public class SHA1Encryption {
	/**
	 * The authenticate method checks if the input text is the same as the encrypted text.
	 * @param text a String, the text to authenticate.
	 * @param stored a byte[], the encrypted text.
	 * @param salt a byte[], the salt used to encrypt the text.
	 * @return boolean true if the text is valid, otherwise false.
	 */
	public boolean authenticate(String text, byte[] stored, byte[] salt){
		byte[] encryptText = encrypt(text,salt); //encrypt the input
		return Arrays.equals(stored, encryptText); //compare the two arrays
	} 
	/**
	 * The encrypt method converts text into a byte[] based on the SHA1 algorithm.
	 * This could be improved by using inner classes to hide information on the algorithm.
	 * @param text a String, the text to encrypt.
	 * @param salt a byte[], the salt used in encryption.
	 * @return toReturn a byte[], the encrypted text.
	 */
	public byte[] encrypt(String text, byte[] salt){
		byte[] toReturn = null;
		//using the SHA1 algorithm
		String algorithm = "PBKDF2WithHmacSHA1";
		//SHA1 returns 160 bytes, so using this length
		int derivedKeyLength = 160;
		//2000 iterations makes it more hard to crack
		int iterations = 2000;
		//generates key specification to algorithm
		KeySpec spec = new PBEKeySpec(text.toCharArray(), salt, iterations, derivedKeyLength);
		
		SecretKeyFactory f;
		try {
			//make a key
			f = SecretKeyFactory.getInstance(algorithm);
			//encrypt using the key
			toReturn = f.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			System.out.println("The encryption does not exist.");
		} catch (InvalidKeySpecException e) {
			System.out.println("Invalid key");
		}
		return toReturn;
	}
	/**
	 * The generateSalt method generates a salt.
	 * It is safe to store a salt alongside the encrypted text (http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf page 3).
	 * 
	 * @return salt a byte[], used to hash text, allows for the generation of many possible keys for a password increasing security.
	 */
	public byte[] generateSalt(){
		//initiates the salt
		byte[] salt = new byte[8];
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			//generates a 64 bit salt
			random.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("The algorithm for salt generation does not exist.");
		}
		return salt;
		
	}
	
}