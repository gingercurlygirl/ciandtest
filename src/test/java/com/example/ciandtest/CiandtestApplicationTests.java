package com.example.ciandtest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-test-h2.properties")
class CiandtestApplicationTests {

	@Test
	void contextLoads() {
	}

}
