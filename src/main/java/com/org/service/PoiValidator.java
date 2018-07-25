package com.org.service;

import com.org.config.exception.BusinessException;

public interface PoiValidator {

    public void validate(Integer getCoordinatedX, Integer getCoordinatedY) throws BusinessException;
}
