package com.project.management.AppConfig;



import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;

public class JwtProvider {
	
	static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public static String generateToken(Authentication auth) { 
		
		return Jwts.builder().setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime()))
					.claim("email", auth)
					.signWith(key)
					.compact();
	}
}
