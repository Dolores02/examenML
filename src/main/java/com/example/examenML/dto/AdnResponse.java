package com.example.examenML.dto;


import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
//enviar la respuesta a las solicitudes de ADN, indicando si el ADN es mutante o no.
public class AdnResponse {
    private boolean isMutant;

    public boolean isMutant() {
        return isMutant;
    }
}
