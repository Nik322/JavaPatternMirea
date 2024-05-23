package ru.gavrilovds.prac14.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.gavrilovds.prac14.dto.UserRegistrationDto;

public interface UserService {

  public UserDetails loadUserByUsername(String username);

  void saveUser(UserRegistrationDto registrationDto);
}
