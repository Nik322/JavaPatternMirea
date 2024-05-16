package com.example.pr12;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public FileHashing fileHashing() {
        String sourceFileName = "source.txt";
        String targetFileName = "target.txt";
        return new FileHashing(sourceFileName, targetFileName);
    }
}