package com.org.controller;

import com.org.AbstractTest;
import com.org.config.exeptions.BusinessException;
import com.org.model.Pois;
import com.org.service.PoisService;
import org.apache.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PoisControllerTest extends AbstractTest {

	private static final Logger LOGGER = Logger.getLogger(PoisControllerTest.class);

	@Autowired
	private PoisService poiService;

	private Pois pois;

	@Test
	public void testfindAllPoisInterest() {
		List<Pois> poisInterest = poiService.findAll();
		LOGGER.info("List all: ");
		poisInterest.forEach(res -> {
			LOGGER.info("Name: " + res.getName());
			LOGGER.info("Coordenate X: " + res.getCoordinatedX());
			LOGGER.info("Coordenate Y: " + res.getCoordinatedY());
		});
		Assertions.assertThat(poisInterest).isNotNull();
	}

	@Test
	public void savePoisInterest() throws BusinessException {
		pois =  Pois.builder()
				.name("Shop Center")
				.coordinatedX(35)
				.coordinatedY(19).build();
		poiService.savePois(pois);
		LOGGER.info("Save point interest");
		Assertions.assertThat(pois.getName()).isNotNull();
		Assertions.assertThat(pois.getCoordinatedX()).isNotNull();
		Assertions.assertThat(pois.getCoordinatedY()).isNotNull();

	}

	@Test
	public void findPoisInterestFoProximity() {
		Integer coordenateX = 20;
		Integer coordenateY = 10;
		Integer distance = 10;
		LOGGER.info("Search by proximity: ");
		poiService.nameProximityPois(coordenateX, coordenateY, distance)
                .forEach(res -> {
			    LOGGER.info("Name: " + res);
		});

		Assertions.assertThat(coordenateX).isEqualTo(20);
		Assertions.assertThat(coordenateY).isEqualTo(10);
		Assertions.assertThat(distance).isEqualTo(10);
	}
}
