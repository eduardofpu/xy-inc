package com.org.service;

import com.org.AbstractTest;
import com.org.config.exception.BusinessException;
import com.org.model.Poi;
import com.org.repository.PoiRepository;
import com.org.representation.IdRepresentation;
import com.org.representation.PoiRepresentation;
import com.org.request.PoiRequest;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PoiServiceTest extends AbstractTest {

    @Autowired
    private PoiService service;

    @Autowired
    private PoiRepository repository;

    @Test
    public void findAll() {
        Page<PoiRepresentation> pois = service.findAll(new PageRequest(0, 100));
        Assertions.assertThat(pois.getTotalElements()).isEqualTo(repository.count());
    }

    @Test
    public void create() throws BusinessException {
        PoiRequest poiRequest = new PoiRequest("Shopping Center", 35, 19);
        IdRepresentation idRepresentation = service.create(poiRequest);

        Assertions.assertThat(idRepresentation).isNotNull();
        Assertions.assertThat(idRepresentation.getId()).isNotNull();

        Poi poi = repository.findOne(idRepresentation.getId());
        Assertions.assertThat(poi.getName()).isEqualTo(poiRequest.getName());
        Assertions.assertThat(poiRequest.getCoordinatedX()).isEqualTo(poiRequest.getCoordinatedX());
        Assertions.assertThat(poiRequest.getCoordinatedY()).isEqualTo(poiRequest.getCoordinatedY());
    }

    @Test
    public void searchByDistance() {
        Page<PoiRepresentation> poiPage = service.searchByDistance(new PageRequest(0, 100), 20, 10, 6.0);
        List<PoiRepresentation> content = poiPage.getContent();

        Assertions.assertThat(poiPage.getTotalElements()).isEqualTo(2);

        Optional<PoiRepresentation> firstResult = content.stream()
                .filter(poi -> poi.getId().equals(3L))
                .findFirst();

        Optional<PoiRepresentation> secondResult = content.stream()
                .filter(poi -> poi.getId().equals(6L))
                .findFirst();

        Assert.assertNotNull(firstResult);
        Assert.assertNotNull(secondResult);
    }
}