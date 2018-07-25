package com.org.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PoiRequest {
    private String name;
    private Integer coordinatedX;
    private Integer coordinatedY;
}
