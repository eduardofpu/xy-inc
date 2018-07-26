package com.org.service;

import com.org.Application;
import com.org.config.exception.BusinessException;
import com.org.model.Poi;
import com.org.repository.PoiRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class PoiValidatorTest {

    @Autowired
    private PoiValidator validator;

    @Autowired
    private PoiRepository repository;

    @Test
    public void validator() throws BusinessException {

        Poi poi = new Poi("Ufu",20,10);
        create(poi.getName(),poi.getCoordinatedX(),poi.getCoordinatedY(),repository, validator);
        Assertions.assertThat(poi.getName()).isEqualTo("Ufu");
        Assertions.assertThat(poi.getCoordinatedX()).isEqualTo(20);
        Assertions.assertThat(poi.getCoordinatedY()).isEqualTo(10);
    }

    @Test(expected=BusinessException.class)
    public void validatorLessZero() throws BusinessException {

        Poi poi = new Poi("Cemig",-20,10);
        validator.validate(poi.getCoordinatedX(), poi.getCoordinatedY());
    }

    @Test(expected=BusinessException.class)
    public void validatorCoodinatedXLessZero() throws BusinessException {

        Poi poi = new Poi("Cemig",-20,-10);
        validator.validate(poi.getCoordinatedX(), poi.getCoordinatedY());
    }

    @Test(expected=BusinessException.class)
    public void validatorCoodinatedYLessZero() throws BusinessException {

        Poi poi = new Poi("Cemig",20,-10);
        validator.validate(poi.getCoordinatedX(), poi.getCoordinatedY());
    }

    public static Poi create(String name,
                             Integer coordinatedX,
                             Integer coordinatedY,
                             PoiRepository repository,
                             PoiValidator validator) throws BusinessException {

        validator.validate(coordinatedX, coordinatedY);
        Poi poi = new Poi(name, coordinatedX, coordinatedY);
        return repository.save(poi);
    }

}