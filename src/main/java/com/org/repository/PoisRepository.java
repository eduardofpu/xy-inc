package com.org.repository;

import com.org.model.Pois;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public interface PoisRepository extends JpaRepository<Pois, Long> {

    String DISTANCIA = "select p from Pois p where sqrt(power(p.coordinatedX - :coordinateReferenceX,2) + power(p.coordinatedY - :coordinateReferenceY,2)) < :distance";

    @Query(DISTANCIA)
    List<Pois> distanciaPois(@Param("coordinateReferenceX") Integer coordinateReferenceX,
                             @Param("coordinateReferenceY") Integer coordinateReferenceY,
                             @Param("distance") Double distance);
}
