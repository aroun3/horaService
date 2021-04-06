package com.visitor;

import com.visitor.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class VisitorManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitorManagementApplication.class, args);
	}
}