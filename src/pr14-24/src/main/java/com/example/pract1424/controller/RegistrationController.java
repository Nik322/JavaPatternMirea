package ru.gavrilovds.prac14.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gavrilovds.prac14.dto.UserRegistrationDto;
import ru.gavrilovds.prac14.service.UserService;

@RequestMapping("/register")
@RequiredArgsConstructor
@Controller
public class RegistrationController {
  private final UserService userService;

  @GetMapping
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new UserRegistrationDto("",""));
    return "registration";
  }
  @PostMapping
  public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
    userService.saveUser(registrationDto);
    return "redirect:/register?success";
  }

}
