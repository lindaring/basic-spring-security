package com.lindaring.security.service;

import com.lindaring.security.entity.User;
import com.lindaring.security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  public User getUser(String username) {
    Optional<User> user = userRepo.findUserByUsername(username);
    return user.orElse(null);
  }
}
