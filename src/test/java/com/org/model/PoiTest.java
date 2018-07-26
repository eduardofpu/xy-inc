package com.org.model;

import com.org.Application;
import com.org.config.exception.BusinessException;
import com.org.repository.PoiRepository;
import com.org.service.PoiValidator;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PoiTest {

    @Autowired
    private PoiValidator validator;

    @Autowired
    private PoiRepository repository;


    @Test
    public void create() throws BusinessException {

        Poi poi = Poi.create("Shop", 20, 10, repository, validator);

        Poi poiCreated = repository.findOne(poi.getId());
        Assertions.assertThat(poiCreated.getId()).isNotNull();
        Assertions.assertThat(poiCreated.getId()).isEqualTo(poi.getId());
        Assertions.assertThat(poiCreated.getName()).isEqualTo(poi.getName());
        Assertions.assertThat(poiCreated.getCoordinatedX()).isEqualTo(poi.getCoordinatedX());
        Assertions.assertThat(poiCreated.getCoordinatedY()).isEqualTo(poi.getCoordinatedY());
    }
}