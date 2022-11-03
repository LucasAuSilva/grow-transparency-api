package com.growtransparency.settings.filters;

import com.growtransparency.repositories.UserRepository;
import com.growtransparency.services.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final UserRepository userRepository;

  public AuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var token = getToken(request);
    var valid = tokenService.isTokenValid(token);

    if (valid) {
      var userId = tokenService.getUserId(token);
      var user = userRepository.findById(userId).get();

      var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  private String getToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");

    if (token == null || token.isBlank()) {
      return null;
    }
    if (token.startsWith("Bearer ")) {
      return token.substring(7);
    }

    return token;
  }
}