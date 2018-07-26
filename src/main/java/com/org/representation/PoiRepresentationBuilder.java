package com.org.representation;

import com.org.model.Poi;
import org.springframework.data.domain.Page;

public class PoiRepresentationBuilder {
    private Page<Poi> pois;

    public PoiRepresentationBuilder pois(Page<Poi> pois) {
        this.pois = pois;
        return this;
    }

    public static PoiRepresentationBuilder builder() {
        return new PoiRepresentationBuilder();
    }

    public Page<PoiRepresentation> build() {
        return  this.pois.map(poi -> new PoiRepresentation(
                poi.getId(),
                poi.getName(),
                poi.getCoordinatedX(),
                poi.getCoordinatedY()));
    }
}