package com.org.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tb_pois_interest")
public class Pois {

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
}
