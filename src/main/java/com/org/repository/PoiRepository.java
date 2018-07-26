package com.org.repository;

import com.org.model.Poi;
import com.org.representation.PoiRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoiRepository extends JpaRepository<Poi, Long> {

    @Query("select p from Poi p where sqrt(power(p.coordinatedX - :coordinateReferenceX,2) + power(p.coordinatedY - :coordinateReferenceY,2)) < :distance")
    Page<Poi> findByDistance(Pageable pageable,
                             @Param("coordinateReferenceX") Integer coordinateReferenceX,
                             @Param("coordinateReferenceY") Integer coordinateReferenceY,
                             @Param("distance") Double distance);
}
