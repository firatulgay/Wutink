package com.fulgay.wutink.security.jwt.filter;

import com.fulgay.wutink.security.jwt.manager.JwtTokenManager;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private JwtTokenManager tokenManager;
    private static final Logger LOG = Logger.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenManager tokenManager) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

        try {
            if (isBasicAuthRequest(request)) {
                SecurityContextHolder.clearContext();
                chain.doFilter(request, response);
                return;
            } else if (isLogoutRequest(request)) {
                chain.doFilter(request, response);
                return;
            }

            String jwtToken = tokenManager.extractJwtFromRequest(request);
            if (jwtToken == null) {
                chain.doFilter(request, response);
                return;
            }

            if (StringUtils.hasText(jwtToken) && tokenManager.validateJwtAccessToken(jwtToken)) {

                if (BooleanUtils.isFalse(request.getRequestURI().contains("/refreshToken"))) {
                    String principal = tokenManager.getUsernameFromToken(jwtToken);
                    List<SimpleGrantedAuthority> roles = tokenManager.getRolesFromToken(jwtToken);

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            principal, null, roles);

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    chain.doFilter(request, response);
                }
                return;
            }
            prepareInvalidAuthResponse(response);
            return;
        } catch (ExpiredJwtException | BadCredentialsException ex) {
            if (ex instanceof ExpiredJwtException) {
                try {
                    ExpiredJwtException expJwtEx = (ExpiredJwtException) ex;
                    String refreshToken = tokenManager.extractRefreshTokenFromRequest(request);
                    tokenManager.doGenerateRefreshedAccessToken(refreshToken, response, expJwtEx);
                    chain.doFilter(request, response);
                    return;
                } catch (Exception e) {
                    LOG.error("Error while generating refreshed access token ! ", e);
                    prepareInvalidAuthResponse(response);
                    return;
                }
            }
            prepareInvalidAuthResponse(response);
            return;
        } catch (Exception ex) {
            LOG.error(ex.getMessage(),ex);
            prepareInvalidAuthResponse(response);
            return;
        }
    }

    private boolean isLogoutRequest(HttpServletRequest request) {
        return request.getRequestURL().toString().contains("/doLogout");
    }

    private boolean isBasicAuthRequest(HttpServletRequest request) {
        String data = request.getHeader(HttpHeaders.AUTHORIZATION);
        return (StringUtils.hasText(data) && data.startsWith("Basic "));
    }

    private void prepareInvalidAuthResponse(HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("INVALID-AUTH", "Invalid authentication attempt!");
    }
}
