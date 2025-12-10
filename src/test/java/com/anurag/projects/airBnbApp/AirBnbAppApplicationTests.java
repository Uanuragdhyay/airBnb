package com.anurag.projects.airBnbApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.temporal.*;

@SpringBootTest
class AirBnbAppApplicationTests {

	@Test
	void contextLoads() {
		long dd = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(1));
		System.out.println(dd);

	}

}
