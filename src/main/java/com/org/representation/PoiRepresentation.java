package com.org.representation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PoiRepresentation {
    private Long id;
    private String name;
    private Integer coordinatedX;
    private Integer coordinatedY;
}
