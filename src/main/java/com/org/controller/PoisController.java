package com.org.controller;

import com.org.config.exeptions.BusinessException;
import com.org.model.Pois;
import com.org.service.PoisService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import org.slf4j.Logger;

@RestController
@RequestMapping(value = "/api/pois-interest")
public class PoisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoisController.class);

    private PoisService poiService;

    @Autowired
    public PoisController(PoisService poiService) {
        this.poiService = poiService;
    }

    @GetMapping(path = "/not-page")
    @ResponseBody
    public List<Pois> findAll(){
        LOGGER.info("Search all (POIs).");
        return poiService.findAll();
    }

    @GetMapping(path = "/page")
    @ResponseBody
    ResponseEntity<?> listAll(Pageable pageable){
        LOGGER.info("Search all page(POIs).");
        return poiService.listAll(pageable);
    }

    @PostMapping()
    public ResponseEntity<?> saveInterestPois(@Valid @RequestBody Pois pois) throws BusinessException {
        LOGGER.info("Save (POIs) in the database.");
        return new ResponseEntity<>(poiService.savePois(pois), HttpStatus.CREATED).getBody();
    }

    @GetMapping(path = "/proximity")
    private List<String> findAllPois(@Valid @RequestParam("coordinateReferenceX") Integer coordinateReferenceX
            ,@Valid @RequestParam("coordinateReferenceY") Integer coordinateReferenceY
            ,@Valid @RequestParam("distance")Double distance) {
        LOGGER.info("Search (POIs) for proximity.");
        return poiService.nameProximityPois(coordinateReferenceX, coordinateReferenceY, distance);
    }

    @GetMapping(path = "/query")
    private List<String> findAllPoisQuery(@Valid @RequestParam("coordinateReferenceX") Integer coordinateReferenceX
            ,@Valid @RequestParam("coordinateReferenceY") Integer coordinateReferenceY
            ,@Valid @RequestParam("distance")Double distance) {
        LOGGER.info("Search (POIs) for proximity.");
        return poiService.nameProximityPoisQuery(coordinateReferenceX, coordinateReferenceY, distance);
    }
}