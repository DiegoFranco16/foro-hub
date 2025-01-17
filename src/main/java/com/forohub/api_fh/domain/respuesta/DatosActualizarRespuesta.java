package com.forohub.api_fh.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarRespuesta(
        @NotBlank(message = "El contenido es obligatorio") String mensaje
) {}
