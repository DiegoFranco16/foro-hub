CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT,
    fecha_creacion DATETIME,
    status VARCHAR(50),
    autor_id BIGINT,
    curso_id BIGINT,
    CONSTRAINT fk_topico_usuario FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topico_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);