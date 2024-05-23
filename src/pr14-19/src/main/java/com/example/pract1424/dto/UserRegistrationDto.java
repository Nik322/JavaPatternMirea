package ru.gavrilovds.prac14.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRegistrationDto {
 private String name;
 private String password;
}
