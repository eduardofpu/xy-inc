package com.org.service;

import com.org.config.exception.BusinessException;
import com.org.model.Poi;
import com.org.representation.IdRepresentation;
import com.org.representation.PoiRepresentation;
import com.org.request.PoiRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PoiService {

    Page<PoiRepresentation> findAll(Pageable pageable);
    IdRepresentation create(PoiRequest pois) throws BusinessException;
    Page<PoiRepresentation> searchByDistance(Pageable pageable, Integer coordinateReferenceX, Integer coordinateReferenceY, Double distance);
}
