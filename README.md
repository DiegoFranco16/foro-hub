# ForoHub

ForoHub es una aplicación backend desarrollada en Java utilizando Spring Boot para gestionar un foro interactivo. Incluye funcionalidades para crear, listar, actualizar y eliminar información relacionada con usuarios, tópicos y respuestas, con soporte de autenticación basada en JWT y seguridad mediante perfiles.

## Características

- CRUD para usuarios, tópicos y respuestas.
- Autenticación stateless mediante JWT.
- Roles y perfiles de usuario.
- Validaciones específicas para garantizar integridad de datos.
- Documentación automática con Swagger.
- Base de datos relacional con MySQL y manejo de migraciones con Flyway.

## Dependencias principales

- **Java 17**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Security
  - Validation
  - DevTools
- **Flyway Migration**
- **MySQL Driver**
- **Lombok**
- **Swagger**
- **JWT (com.auth0:java-jwt)**

## Requisitos previos

1. Java 17 instalado.
2. MySQL 8.4 configurado.
3. IDE compatible con Spring Boot (IntelliJ, Eclipse, etc.).
4. Insomnia o Postman para pruebas de API.

## Configuración del proyecto

1. Clona este repositorio:
   ```bash
   git clone https://github.com/DiegoFranco16/foro-hub.git
   ```
2. Configura tu base de datos en el archivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/forohub_api
   spring.datasource.username=root
   spring.datasource.password=root

   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true

   spring.flyway.baseline-on-migrate=true
   spring.flyway.baseline-version=1

   api.security.secret=${JWT_SECRET:123456}
   ```
3. Crea la base de datos en MySQL:
   ```sql
   CREATE DATABASE forohub_api;
   ```
4. Ejecuta el proyecto desde tu IDE o mediante:
   ```bash
   ./mvnw spring-boot:run
   ```
5. Accede a la documentación Swagger en:
   ```
http://localhost:8080/swagger-ui/index.html
   ```

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
