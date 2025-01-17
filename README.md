# ForoHub
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-brightgreen)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![License](https://img.shields.io/badge/license-MIT-blue)
![Status](https://img.shields.io/badge/status-en%20desarrollo-yellow)

ForoHub es una aplicación backend desarrollada en Java utilizando Spring Boot para gestionar un foro interactivo. Incluye funcionalidades para crear, listar, actualizar y eliminar información relacionada con usuarios, tópicos y respuestas, con soporte de autenticación basada en JWT y seguridad mediante perfiles.

## Índice

1. [Introducción](#introducción)
2. [Características](#características)
3. [Requisitos previos](#requisitos-previos)
4. [Tecnologías utilizadas](#tecnologías-utilizadas)
5. [Configuración del proyecto](#configuración-del-proyecto)
6. [Documentación de la API](#documentación-de-la-api)
7. [Estructura del proyecto](#estructura-del-proyecto)
8. [Endpoints principales](#endpoints-principales)
    - [Usuarios](#usuarios)
    - [Tópicos](#tópicos)
    - [Respuestas](#respuestas)
9. [Seguridad](#seguridad)
10. [Errores comunes y solución](#errores-comunes-y-solución)
11. [Licencia](#licencia)
12. [Autor](#autor)

## Introducción

ForoHub es una solución completa para gestionar foros interactivos. Proporciona endpoints para la gestión de usuarios, tópicos, y respuestas, además de ofrecer autenticación segura mediante JWT.

## Características

- CRUD para usuarios, tópicos y respuestas.
- Autenticación stateless mediante JWT.
- Roles y perfiles de usuario.
- Validaciones específicas para garantizar integridad de datos.
- Documentación automática con Swagger.
- Base de datos relacional con MySQL y manejo de migraciones con Flyway.

## Requisitos previos

Asegúrate de tener instalado:

1. **Java 17** o superior instalado.
2. **MySQL 8.0** o superior configurado.
3. Un cliente para consumir APIs como [Postman](https://www.postman.com/) o [Insomnia](https://insomnia.rest/)
4. IDE compatible con Spring Boot (IntelliJ, Eclipse, etc.).

## Tecnologías utilizadas
El proyecto utiliza las siguientes tecnologías:

- **Java 17**
- **Spring Boot 3.4.1**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Validation
  - DevTools
- **Flyway Migration**
- **MySQL Driver**
- **Lombok**
- **Swagger/OpenAPI**
- **JWT (com.auth0:java-jwt)**

## Configuración del proyecto

1. Clona este repositorio:
   ```bash
   git clone https://github.com/DiegoFranco16/foro-hub.git
   ```
2. Configura tu base de datos en el archivo `application.properties`:
   ```properties
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.url=${DATASOURCE_URL}
   spring.datasource.username=${DATASOURCE_USERNAME}
   spring.datasource.password=${DATASOURCE_PASSWORD}

   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true

   spring.flyway.baseline-on-migrate=true
   spring.flyway.baseline-version=1

   api.security.secret=${JWT_SECRET:123456}
   ```

   Para las pruebas locales se usó:
   ```
   DATASOURCE_URL = jdbc:mysql://localhost:3306/forohub_api
   DATASOURCE_USERNAME = ${DB_USERNAME}
   DATASOURCE_PASSWORD = ${DB_PASSWORD}
   ```
4. Crea la base de datos en MySQL:
   ```sql
   CREATE DATABASE forohub_api;
   ```
5. Ejecuta el proyecto desde tu IDE o mediante:
   ```bash
   ./mvnw spring-boot:run
   ```
## Documentación de la API
   La API está documentada utilizando Swagger y OpenAPI. Accede a la documentación interactiva en http://localhost:8080/swagger-ui.html.
   

## Estructura del proyecto

```
src/main/java
├── com.forohub.api_fh
│   ├── controller
│   │   ├── TopicoController.java
│   │   ├── UsuarioController.java
│   │   └── RespuestaController.java
│   ├── domain
│   │   ├── usuario
│   │   │   ├── Usuario.java
│   │   │   ├── UsuarioRepository.java
│   │   │   ├── GestionDeUsuarios.java
│   │   │   └── dtos
│   │   ├── topico
│   │   ├── respuesta
│   │   └── perfil
│   ├── infra
│   │   └── security
│   │       ├── SecurityConfigurations.java
│   │       ├── SecurityFilter.java
│   │       ├── TokenService.java
│   │       └── AutenticacionController.java
├── resources
│   ├── application.properties
│   └── db/migration
│       ├── V1__CreateUsersTable.sql
│       ├── V2__CreateTopicsTable.sql
│       └── ...
```

## Endpoints principales

### Usuarios
- **GET** `/usuarios`: Listar usuarios.
- **POST** `/usuarios`: Crear un usuario.
- **GET** `/usuarios/{id}`: Obtener un usuario por ID.
- **DELETE** `/usuarios/{id}`: Eliminar un usuario.

### Tópicos
- **GET** `/topicos`: Listar tópicos con filtros opcionales por curso y año.
- **POST** `/topicos`: Crear un tópico.
- **GET** `/topicos/{id}`: Obtener un tópico por ID.
- **PUT** `/topicos/{id}`: Actualizar un tópico.
- **DELETE** `/topicos/{id}`: Eliminar un tópico físicamente.
- **PUT** `/topicos/desactivar/{id}`: Desactivar un tópico.

### Respuestas
- **GET** `/respuestas`: Listar respuestas.
- **POST** `/respuestas`: Crear una respuesta.
- **GET** `/respuestas/{id}`: Obtener una respuesta por ID.
- **DELETE** `/respuestas/{id}`: Eliminar una respuesta.

## Seguridad

La aplicación utiliza autenticación basada en JWT. 

1. Obtén un token enviando una solicitud **POST** a `/login` con:
   ```json
   {
       "correoElectronico": "usuario@example.com",
       "contrasena": "123456"
   }
   ```
2. Usa el token para autenticarte en otros endpoints añadiendo un header `Authorization`:
   ```
   Bearer <TOKEN>
   ```

## Errores comunes y solución

- **LazyInitializationException:** Cambiar `fetch = FetchType.LAZY` a `EAGER` si es indispensable.
- **Token inválido:** Verifica que el secret en `application.properties` coincida con el utilizado en la generación del token.

## Licencia

Este proyecto está bajo la Licencia MIT. Para más información, revisa el archivo `LICENSE` en el repositorio.

---

¡Gracias por usar ForoHub! Si tienes dudas o problemas, no dudes en crear un issue en el repositorio. 🚀


## Autor
Este proyecto fue desarrollado por [Diego Alejandro Franco Alvarez](https://www.linkedin.com/in/diego-alejandro-franco-alvarez/), un apasionado por la tecnología y el desarrollo de soluciones innovadoras.
