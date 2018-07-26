package com.org.service;

import com.org.Application;
import com.org.config.exception.BusinessException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PoiValidatorTest {

    @Autowired
    private PoiValidator validator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void validateCoordinatedBiggerZero() throws BusinessException {
        validator.validate(20, 10);
    }

    @Test
    public void validateCoordinatedXLessZero() throws BusinessException {
        exception.expect(BusinessException.class);
        exception.expectMessage("Unable to save (POIs) with negative coordinates");
        validator.validate(-20, 1000);
    }

    @Test
    public void validateCoordinatedYLessZero() throws BusinessException {
        exception.expect(BusinessException.class);
        exception.expectMessage("Unable to save (POIs) with negative coordinates");
        validator.validate(20, -1000);
    }

    @Test
    public void validateAllCoordinatedLessZero() throws BusinessException {
        exception.expect(BusinessException.class);
        exception.expectMessage("Unable to save (POIs) with negative coordinates");
        validator.validate(-20, -1000);
    }
}