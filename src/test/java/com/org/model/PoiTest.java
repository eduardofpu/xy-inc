package com.org.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PoiTest {

    @Test
    public void findAll(){
        List<Poi> pois = listPois();
        Assertions.assertThat(pois.size()).isNotNull();
    }

    @Test
    public void create(){
        Poi poi = new Poi( "Farmácia",   47, 14);

        Assertions.assertThat(poi.getName()).isEqualTo("Farmácia");
        Assertions.assertThat(poi.getCoordinatedX()).isEqualTo(47);
        Assertions.assertThat(poi.getCoordinatedY()).isEqualTo(14);
    }

    private List<Poi> listPois(){
        Poi p1 = new Poi(1L, "Lanchonete",   27, 12);
        Poi p2 = new Poi(2L, "Posto",        31, 18);
        Poi p3 = new Poi(3L, "Joalheria",    15, 12);
        Poi p4 = new Poi(4L, "Floricultura", 19, 21);
        Poi p5 = new Poi(5L, "Pub",          12, 8);
        Poi p6 = new Poi(6L, "Supermecado",  23, 6);
        Poi p7 = new Poi(7L, "Churrascaria", 28, 2);
        return Arrays.asList(p1, p2, p3, p4, p5, p6, p7);
    }

}