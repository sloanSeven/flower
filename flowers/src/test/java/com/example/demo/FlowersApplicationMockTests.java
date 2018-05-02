package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
// @RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FlowersApplicationMockTests {

	@Autowired
	private WebTestClient webClient;

	// @Autowired
	// private Reverser reverser;

	@MockBean
	private FlowerSource mockSource;

	@InjectMocks
	@Autowired
	private FlowerController mockController;

	@Before
	public void setUp() {
		BDDMockito.given(mockSource.getFeed(FlowerController.url)).willReturn("garbage");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void countTest() {
		String expected = "{\"content\":\"0\",\"id\":1}";
		webClient.get().uri("/count").exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(expected);
	}

	@Test
	public void feedTest() {
		String expected = null;
		webClient.get().uri("/feed").exchange().expectStatus().isOk().expectBody(String.class).isEqualTo(expected);

	}
}