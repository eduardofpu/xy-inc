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

    @Test
    public void validatorCoordinatedZero() throws BusinessException {

        Poi poi = new Poi("Camaru",0,0);
        create(poi.getName(),poi.getCoordinatedX(),poi.getCoordinatedY(),repository, validator);
        Assertions.assertThat(poi.getName()).isEqualTo("Camaru");
        Assertions.assertThat(poi.getCoordinatedX()).isEqualTo(0);
        Assertions.assertThat(poi.getCoordinatedY()).isEqualTo(0);
    }

    @Test
    public void validatorCoordinatedXZero() throws BusinessException {

        Poi poi = new Poi("Droga Lider",0,10);
        create(poi.getName(),poi.getCoordinatedX(),poi.getCoordinatedY(),repository, validator);
        Assertions.assertThat(poi.getName()).isEqualTo("Droga Lider");
        Assertions.assertThat(poi.getCoordinatedX()).isEqualTo(0);
        Assertions.assertThat(poi.getCoordinatedY()).isEqualTo(10);
    }

    @Test
    public void validatorCoordinatedYZero() throws BusinessException {

        Poi poi = new Poi("Loja do Açai",50,0);
        create(poi.getName(),poi.getCoordinatedX(),poi.getCoordinatedY(),repository, validator);
        Assertions.assertThat(poi.getName()).isEqualTo("Loja do Açai");
        Assertions.assertThat(poi.getCoordinatedX()).isEqualTo(50);
        Assertions.assertThat(poi.getCoordinatedY()).isEqualTo(0);
    }


    @Test(expected=BusinessException.class)
    public void validatetorNotCreate() throws BusinessException {

        Poi poi = new Poi("Ufu",-20,-10);
        create(poi.getName(),poi.getCoordinatedX(),poi.getCoordinatedY(),repository, validator);

    }

    @Test(expected=BusinessException.class)
    public void validateIfLessThanZero() throws BusinessException {

        Poi poi = new Poi("Cemig",-1,-2);
        validator.validate(poi.getCoordinatedX(), poi.getCoordinatedY());
    }

    @Test(expected=BusinessException.class)
    public void validateIfCoordinatedXLessThanZero() throws BusinessException {

        Poi poi = new Poi("Cemig",-4,0);
        validator.validate(poi.getCoordinatedX(), poi.getCoordinatedY());
    }

    @Test(expected=BusinessException.class)
    public void validateIfCoordinatedYLessThanZero() throws BusinessException {

        Poi poi = new Poi("Cemig",0,-3);
        validator.validate(poi.getCoordinatedX(), poi.getCoordinatedY());
    }

    private Poi create(String name,
                             Integer coordinatedX,
                             Integer coordinatedY,
                             PoiRepository repository,
                             PoiValidator validator) throws BusinessException {

        validator.validate(coordinatedX, coordinatedY);
        Poi poi = new Poi(name, coordinatedX, coordinatedY);
        return repository.save(poi);
    }
}