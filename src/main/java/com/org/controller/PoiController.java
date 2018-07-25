package com.org.controller;

import com.org.config.exception.BusinessException;
import com.org.representation.IdRepresentation;
import com.org.representation.PoiRepresentation;
import com.org.request.PoiRequest;
import com.org.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pois")
public class PoiController {

    private PoiService poiService;

    @Autowired
    public PoiController(PoiService poiService) {
        this.poiService = poiService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Page<PoiRepresentation> findAll(Pageable pageable){
        return poiService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IdRepresentation create(@Valid @RequestBody PoiRequest pois) throws BusinessException {
        return poiService.create(pois);
    }

    @GetMapping(path = "/distance")
    @ResponseStatus(HttpStatus.OK)
    private Page<PoiRepresentation> searchByDistance(Pageable pageable,
                                                     @Valid @RequestParam("coordinateReferenceX") Integer coordinateReferenceX,
                                                     @Valid @RequestParam("coordinateReferenceY") Integer coordinateReferenceY,
                                                     @Valid @RequestParam("distance") Double distance) {
        return poiService.searchByDistance(pageable, coordinateReferenceX, coordinateReferenceY, distance);
    }
}