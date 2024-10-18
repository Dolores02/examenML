package com.example.examenML.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//enviar estadísticas sobre los ADN procesados
public class EstadoResponse {

    @JsonProperty("count_mutant_dna")
    private Long countMutantDna;


    @JsonProperty("count_human_dna")
    private Long countHumanDna;
    private double ratio;
}
