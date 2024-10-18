package com.example.examenML.entities;

import jakarta.persistence.Column;
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
    @Column(nullable = false, name = "dna", unique = true)
    private String dna;
    private boolean isMutant;

}
