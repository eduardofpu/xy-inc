package com.org.service;

import com.org.config.exeptions.BusinessException;
import com.org.model.Pois;
import com.org.repository.PoisRepositoryPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@Service
@Transactional
public class PoisServiceIpml implements PoisService {

    private PoisRepositoryPage poisRepositoryPage;

    @Autowired
    public PoisServiceIpml(PoisRepositoryPage poisRepositoryPage) {
        this.poisRepositoryPage = poisRepositoryPage;
    }

    @Override
    public Iterable<Pois> findAll() {
        return poisRepositoryPage.findAll();
    }

    @GetMapping //http://localhost:8080/api/pois-interest/page?page=0&size=4
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(poisRepositoryPage.findAll(pageable), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> savePois(Pois poi) throws BusinessException {

        if(poi.getCoordinatedX() < 0 || poi.getCoordinatedY() < 0) {
            throw new BusinessException("Unable to save (POIs) with negative coordinates");
        }
        return new ResponseEntity<>( poisRepositoryPage.save(poi), HttpStatus.CREATED);
    }

    @Override
    public List<String> nameProximityPois(Integer coordinateReferenceX, Integer coordinateReferenceY, Integer distance) {

        List<Pois> pois = getPois(coordinateReferenceX, coordinateReferenceY, distance);
        List<String> names = getNames(pois);
        return names;
    }

    private List<String> getNames(List<Pois> pois) {
        return pois.stream()
                   .map(Pois::getName)
                   .collect(Collectors.toList());
    }

    private List<Pois> getPois(Integer coordinateReferenceX, Integer coordinateReferenceY, Integer distance) {
        List<Pois> getPois = (List<Pois>) poisRepositoryPage.findAll();
        return getPois.stream()
                .filter(pois-> calcDistance(coordinateReferenceX, coordinateReferenceY, pois) < distance)
                .collect(Collectors.toList());
    }

    private double calcDistance(Integer coordinateReferenceX, Integer coordinateReferenceY, Pois pois) {
        return sqrt(getPow(coordinateReferenceX, coordinateReferenceY, pois));
    }

    private double getPow(Integer coordinateReferenceX, Integer coordinateReferenceY, Pois pois) {
        return pow(pois.getCoordinatedX() - coordinateReferenceX,2) + pow(pois.getCoordinatedY() - coordinateReferenceY,2);
    }
}
