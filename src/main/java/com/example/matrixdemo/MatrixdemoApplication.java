package com.example.matrixdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class MatrixdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(MatrixdemoApplication.class, args);
	}

}
