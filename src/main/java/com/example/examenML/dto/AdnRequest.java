package com.example.examenML.dto;

import com.example.examenML.validators.ValidDna;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//recibir las solicitudes que contienen la secuencia de ADN.
public class AdnRequest {
    @ValidDna
    private String [] dna;
}
