package com.lindaring.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicSpringSecurityTest {

  @Test
  public void contextLoads() {
  }

  @Test
  public void bcryptGenerate() {
      String password = "password";
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
      String hashedPassword = passwordEncoder.encode(password);
      System.out.println(hashedPassword);
  }

}
