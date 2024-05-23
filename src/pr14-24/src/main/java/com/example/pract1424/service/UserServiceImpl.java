package ru.gavrilovds.prac14.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gavrilovds.prac14.dto.UserRegistrationDto;
import ru.gavrilovds.prac14.entity.UserEntity;
import ru.gavrilovds.prac14.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
  private final PasswordEncoder passwordEncoder;
  private final UserRepository users;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity user = users.getByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return new User(user.getUsername(), user.getPassword(), List.of(
        new SimpleGrantedAuthority("ROLE_USER")
    ));
  }

  @Override
  public void saveUser(UserRegistrationDto user) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(user.getName());
    userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    users.save(userEntity);
  }
}
