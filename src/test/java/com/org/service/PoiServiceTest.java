package com.org.service;

import com.org.Application;
import com.org.config.exception.BusinessException;
import com.org.model.Poi;
import com.org.repository.PoiRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

import static java.lang.String.valueOf;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PoiServiceTest {

    @Autowired
    private PoiService service;

    @Autowired
    private PoiRepository repository;

    @Test
    public void findAll() {
        Page<PoiRepresentation> pois = service.findAll(new PageRequest(0, 100));
        Assertions.assertThat(pois.getTotalElements()).isEqualTo(repository.count());
    }

    @Test
    public void create() throws BusinessException {
        PoiRequest poiRequest = new PoiRequest("Shopping Center", 35, 19);
        IdRepresentation idRepresentation = service.create(poiRequest);

        Assertions.assertThat(idRepresentation).isNotNull();
        Assertions.assertThat(idRepresentation.getId()).isNotNull();

        Poi poi = repository.findOne(idRepresentation.getId());
        Assertions.assertThat(poi.getName()).isEqualTo(poiRequest.getName());
        Assertions.assertThat(poiRequest.getCoordinatedX()).isEqualTo(poiRequest.getCoordinatedX());
        Assertions.assertThat(poiRequest.getCoordinatedY()).isEqualTo(poiRequest.getCoordinatedY());
    }

    @Test
    public void  searchByDistance(){

        Page<PoiRepresentation> poiRepresentations = service.searchByDistance(new PageRequest(0, 100), 20, 10, 10.0);

        Poi poiTotalElements = repository.findOne(poiRepresentations.getTotalElements());
        Assertions.assertThat(poiTotalElements).isNotNull();
        Assertions.assertThat(poiTotalElements.getId()).isNotNull();

        Poi poiTotalElements2 = repository.findOne(poiRepresentations.getTotalElements());

        Assertions.assertThat(poiTotalElements.getId()).isEqualTo(poiRepresentations.getTotalElements());
        Assertions.assertThat(poiTotalElements.getName()).isEqualTo(poiTotalElements2.getName());
        Assertions.assertThat(poiTotalElements.getCoordinatedX()).isEqualTo(poiTotalElements2.getCoordinatedX());
        Assertions.assertThat(poiTotalElements.getCoordinatedY()).isEqualTo(poiTotalElements2.getCoordinatedY());


    }
}