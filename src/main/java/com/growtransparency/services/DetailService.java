package com.growtransparency.services;

import com.growtransparency.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailService implements UserDetailsService {

  private final UserRepository userRepository;

  public DetailService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var user = userRepository.findByEmail(email);

    if (user.isPresent()) {
      return (UserDetails) user.get();
    }

    throw new UsernameNotFoundException("Dados inválidos");
  }
}