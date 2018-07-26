package com.org.service;

import com.org.Application;
import com.org.config.exception.BusinessException;
import com.org.representation.IdRepresentation;
import com.org.representation.PoiRepresentation;
import com.org.request.PoiRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

import static java.lang.String.valueOf;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class PoiServiceTest {
    private static final Logger LOGGER = Logger.getLogger(valueOf(PoiServiceIpml.class));

    @Autowired
    private PoiService service;

    @Test
    public void findAll() {
        Page<PoiRepresentation> pois = service.findAll(new PageRequest(0, 100));

        LOGGER.info("Listar todos: ");
        pois.forEach(res -> {
            LOGGER.info("Name: " + res.getName());
            LOGGER.info("Coordenate X: " + res.getCoordinatedX());
            Assertions.assertThat(pois).isNotNull();
        });
    }

    @Test
    public void create() throws BusinessException {
        PoiRequest poiRequest = new PoiRequest("Shop Center", 35, 19);
        IdRepresentation id = service.create(poiRequest);
        LOGGER.info("Save point interest");
        Assertions.assertThat(poiRequest.getName()).isEqualTo("Shop Center");
        Assertions.assertThat(poiRequest.getCoordinatedX()).isEqualTo(35);
        Assertions.assertThat(poiRequest.getCoordinatedY()).isEqualTo(19);
        Assertions.assertThat(id.getId()).isNotNull();
    }

    @Test
    public void  searchByDistance(){

        Integer coordenateX = 20;
        Integer coordenateY = 10;
        Double distance = 10.0;
        LOGGER.info("Search by proximity: ");
        service.searchByDistance(new PageRequest(0, 100), coordenateX, coordenateY, distance)
                .forEach(res -> {
                    LOGGER.info("Name: " + res);
                });
        Assertions.assertThat(coordenateX).isEqualTo(20);
        Assertions.assertThat(coordenateY).isEqualTo(10);
        Assertions.assertThat(distance).isEqualTo(10.0);

    }
}