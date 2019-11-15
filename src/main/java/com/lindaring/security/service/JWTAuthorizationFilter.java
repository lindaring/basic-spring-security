package com.lindaring.security.service;

import com.lindaring.security.model.ApplicationUser;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static com.lindaring.security.service.SecurityContants.HEADER_STRING;
import static com.lindaring.security.service.SecurityContants.SECRET;
import static com.lindaring.security.service.SecurityContants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
  private final CustomUserDetailsService customUserDetailsService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService) {
    super(authenticationManager);
    this.customUserDetailsService = customUserDetailsService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    String header = getHeadersInfo(request).get("authorization");
    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getAuthenticationToken(request);
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    chain.doFilter(request, response);
  }

  private Map<String, String> getHeadersInfo(HttpServletRequest request) {

    Map<String, String> map = new HashMap<String, String>();

    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = request.getHeader(key);
      map.put(key, value);
    }

    return map;
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
    String token = getHeadersInfo(request).get("authorization");
    if (token == null) return null;
    String username = Jwts.parser().setSigningKey(SECRET)
            .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
            .getBody()
            .getSubject();
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
    ApplicationUser appUser = customUserDetailsService.loadApplicationuserByUsername(username);
    return username != null ? new UsernamePasswordAuthenticationToken(appUser, null, userDetails.getAuthorities()) : null;
  }
}
