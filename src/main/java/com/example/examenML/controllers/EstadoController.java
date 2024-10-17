package com.example.examenML.controllers;


import com.example.examenML.dto.EstadoResponse;
import com.example.examenML.services.AdnEstadoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class EstadoController {
    private final AdnEstadoService adnEstadoService;

    public EstadoController(AdnEstadoService adnEstadoService) {
        this.adnEstadoService = adnEstadoService;
    }

    @GetMapping
    public EstadoResponse getStats() {
        return adnEstadoService.getStats();   //devolver estadisticas
    }
}
