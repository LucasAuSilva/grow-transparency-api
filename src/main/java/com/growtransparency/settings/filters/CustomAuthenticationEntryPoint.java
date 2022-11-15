package com.growtransparency.settings.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.growtransparency.services.TokenService;
import com.growtransparency.settings.errors.ResponseError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private TokenService tokenService;

  public CustomAuthenticationEntryPoint(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    try {
      var token = request.getHeader("Authorization").split(" ")[1];
      expirado(token);
    } catch (ExpiredJwtException e) {
      factoryResponse(response, 403, "Acesso negado: token expirado");
    } catch (ArrayIndexOutOfBoundsException e) {
      factoryResponse(response, 403, "Acesso negado: token não encontrado");
    } catch (Exception e) {
      factoryResponse(response, 403, "Acesso negado: token inválido");
    }
  }

  private static void factoryResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
    response.setContentType("application/json;charset=UTF-8");
    response.setStatus(statusCode);

    var mapper = new ObjectMapper();
    response.getWriter().write(mapper.writeValueAsString(new ResponseError(statusCode, message)));
  }

  private boolean expirado(String token) {
    var expired = tokenService.getExpired(token);
    var issuedAt = tokenService.getIssued(token);

    return issuedAt > expired;
  }
}
