package com.org.service;

import com.org.config.exception.BusinessException;
import com.org.model.Poi;
import com.org.representation.IdRepresentation;
import com.org.representation.PoiRepresentation;
import com.org.representation.PoiRepresentationBuilder;
import com.org.request.PoiRequest;
import com.org.repository.PoiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Transactional
public class PoiServiceIpml implements PoiService {

    private PoiRepository repository;
    private PoiValidator validator;

    @Autowired
    public PoiServiceIpml(PoiRepository repository, PoiValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @GetMapping
    public Page<PoiRepresentation> findAll(Pageable pageable) {
        Page<Poi> pois = repository.findAll(pageable);
        return PoiRepresentationBuilder.builder().pois(pois).build();
    }

    @Override
    public IdRepresentation create(PoiRequest poiRequest) throws BusinessException {
        Poi poi = Poi.create(
                poiRequest.getName(),
                poiRequest.getCoordinatedX(),
                poiRequest.getCoordinatedY(),
                repository,
                validator);
        return new IdRepresentation(poi.getId());
    }

    @Override
    public Page<PoiRepresentation> searchByDistance(Pageable pageable,
                                                    Integer coordinateReferenceX,
                                                    Integer coordinateReferenceY,
                                                    Double distance) {

        Page<Poi> pois = repository.findByDistance(pageable, coordinateReferenceX, coordinateReferenceY, distance);
        return PoiRepresentationBuilder.builder().pois(pois).build();
    }
}
