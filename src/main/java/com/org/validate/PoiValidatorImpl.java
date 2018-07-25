package com.org.validate;

import com.org.config.exception.BusinessException;
import com.org.service.PoiValidator;
import org.springframework.stereotype.Service;

@Service
public class PoiValidatorImpl implements PoiValidator {

    @Override
    public void validate(Integer coordinatedX, Integer coordinatedY) throws BusinessException {
        verifyIfCoordinatesIsLessThanZero(coordinatedX, coordinatedY);
    }

    private void verifyIfCoordinatesIsLessThanZero(Integer coordinatedX, Integer coordinatedY) throws BusinessException {
        if(coordinatedX < 0 || coordinatedY < 0) {
            throw new BusinessException("Unable to save (POIs) with negative coordinates");
        }
    }
}
