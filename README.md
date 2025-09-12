# Lab4-REST-API-Blueprints

[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![Maven](https://img.shields.io/badge/Maven-3.9-blue.svg)](https://maven.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue.svg)](https://www.postgresql.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Swagger](https://img.shields.io/badge/Swagger-OpenAPI-green.svg)](https://swagger.io/)

**Asignatura:** Arquitectura de Software  
**Estudiantes:**
- [Alexandra Moreno](https://github.com/AlexandraMorenoL)
- [Alison Valderrama](https://github.com/LIZVALMU)
- [Jeisson Sánchez](https://github.com/JeissonS02)
- [Valentina Gutierrez](https://github.com/LauraGutierrezr)
---

## 1 Familiarizacion del Laboratorio

- Revisar Paquetes


- Capa persistence con *InMemoryBlueprintPersistence*


- Capa *Services* y controlador *BlueprintsAPIController*



## 2 Migración a persitencia en PostgreSQL

- Configuracion de base de datos PostgreSQL con Docker

Cramos las tablas en la base de datos con el siguiente script:

```sql
-- Schema for PostgreSQL
CREATE TABLE IF NOT EXISTS blueprints (
    author VARCHAR(100) NOT NULL,
    name   VARCHAR(200) NOT NULL,
    PRIMARY KEY (author, name)
);

CREATE TABLE IF NOT EXISTS blueprint_points (
    id     BIGSERIAL PRIMARY KEY,
    author VARCHAR(100) NOT NULL,
    name   VARCHAR(200) NOT NULL,
    x      INTEGER NOT NULL,
    y      INTEGER NOT NULL,
    CONSTRAINT fk_blueprint
        FOREIGN KEY (author, name)
        REFERENCES blueprints(author, name)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_points_blueprint ON blueprint_points(author, name);

```

- Implementacion de neuvo repositotio *PostgresBlueprintPersistence*

- Mantener el contrato de la interfaz *BlueprintPersistence*


# 3 Buenas Prácticas de API REST

- Cambio de path base de controladore `/api/v1/blueprints`


## OpenAPI/ Swagger


## Filtros de *Blueprints*





### *BONUS*

- Imagen de contenedor (`spring-boot:build-image`)

- Metricas con Actuator
