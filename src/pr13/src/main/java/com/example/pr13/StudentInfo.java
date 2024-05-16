package com.example.pr13;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentInfo {

    @Value("${student.name}")
    private String name;

    @Value("${student.last_name}")
    private String lastName;

    @Value("${student.group}")
    private String group;

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Last Name: " + lastName);
        System.out.println("Group: " + group);
    }
}
