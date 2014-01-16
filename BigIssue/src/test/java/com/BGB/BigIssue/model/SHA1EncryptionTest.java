package com.BGB.BigIssue.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.BGB.BigIssue.model.SHA1Encryption;

public class SHA1EncryptionTest {
	SHA1Encryption cipher;
	String text;
	byte[] salt;
	byte[] encrypt;

	@Before
	public void setUp(){
		cipher = new SHA1Encryption();
		
		text = "text";
		salt = cipher.generateSalt();
		encrypt = cipher.encrypt(text, salt);
	}
	
	@Test
	public void testEncryptionGenerateSaltGeneratesANewSaltWhenCalled(){
		System.out.println(salt);
		assertEquals(false,salt.equals(cipher.generateSalt()));
	}
	
	@Test  
	public void testEncryptionEncryptEncryptsText(){
		System.out.println(encrypt);
		assertEquals(false,text.equals(encrypt));
	}
	
	@Test
	public void testEncryptionAuthenticateReturnsTrueWhenValidTextPassedIn(){				
		assertEquals(true,cipher.authenticate(text, encrypt, salt));
	}
	
	@Test
	public void testEncryptionAuthenticateReturnsFalseWhenInvalidTextPassedIn(){
		assertEquals(false,cipher.authenticate("text1", encrypt, salt));
	}
	
}

