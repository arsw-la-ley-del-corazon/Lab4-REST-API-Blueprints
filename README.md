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


El proyecto está organizado en paquetes que siguen el patrón de capas lógicas

```

- model: contiene las entidades principales, Blueprint y Point. 

- persistence: define la interfaz BlueprintPersistence y su implementación inicial en memoria InMemoryBlueprintPersistence. 

- services: la clase BlueprintsServices coordina la lógica de negocio, aplica filtros y delega en la capa de persistencia. 

- controllers: el BlueprintsAPIController expone la API REST con operaciones CRUD 

- filters: provee distintos filtros (IdentityFilter, RedundancyFilter, UndersamplingFilter) para transformar datos. 

- config: configuración de Swagger y OpenApi

```

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

- Versionamiento en rutas (`/api/v1/blueprints`).  
- Respuestas uniformes con `ApiResponse<T>`.  
- Excepciones personalizadas (`BlueprintNotFoundException`, `BlueprintPersistenceException`).  
- Uso de ResponseEntity para códigos HTTP:  
  - `200 OK` → consultas exitosas  
  - `201 Created` → creación de recurso  
  - `202 Accepted` → actualización aceptada  
  - `400 Bad Request` → datos inválidos  
  - `404 Not Found` → recurso inexistente
    
- Filtros configurables (`Identity`, `Redundancy`, `Undersampling`) mediante perfiles

# OpenAPI/ Swagger

- Interfaz de Swagger UI documentando la API REST de Blueprints

<img width="533" height="358" alt="Captura de pantalla 2025-09-13" src="https://github.com/user-attachments/assets/fa31a07d-6263-40bd-8a64-dfdc609b34dd" />

forma interactiva los principales endpoints:

``crear un blueprint (POST), listar todos (GET), consultar  (GET), y agregar puntos a uno existente (PUT)``

Con Swagger se pueden visualizar, probar y validar los endpoints de forma interactiva. Además, genera automáticamente la especificación de la API en **OpenAPI 3.0**.

# Filtros de *Blueprints*

Implementamos dos filtros para el manejo de puntos en los blueprints: el RedundancyFilter, que elimina puntos duplicados consecutivos optimizando los datos almacenados, y el UndersamplingFilter, que conserva solo uno de cada dos puntos reduciendo la cantidad de información procesada, etos filtros se activan de manera flexible mediante perfiles de Spring (redundancy, undersampling), lo que permite seleccionar en tiempo de ejecución cuál filtro aplicar según la necesidad del sistema


# Pruebas

- **Model Test:** validación de entidades `Blueprint` y `Point`
- **Persistence Test:** pruebas en memoria y en PostgreSQL
- **Service Test:** verificación de la lógica de negocio
- **Filter Test:** validación de filtros de redundancia y undersampling

# Diagrama de Componentes

<img width="561" height="255" alt="Captura de pantalla 2025-09-13" src="https://github.com/user-attachments/assets/2a27d30c-b1c1-4b62-9ea7-d89a51fcba82" />


# Bono

Como bono del laboratorio, se empaquetó el proyecto en una imagen Docker usando el plugin spring-boot:build-image, esto permitió crear la imagen y correrla como un contenedor, al levantar el contenedor, la API REST quedó disponible dentro de Docker, facilitando su portabilidad y despliegue en cualquier entorno sin necesidad de instalar dependencias adicionales


<img width="1022" height="251" alt="Captura de pantalla 2025-09-13" src="https://github.com/user-attachments/assets/ada20834-59cb-40ad-8ddd-8df3c245aec9" />


<img width="1239" height="351" alt="Captura de pantalla 2025-09-13" src="https://github.com/user-attachments/assets/841b88e0-b181-4abb-a8b6-5a80939d5cac" />



## Conclusiones

El laboratorio permitió reforzar el entendimiento de la **arquitectura en capas** y cómo esta facilita la extensibilidad y el mantenimiento del software, la migración desde una persistencia en memoria hacia una base de datos real **PostgreSQL** se realizó de manera fluida gracias a la separación de responsabilidades, con la integración de 
**Swagger/OpenAPI** se quería potenciar la documentación y las pruebas, permitiendo que cualquier usuario consuma la API sin herramientas externas y los **filtros configurables** sobre cómo aplicar transformaciones dinámicas a los datos de un servicio, aumentando la flexibilidad y adaptabilidad de la aplicación a distintos escenarios

