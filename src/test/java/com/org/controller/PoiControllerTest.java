package com.org.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.AbstractTest;
import com.org.model.Poi;
import com.org.repository.PoiRepository;
import com.org.representation.IdRepresentation;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PoiControllerTest extends AbstractTest {

    private final String PATH = "/pois";

    @Autowired
    PoiRepository repository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        int totalElements = (int) repository.count();

        this.mockMvc.perform(
                get(PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalElements", Matchers.is(totalElements)));
    }

    @Test
    public void create() throws Exception {
        String jsonContent = IOUtils.toString(getClass()
                .getClassLoader()
                .getResourceAsStream("createPoi.json"));

        String jsonResponse = this.mockMvc.perform(
                post(PATH)
                        .contentType(APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id", Matchers.notNullValue()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        IdRepresentation idRepresentation = mapper.readValue(jsonResponse, IdRepresentation.class);

        Poi poiCreated = repository.findOne(idRepresentation.getId());

        Assertions.assertThat(poiCreated.getName()).isEqualTo("Farmacia");
        Assertions.assertThat(poiCreated.getCoordinatedX()).isEqualTo(88);
        Assertions.assertThat(poiCreated.getCoordinatedY()).isEqualTo(1);
    }

    @Test
    public void distancePois() throws Exception {
        int totalElements = (int) repository.findByDistance(
                new PageRequest(0, 100),
                20,
                10,
                10.0)
                .getTotalElements();

        this.mockMvc.perform(
                get(PATH + "/distance?coordinateReferenceX=20&coordinateReferenceY=10&distance=10&size=100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalElements", Matchers.is(totalElements)));
    }

}