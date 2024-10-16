package com.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class MeetingCalendarApplication {

    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(MeetingCalendarApplication.class, args);
    }

    @PostConstruct
    public void init() {
        Employee emp1 = new Employee(null, "John Doe");
        Employee emp2 = new Employee(null, "Jane Smith");
        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
    }
}
