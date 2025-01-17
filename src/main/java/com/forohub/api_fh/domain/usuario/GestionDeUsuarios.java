package com.forohub.api_fh.domain.usuario;

import com.forohub.api_fh.domain.ValidacionException;
import com.forohub.api_fh.domain.perfil.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GestionDeUsuarios {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    public DatosRespuestaUsuario registrar(DatosRegistroUsuario datos) {
        if (usuarioRepository.existsByCorreoElectronico(datos.correoElectronico())) {
            throw new ValidacionException("El correo ya está registrado");
        }

        var perfil = perfilRepository.findById(datos.perfilId())
                .orElseThrow(() -> new ValidacionException("El perfil no existe"));

        var usuario = new Usuario(datos.nombre(), datos.correoElectronico(), datos.contrasena(), perfil);
        usuarioRepository.save(usuario);

        return new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                perfil.getNombre()
        );
    }

    public Page<DatosRespuestaUsuario> listar(Pageable paginacion) {
        return usuarioRepository.findAll(paginacion).map(usuario ->
                new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico(), usuario.getPerfil().getNombre())
        );
    }

    public DatosRespuestaUsuario obtenerUsuarioPorId(Long id) {
        // Buscar el usuario
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El usuario con ID " + id + " no existe"));

        // Retornar el DTO con los datos del usuario
        return new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getPerfil().getNombre()
        );
    }

    public DatosRespuestaUsuario actualizar(Long id, DatosActualizarUsuario datos) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El usuario no existe"));

        usuario.setNombre(datos.nombre());
        usuario.setCorreoElectronico(datos.correoElectronico());
        usuario.setContrasena(datos.contrasena());
        usuario.setPerfil(perfilRepository.findById(datos.perfilId())
                .orElseThrow(() -> new ValidacionException("El perfil no existe")));

        usuarioRepository.save(usuario);

        return new DatosRespuestaUsuario(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),
                usuario.getPerfil().getNombre()
        );
    }

    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ValidacionException("El usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }
}
