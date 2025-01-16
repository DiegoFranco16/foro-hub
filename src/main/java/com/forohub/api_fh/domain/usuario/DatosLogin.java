package com.forohub.api_fh.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosLogin(
        @NotBlank String correoElectronico,
        @NotBlank String contrasena
) {}

