package com.forohub.api_fh.domain.respuesta;

public record DatosRespuesta(
        Long id,
        String contenido,
        String autor,
        String topico
) {}
