package com.org.controller;

import com.org.Application;

import com.org.repository.PoiRepository;
import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@AutoConfigureMockMvc
public class PoiControllerTest {

    private final String PATH = "/pois";

    @Autowired
    PoiRepository repository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        int totalElements = (int) repository.count();

        this.mockMvc.perform(
                get("/pois"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalElements", Matchers.is(totalElements)));
    }

    @Test
    public void create() throws Exception {
        String jsonContent = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("createPoi.json"));
        int totalElements = (int) repository.count();

        this.mockMvc.perform(
                post("/pois")
                        .contentType(APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id", Matchers.is(++totalElements)));
    }

}
