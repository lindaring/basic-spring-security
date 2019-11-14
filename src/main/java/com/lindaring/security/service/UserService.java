package com.lindaring.security.service;

import com.lindaring.security.entity.User;
import com.lindaring.security.model.UserCredentials;
import com.lindaring.security.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<User> user = userRepo.findUserByUsername(userName);

    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

    UserCredentials userCredentials = user.map(UserCredentials::new).get();
    log.info("User: " + userCredentials);
    return userCredentials;
  }
}
