package com.forohub.api_fh.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @Email(message = "El correo debe ser válido") String correoElectronico,
        @NotBlank(message = "La contraseña es obligatoria") String contrasena,
        @NotNull(message = "El perfil es obligatorio") Long perfilId
) {}