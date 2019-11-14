package com.lindaring.security.repo;

import com.lindaring.security.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

  Optional<User> findUserByUsername(String username);

}
