package com.forohub.api_fh.domain.usuario;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        String perfil
) {}
