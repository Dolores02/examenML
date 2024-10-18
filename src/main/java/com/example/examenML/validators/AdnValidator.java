package com.example.examenML.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AdnValidator implements ConstraintValidator<ValidDna, String[]> {

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        // Verificar si el ADN es nulo
        if (dna == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "El ADN no debe ser nulo");
        }

        // Validar la longitud de las secuencias
        int length = dna.length;
        for (String sequence : dna) {
            if (sequence.length() != length) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Todas las secuencias deben tener la misma longitud");
            }
        }

        // Validar caracteres permitidos (A, T, C, G)
        for (String sequence : dna) {
            if (!isValidDnaSequence(sequence)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "La secuencia de ADN contiene caracteres no válidos");
            }
        }

        return true; // Si pasa todas las validaciones
    }

    private boolean isValidDnaSequence(String sequence) {
        return sequence != null && sequence.matches("[ATCG]*"); // Solo permite A, T, C, G
    }
}
