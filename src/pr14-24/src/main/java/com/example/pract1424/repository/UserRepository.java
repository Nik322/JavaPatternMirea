package ru.gavrilovds.prac14.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gavrilovds.prac14.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<Long, UserEntity> {
  UserEntity getByUsername(String username);
}
