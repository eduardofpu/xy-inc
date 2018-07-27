package com.org.model;

import com.org.AbstractTest;
import com.org.config.exception.BusinessException;
import com.org.repository.PoiRepository;
import com.org.service.PoiValidator;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PoiTest extends AbstractTest{

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