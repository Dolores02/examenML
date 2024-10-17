package com.example.examenML.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Adn extends Base implements Serializable {
    private String dna;
    private boolean isMutant;

}
