package com.abeeks.maintenancerequestserver;

import com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.models.MaintenceRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MaintenanceRequestServerApplicationTests {

	@Test
	public void constructorTest01() {
		MaintenceRequest maintenceRequest = new MaintenceRequest("Spongebob", "Squarepants", "sponge@bikinibottom.com", "Apt 1", "Plumbing issue");
		maintenceRequest.setId(2l);

		String expected = "2 Spongebob Squarepants sponge@bikinibottom.com Apt 1 Plumbing issue";
		String actual = maintenceRequest.toString();

		Assertions.assertEquals(expected, actual);
	}
}
