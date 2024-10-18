package com.example.examenML.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdnValidator implements ConstraintValidator<ValidDna, String[]> {

    @Override
    public boolean isValid(String[] dna, ConstraintValidatorContext context) {
        if (dna == null) {
            return false; // El ADN no debe ser nulo
        }

        // Validar la longitud de las secuencias
        int length = dna.length;
        for (String sequence : dna) {
            if (sequence.length() != length) {
                return false; // Todas las secuencias deben tener la misma longitud
            }
        }

        // Validar caracteres permitidos (A, T, C, G)
        for (String sequence : dna) {
            if (!isValidDnaSequence(sequence)) {
                return false; // Contiene caracteres no válidos
            }
        }

        return true; // Si pasa todas las validaciones
    }

    private boolean isValidDnaSequence(String sequence) {
        return sequence != null && sequence.matches("[ATCG]*"); // Solo permite A, T, C, G
    }
}
