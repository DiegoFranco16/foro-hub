package com.forohub.api_fh.domain.topico;

import com.forohub.api_fh.domain.curso.Curso;
import com.forohub.api_fh.domain.respuesta.Respuesta;
import com.forohub.api_fh.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank(message = "El título es obligatorio") String titulo,
        @NotBlank(message = "El mensaje es obligatorio") String mensaje,
        @NotNull(message = "El ID del autor es obligatorio") Long autorId,
        @NotNull(message = "El ID del curso es obligatorio") Long cursoId
) {}