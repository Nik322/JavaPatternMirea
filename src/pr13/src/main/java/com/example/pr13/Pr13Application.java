package com.example.pr13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Pr13Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Pr13Application.class, args);

        String name = context.getEnvironment().getProperty("student.name");
        String lastName = context.getEnvironment().getProperty("student.last_name");
        String group = context.getEnvironment().getProperty("student.group");

        System.out.println("Name: " + name);
        System.out.println("Last Name: " + lastName);
        System.out.println("Group: " + group);
    }
}
