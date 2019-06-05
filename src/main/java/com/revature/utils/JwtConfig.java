package com.revature.utils;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtConfig {
	
	public static final String URI = "/**";
	public static final String HEADER = "Authorization";
	public static final String PREFIX = "Bearer ";
	public static final int EXPIRATION = 24 * 60 * 60;
	public static final String SECRET = "JwtSecretKey";
	public static final Key SIGNING_KEY;
	
	static {
		// Create a signing key using the JWT secret key
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] secretBytes = DatatypeConverter.parseBase64Binary(SECRET);
		SIGNING_KEY = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
	}
	
	private JwtConfig() {
		super();
	}

}
