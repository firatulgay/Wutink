package com.fulgay.wutink.security.jwt.manager;

import com.fulgay.wutink.service.AuthenticationService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class JwtTokenManager {

	@Value("${jwt.token.secret}")
	private String secret;
	
	@Value("${jwt.access.token.expiration.duration}")
	private long expirationDurationInMsForAccess;

	@Value("${jwt.refresh.token.expiration.duration}")
	private long expirationDurationInMsForRefresh;
	
	@Autowired
	private EncryptionManager encryptionManager;

	@Autowired
	private AuthenticationService authenticationService;

	
	public String [] generateToken(UserDetails userDetails, Long userId) {
		
		Map<String, Object> claims = new HashMap<>();

		Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
		
		if (roles.contains(new SimpleGrantedAuthority("ADMIN"))) {
			claims.put("isAdmin", true);
		}
		
		if (roles.contains(new SimpleGrantedAuthority("USER"))) {
			claims.put("isUser", true);
		}
		
		if (userId != null && userId > 0) {
			claims.put("ticket", encryptionManager.encrypt(String.valueOf(userId)));
		}

		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	public boolean validateJwtAccessToken(String authToken) {
		
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} 
		catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		} 
		catch (ExpiredJwtException ex) {
			throw ex;
		}
	}

	public boolean validateJwtRefreshToken(String refreshToken) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(refreshToken);
			return true;
		}
		catch (SignatureException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
			throw new BadCredentialsException("INVALID_CREDENTIALS", ex);
		}
		catch (ExpiredJwtException ex) {
			throw ex;
		}
	}

	public void doGenerateRefreshedAccessToken(String refreshToken, HttpServletResponse httpResponse,ExpiredJwtException ex){
		if (validateJwtRefreshToken(refreshToken)){
			String usernameFromToken = getUsernameFromToken(refreshToken);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
					null, null, null);

			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			String[] tokens = doGenerateToken(ex.getClaims(), usernameFromToken);
			authenticationService.addAuthCookies(tokens,httpResponse);
		}
	}

	public String getUsernameFromToken(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	public List<SimpleGrantedAuthority> getRolesFromToken(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		List<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>(2);
		
		Boolean admin = claims.get("isAdmin", Boolean.class);
		if (admin != null && admin) {
			roles.add(new SimpleGrantedAuthority("ADMIN"));
		}

		Boolean user = claims.get("isUser", Boolean.class);
		if (user != null && user) {
			roles.add(new SimpleGrantedAuthority("USER"));
		}
		return roles;
	}
	
	public String extractTicket(String token) {
		
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return claims.get("ticket", String.class);
	}
	
	public String extractJwtFromRequest(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String jwtSessionId = null;

		if (cookies != null){
			for (Cookie cookie : cookies) {
				if ("jwtSessionId".equals(cookie.getName())){
					jwtSessionId = cookie.getValue();
				}
			}
		}

		if (StringUtils.hasText(jwtSessionId)) {
			return jwtSessionId;
		}
		return null;
	}

	public String extractRefreshTokenFromRequest(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String refreshToken = null;

		if (cookies != null){
			for (Cookie cookie : cookies) {
				if ("refreshToken".equals(cookie.getName())){
					refreshToken = cookie.getValue();
				}
			}
		}

		if (StringUtils.hasText(refreshToken)) {
			return refreshToken;
		}
		return null;
	}
	
	public String[] doGenerateToken(Map<String, Object> claims, String subject) {


		long currentTimeMillis = System.currentTimeMillis();
		String accessToken = Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(currentTimeMillis))
				.setExpiration(new Date(currentTimeMillis + expirationDurationInMsForAccess))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();

		String refreshToken = Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(currentTimeMillis))
				.setExpiration(new Date(currentTimeMillis + expirationDurationInMsForRefresh))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();

		String[] tokenArray = {accessToken,refreshToken};

		return tokenArray;
	}
}
