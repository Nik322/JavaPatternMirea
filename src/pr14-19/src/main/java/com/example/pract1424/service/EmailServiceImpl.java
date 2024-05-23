package ru.gavrilovds.prac14.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender mailSender;

  @Value("${application.worker-mail}")
  private String workerMail;

  @Value("${application.mail.enabled}")
  private boolean enabled;

  @Override
  @Async
  public void send(String body) {
    if (enabled) {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(workerMail);
      message.setSubject("Prac14");
      message.setText(body);
      mailSender.send(message);
    }
  }

}
