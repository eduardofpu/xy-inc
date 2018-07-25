package com.org.controller;

import com.org.Application;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@AutoConfigureMockMvc
public class PoiControllerTest {

    private final String PATH = "/pois";

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAllControllerTest() throws Exception {
        this.mockMvc.perform(
                get(PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("totalElements"), Matchers.is(7));
    }

}
