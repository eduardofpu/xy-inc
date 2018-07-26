package com.org.repository;

import com.org.Application;
import com.org.model.Poi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class PoiRepositoryTest {

    @Autowired
    private PoiRepository repository;


    @Test
    public void create(){
        repository.findAll();
    }

    @Test
    public void save(){
        Poi poi = new Poi("Una", 47, 55);
        repository.save(poi);

    }

    @Test
    public void findByDistance(){
        repository.findByDistance(new PageRequest(0, 100), 20,10, 10.0);
    }
}