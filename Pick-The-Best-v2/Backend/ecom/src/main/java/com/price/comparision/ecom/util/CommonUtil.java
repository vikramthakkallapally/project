package com.price.comparision.ecom.util;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public String generateOtp() {
		String numbers = "0123456789";
		String otp = "";

		Random rndm_method = new Random();

		for (int i = 0; i < 6; i++) {
			otp += numbers.charAt(rndm_method.nextInt(numbers.length()));
		}

		return otp;

	}
	
	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}
	
	public boolean getDecodedPassword(String password, String encryptedPassword) {
		
		System.out.println(password);
		System.out.println(encryptedPassword);
		return passwordEncoder.matches(password, encryptedPassword);
	}
	
	public String stringToHexaDecimal(String str ) {
	    StringBuffer sb = new StringBuffer();
	      char ch[] = str.toCharArray();
	      for(int i = 0; i < ch.length; i++) {
	         String hexString = Integer.toHexString(ch[i]);
	         sb.append(hexString);
	      }
	      
	     return sb.toString();
	}
	
	
	public String getUuid() {
	    return UUID.randomUUID().toString();
	}

}
