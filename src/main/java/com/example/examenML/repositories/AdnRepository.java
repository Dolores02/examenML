package com.example.examenML.repositories;

import com.example.examenML.entities.Adn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdnRepository extends JpaRepository<Adn, Long> {

    Optional<Adn> findByDna(String dnaSequence);  //buscar un registro en la base de datos que tenga una secuencia de ADN específica
    long countByIsMutant(boolean isMutant);
}
