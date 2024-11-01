# ExamenML: Detección de Mutantes en Secuencias de ADN

Este proyecto es una API para detectar mutantes basada en la secuencia de ADN de un individuo. La API recibe secuencias de ADN y determina si pertenecen a un mutante o no, siguiendo ciertos patrones genéticos. El proyecto ha sido desarrollado en el contexto de un examen de MercadoLibre.

## Requisitos

- Solo se aceptan matrices cuadradas de caracteres **A, T, C, G**.
- Si la secuencia tiene más de cuatro letras iguales consecutivas en cualquier dirección (horizontal, vertical o diagonal), se considera **mutante**.
- Si se ingresa una matriz no cuadrada o con caracteres inválidos, la API responde con un error **403 Forbidden**.
- Si se ingresa una matriz valida, la API responde con **200 OK**.

### `POST /mutant`
Recibe una matriz de ADN y determina si el individuo es mutante.

### `GET /stats`
Devuelve estadísticas sobre las verificaciones realizadas.

## Ejecución del Proyecto

1. Clonar el repositorio.
2. Configurar el entorno con Java 17 y Spring Boot.
3. Ejecutar la aplicación en un servidor local o en el host de Render especificado.
4. Probar los endpoints utilizando Postman o cualquier cliente de API.
