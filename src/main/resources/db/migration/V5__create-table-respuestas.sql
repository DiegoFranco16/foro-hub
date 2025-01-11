CREATE TABLE respuestas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    mensaje TEXT,
    fecha_creacion DATETIME,
    solucion BOOLEAN,
    topico_id BIGINT,
    autor_id BIGINT,
    CONSTRAINT fk_respuesta_topico FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respuesta_usuario FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);