package com.lindaring.security.service;

import com.lindaring.security.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ApplicationUser appUser = loadApplicationuserByUsername(username);
    return new User(appUser.getUsername(), appUser.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
  }

  public ApplicationUser loadApplicationuserByUsername(String username) {
    return new ApplicationUser("batman", "$2y$05$o/y38i5JnMYLW3gi4ImIiuGIgo85onzEQaqWu9BpnNsriEoxaV8J6");
  }
}
