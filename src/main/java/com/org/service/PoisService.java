package com.org.service;

import com.org.config.exeptions.BusinessException;
import com.org.model.Pois;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PoisService {

    List<Pois> findAll();
    ResponseEntity<?> savePois(Pois poi) throws BusinessException;
    List<String> nameProximityPois(Integer coordinateReferenceX, Integer coordinateReferenceY, Integer distance);
}
