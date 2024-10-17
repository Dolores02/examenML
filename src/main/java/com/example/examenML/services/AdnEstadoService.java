package com.example.examenML.services;


import com.example.examenML.dto.EstadoResponse;
import com.example.examenML.repositories.AdnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdnEstadoService {
    private final AdnRepository adnRepository;
    @Autowired
    public AdnEstadoService(AdnRepository adnRepository) {
        this.adnRepository = adnRepository;
    }

    public EstadoResponse getStats() {
        long countMutantDna = adnRepository.countByIsMutant(true);
        long countHumanDna = adnRepository.countByIsMutant(false);
        double ratio = countHumanDna == 0 ? 0 : (double) countMutantDna / countHumanDna;
        return new EstadoResponse(countMutantDna, countHumanDna, ratio);
    }
}
