package com.wms.utils.security;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Yi Qiu
 *
 * Initial created on 2020-12-6
 */
public class JwtUtil {
	
	/*JWT authentication standard*/
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String ROLE = "rol";
	public static final String ID = "id";
	
	private static final Integer HS512_SECRET_SIZE = 86;
	private static String ISS = "Unknown ISS";
	private static String Secret = "Insecure Secret";
	private static boolean use_default = true;

	/**
	 * Assign new ISS and SECRET to replace the default.
	 * @param _ISS ISSUER who issues the JWTs.
	 * @param _SECRET SECRET using to create/extract the signature
	 */
	public static void register(String _ISS, String _SECRET) {	
		Secret = _SECRET;
		ISS = _ISS;
		use_default = false;
	}
	
	/**
	 * Create a new JWT.
	 * @param _obj Classes that implement AuthInfoExtraction can provide payload info.
	 * @param _lifetime Lifetime of JWTs.
	 * @return String data of JWTs.
	 */
	public static String makeToken(AuthInfoExtraction _obj, Long _lifetime) {
		HashMap<String, Object> c_map = new HashMap<String, Object>();
		c_map.put(ROLE, _obj.getRole());
		c_map.put(ID, _obj.getId());
		for(String key : _obj.getOpts().keySet()) {
			c_map.put(key, _obj.getOpts().get(key));
		}
		
		return makeToken(c_map, _lifetime);
	}
	
	/**
	 * Create a new JWT.
	 * @param _id Identifier for authentication
	 * @param _claims Additional payload info
	 * @param _lifetime Lifetime of JWTs.
	 * @return String data of JWTs.
	 */
	public static String makeToken(HashMap<String, Object> _claims, Long _lifetime) {
		warn();
		
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS512, Secret)
				.setClaims(_claims)
				.setIssuer(ISS)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + _lifetime * 1000 * 60))
				.compact();
	}
	

	/**
	 * Extract JWT to receive Identifier info.
	 * @param _Token Input JWT.
	 * @return Identifier info, null if the JWT is invalid.
	 */
	public static Object getIdentifier(String _Token) {
		Claims c = getTokenBody(_Token);
		return (c == null)?null:c.get(ID);
	}
	
	public static String getRole(String _Token) {
		Claims c = getTokenBody(_Token);
		return (c == null)?null:(String)c.get(ROLE);
	}
	
	public static Long getExpire(String _Token) {
		Claims c = getTokenBody(_Token);
		return (c == null)?null:c.getExpiration().getTime();
	}
	
	public static Object getValueByKey(String _Token, String key) {
		Claims c = getTokenBody(_Token);
		return (c == null)?null:c.get(key);
	}

	public static boolean isNonExpired(String _Token) {
		Claims c = getTokenBody(_Token);
		if(c == null) {
			return false;
		}else {
			return System.currentTimeMillis() <= c.getExpiration().getTime();
		}
		
	}
	
	/**
	 * Get a map object that stores the payload info of the input JWT.
	 * @param _Token Input JWT.
	 * @return map object that stores the payload info, null if the JWT is invalid.
	 */
	public static HashMap<String, Object> getPayLoadAttrs(String _Token) { 
		Claims c = getTokenBody(_Token);
		return (c == null)?null:new HashMap<String,Object>(c);
	}
	
	/**
	 * Helper class to extract a input JWT.
	 * @param _Token Input JWT.
	 * @return Claims object extracted from input JWT, null if the JWT is invalid.
	 */
	private static Claims getTokenBody(String _Token) {
		try {
			
			return Jwts.parser()
					.setSigningKey(Secret)
					.parseClaimsJws(_Token)
					.getBody();
			
		}catch(Exception e) {
			return null;
		}

	}
	
	/**
	 * Output warning log while using insecure secret.
	 */
	private static void warn() {
		if(use_default) {
			Logger.getGlobal().warning("Default config is only for testing purpose, "
					+ "Make sure use register() method to assign a strong secret");
		}
		
		if(Secret.length() != HS512_SECRET_SIZE) {
			Logger.getGlobal().warning("Currently using a non-HS512 secure secret...");
		}
	}
	
}
