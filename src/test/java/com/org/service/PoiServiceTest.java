package com.org.service;

import com.org.AbstractTest;
import com.org.config.exception.BusinessException;
import com.org.model.Poi;
import com.org.repository.PoiRepository;
import com.org.representation.IdRepresentation;
import com.org.representation.PoiRepresentation;
import com.org.request.PoiRequest;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
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
        Page<PoiRepresentation> poiRepresentations = service.searchByDistance(new PageRequest(0, 100), 20, 10, 10.0);
        int totalElements = (int) poiRepresentations.getTotalElements();

        List<String> namesContent = getNameContent(poiRepresentations);
        System.out.println("Content---"+namesContent);

        List<String> namesPois = getNamesPois();
        System.out.println("Pois---"+namesPois);


        List<String> namesContentPois = comparatorNamesContentEqualsNamesPois(namesContent, namesPois);
        System.out.println("ContentPois---"+namesContentPois);

        Assertions.assertThat(namesContent).isEqualTo(namesContentPois);
        Assertions.assertThat(namesContent.size()).isEqualTo(totalElements);
        Assertions.assertThat(poiRepresentations.getSize()).isEqualTo(100);
    }

    private List<String> getNamesPois() {
        List<Poi> pois = repository.findAll();
        return pois.stream()
                .map(Poi::getName)
                .collect(Collectors.toList());
    }

    private List<String> getNameContent(Page<PoiRepresentation> poiRepresentations) {
        List<PoiRepresentation> content = poiRepresentations.getContent();
        return content.stream()
                .map(PoiRepresentation::getName)
                .collect(Collectors.toList());
    }

    private List<String> comparatorNamesContentEqualsNamesPois(List<String> namesContent, List<String> namesPois) {

        for (String content : namesContent) {
            for (String pois : namesPois) {
                if (content.equals(pois)) {
                    return namesContent;
                }
            }
        }
        return namesPois;
    }
}