package com.example.examenML.services;

import com.example.examenML.entities.Adn;
import com.example.examenML.repositories.AdnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdnService {
    private final AdnRepository adnRepository;  // Acceder a base de datos
    private static final int sequenceLength = 4;   // Cuantos caracteres iguales consecutivos forman una SECUENCIA VALIDA

    @Autowired
    public AdnService(AdnRepository adnRepository) {
        this.adnRepository = adnRepository;
    }

    public static boolean isMutant(String[] dna) {
        int n = dna.length;
        int contadorsecuencia = 0;

        // Verificamos filas, columnas y diagonales
        contadorsecuencia += checkRows(dna, n);
        if (contadorsecuencia > 1) return true;

        contadorsecuencia += checkColumns(dna, n);
        if (contadorsecuencia > 1) return true;

        contadorsecuencia += checkDiagonals(dna, n);
        return contadorsecuencia > 1;
    }

    private static int checkRows(String[] dna, int n) {
        int contadorsecuencia = 0;  // Lleva la cuenta de cuantas SECUENCIAS DE 4 LETRAS IGUALES encuentra por fila
        for (int i = 0; i < n; i++) {  // Fila actual
            int cont = 1;   // Cuantos caracteres consecutivos iguales hay en la misma fila
            for (int j = 1; j < n; j++) {  // Cada caracter
                if (dna[i].charAt(j) == dna[i].charAt(j - 1)) {   // Comparamos el caracter actual con el anterior en la MISMA FILA
                    cont++;
                    if (cont == sequenceLength) {  // Si hay 4 caracteres iguales consecutivos
                        contadorsecuencia++;  // Suma 1 a las secuencias válidas
                        if (contadorsecuencia > 1) {  // Si hay más de 1 secuencia
                            return contadorsecuencia;
                        }
                    }
                } else {  // Si no hay más de 1 letra consecutiva igual
                    cont = 1;
                }
            }
        }
        return contadorsecuencia;
    }

    private static int checkColumns(String[] dna, int n) {
        int contadorsecuencia = 0;  // Lleva la cuenta de cuantas SECUENCIAS DE 4 LETRAS IGUALES encuentra por columna
        for (int j = 0; j < n; j++) {  // Columna actual
            int cont = 1;   // Cuantos caracteres consecutivos iguales hay en la misma columna
            for (int i = 1; i < n; i++) {  // Cada caracter
                if (dna[i].charAt(j) == dna[i - 1].charAt(j)) {   // Comparamos el caracter actual con el anterior en la MISMA COLUMNA
                    cont++;
                    if (cont == sequenceLength) {  // Si hay 4 caracteres iguales consecutivos
                        contadorsecuencia++;  // Suma 1 a las secuencias válidas
                        if (contadorsecuencia > 1) {  // Si hay más de 1 secuencia
                            return contadorsecuencia;
                        }
                    }
                } else {  // Si no hay más de 1 letra consecutiva igual
                    cont = 1;
                }
            }
        }
        return contadorsecuencia;
    }

    private static int checkDiagonals(String[] dna, int n) {
        int contadorsecuencia = 0;

        // Diagonales de izquierda a derecha
        for (int i = 0; i <= n - sequenceLength; i++) {
            for (int j = 0; j <= n - sequenceLength; j++) {
                // Verifico la diagonal en (i,j)
                char first = dna[i].charAt(j);
                boolean isDiagonal = true;

                for (int k = 1; k < sequenceLength; k++) {  // Siguientes elementos de la diagonal
                    if (dna[i + k].charAt(j + k) != first) {
                        isDiagonal = false;
                        break;
                    }
                }
                if (isDiagonal) {
                    contadorsecuencia++;
                    if (contadorsecuencia > 1) {
                        return contadorsecuencia; // Salida temprana si ya contamos más de una secuencia
                    }
                }
            }
        }

        // Diagonales de derecha a izquierda
        for (int i = 0; i <= n - sequenceLength; i++) { // Iterar sobre filas
            for (int j = sequenceLength - 1; j < n; j++) { // Iterar sobre columnas
                // Verificamos la diagonal que comienza en (i, j)
                char first = dna[i].charAt(j);
                boolean isDiagonal = true;

                for (int k = 1; k < sequenceLength; k++) { // Comprobamos los siguientes elementos en la diagonal
                    if (dna[i + k].charAt(j - k) != first) {
                        isDiagonal = false;
                        break; // Si encontramos una diferencia, salimos del bucle
                    }
                }

                if (isDiagonal) {
                    contadorsecuencia++;
                    if (contadorsecuencia > 1) {
                        return contadorsecuencia; // Salida temprana si ya contamos más de una secuencia
                    }
                }
            }
        }

        return contadorsecuencia;
    }

    public boolean analyzeAdn(String[] dna) {
        String dnaSequence = String.join(",", dna);
        // Verificamos si el ADN ya existe en la base de datos
        Optional<Adn> existingAdn = adnRepository.findByDna(dnaSequence);
        if (existingAdn.isPresent()) {
            return existingAdn.get().isMutant();
        }
        // Determinamos si el ADN es mutante
        boolean isMutant = isMutant(dna);
        Adn adnEntity = Adn.builder().dna(dnaSequence).isMutant(isMutant).build();
        adnRepository.save(adnEntity); // Guardo en la base de datos
        return isMutant;
    }
}
