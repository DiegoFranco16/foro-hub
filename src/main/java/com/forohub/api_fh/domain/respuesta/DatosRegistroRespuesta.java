package com.forohub.api_fh.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotBlank(message = "El contenido es obligatorio") String mensaje,
        @NotNull(message = "El ID del autor es obligatorio") Long autorId,
        @NotNull(message = "El ID del tópico es obligatorio") Long topicoId
) {}