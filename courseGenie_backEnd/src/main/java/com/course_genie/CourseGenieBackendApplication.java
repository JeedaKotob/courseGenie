package com.course_genie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableMethodSecurity
@EnableAsync
@SpringBootApplication
public class CourseGenieBackendApplication {

    public static void main(String[] args) {
		SpringApplication.run(CourseGenieBackendApplication.class, args);
	}

}
