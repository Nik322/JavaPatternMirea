package com.example.task_10;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

interface Programmer {
    void doCoding();
}

class Junior implements Programmer {
    @Override
    public void doCoding() {
        System.out.println("Junior programmer is coding...");
    }
}

class Middle implements Programmer {
    @Overrided
    public void doCoding() {
        System.out.println("Middle programmer is coding...");
    }
}

class Senior implements Programmer {
    @Override
    public void doCoding() {
        System.out.println("Senior programmer is coding...");
    }
}

@Configuration
class AppConfig {

    @Bean
    public Junior junior() {
        return new Junior();
    }

    @Bean
    public Middle middle() {
        return new Middle();
    }

    @Bean
    public Senior senior() {
        return new Senior();
    }
}

public class Task10Application {
    public static void main(String[] args) {
        String beanName = "senior";

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Programmer programmer = (Programmer) context.getBean(beanName);

        if (programmer != null) {
            programmer.doCoding();
        } else {
            System.out.println("Bean with name '" + beanName + "' not found.");
        }
    }
}