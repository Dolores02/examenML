package com.example.examenML.controllers;

import com.example.examenML.dto.AdnRequest;
import com.example.examenML.dto.AdnResponse;
import com.example.examenML.services.AdnService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mutant")
public class AdnController {
    private final AdnService adnService;

    public AdnController(AdnService adnService) {
        this.adnService = adnService;
    }

    @PostMapping
    public ResponseEntity<AdnResponse> checkMutant(@Valid @RequestBody AdnRequest adnRequest) {
        boolean isMutant = adnService.analyzeAdn(adnRequest.getDna());
        AdnResponse adnResponse = new AdnResponse(isMutant);  //contiene el resultado de la verificacion
        if (isMutant) {
            return ResponseEntity.ok(adnResponse); //200ok
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(adnResponse);  //403 forbidden
        }
    }

}

