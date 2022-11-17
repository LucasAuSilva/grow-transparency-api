package com.growtransparency.settings.security;

import com.growtransparency.repositories.UserRepository;
import com.growtransparency.services.DetailService;
import com.growtransparency.services.TokenService;
import com.growtransparency.settings.filters.AuthenticationFilter;
import com.growtransparency.settings.filters.CustomAccessDeniedHandler;
import com.growtransparency.settings.filters.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecuritySettings {

  private final DetailService detailService;
  private final TokenService tokenService;
  private final UserRepository userRepository;

  public SecuritySettings(DetailService detailService, TokenService tokenService, UserRepository userRepository) {
    this.detailService = detailService;
    this.tokenService = tokenService;
    this.userRepository = userRepository;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    var authFilter = new AuthenticationFilter(tokenService, userRepository);
    var roleAdmin = "ADMIN";

    return httpSecurity.cors().and().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/user/register").permitAll()  // permit all POST requests in /user/register
            .antMatchers(HttpMethod.POST, "/user/login").permitAll()     // permit all POST requests in /user/login
            .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
            .antMatchers(HttpMethod.POST, "/project").hasRole(roleAdmin)   // permit POST requests in /projects if it has ADMIN role
            .antMatchers(HttpMethod.GET, "/user/all").hasRole(roleAdmin)
            .antMatchers(HttpMethod.PUT, "/user/admin/**").hasRole(roleAdmin)
            .anyRequest().authenticated()                                                 // permit any requests if its authenticated
            .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
            .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler())
            .and().csrf().disable()
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().build();
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);

    authenticationManagerBuilder.userDetailsService(detailService).passwordEncoder(passwordEncoder());

    return authenticationManagerBuilder.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new CustomAuthenticationEntryPoint(tokenService);
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }
}