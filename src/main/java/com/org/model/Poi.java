package com.org.model;

import com.org.config.exception.BusinessException;
import com.org.repository.PoiRepository;
import com.org.service.PoiValidator;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Poi {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Integer coordinatedX;
    @NotNull
    private Integer coordinatedY;

    public Poi(String name, Integer coordinatedX, Integer coordinatedY){
        this.name = name;
        this.coordinatedX = coordinatedX;
        this.coordinatedY = coordinatedY;
    }

    public static Poi create(String name,
                             Integer coordinatedX,
                             Integer coordinatedY,
                             PoiRepository repository,
                             PoiValidator validator) throws BusinessException {

        validator.validate(coordinatedX, coordinatedY);
        Poi poi = new Poi(name, coordinatedX, coordinatedY);
        return repository.save(poi);
    }
}
