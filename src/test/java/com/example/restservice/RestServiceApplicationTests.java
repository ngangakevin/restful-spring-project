package com.example.restservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestServiceApplicationTests {
	@Autowired
	private MainController mainController;
	@Test
	void contextLoads() {
	}

	@Test
	void controllerExists(){
		assertThat(mainController).isNotNull();
	}
}
