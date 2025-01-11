CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    correo_electronico VARCHAR(255) UNIQUE NOT NULL,
    contrasena VARCHAR(255),
    perfil_id BIGINT,
    CONSTRAINT fk_usuario_perfil FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
);