package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.node.ArrayNode;

@SpringBootApplication
public class FlowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowerApplication.class, args);

		// return modified to main class???
		FlowerController controller = new FlowerController(new FlowerSource());
		ArrayNode modifiedString = controller.feed();
		System.out.println(modifiedString);
	}

}
