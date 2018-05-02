package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

@RunWith(SpringRunner.class) // @RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlowersApplicationEndTests {

	@Autowired
	private WebTestClient webClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void countTest() {
		String expected = "{\"content\":\"10\",\"id\":1}";
		webClient.get().uri("/count").exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(expected);
	}

	@Test
	public void feedTest() {
		ResponseSpec spec = webClient.get().uri("/feed").exchange();
		spec.expectStatus().isOk();
		spec.expectBody().jsonPath("[3]title").isEqualTo("1800Flowers");

		spec = webClient.get().uri("/feed").exchange();
		spec.expectBody().jsonPath("[3]body").isEqualTo("1800Flowers");
	}
}