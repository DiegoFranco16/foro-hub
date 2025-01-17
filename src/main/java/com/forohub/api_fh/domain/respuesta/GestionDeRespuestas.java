package com.forohub.api_fh.domain.respuesta;

import com.forohub.api_fh.domain.ValidacionException;
import com.forohub.api_fh.domain.topico.TopicoRepository;
import com.forohub.api_fh.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GestionDeRespuestas {
    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosRespuesta registrar(DatosRegistroRespuesta datos) {
        var autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new ValidacionException("El autor no existe"));

        var topico = topicoRepository.findById(datos.topicoId())
                .orElseThrow(() -> new ValidacionException("El tópico no existe"));

        var respuesta = new Respuesta(datos, autor, topico);
        respuestaRepository.save(respuesta);

        return new DatosRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                autor.getNombre(),
                topico.getTitulo()
        );
    }

    public Page<DatosRespuesta> listar(Pageable paginacion) {
        return respuestaRepository.findAll(paginacion).map(respuesta ->
                new DatosRespuesta(
                        respuesta.getId(),
                        respuesta.getMensaje(),
                        respuesta.getAutor().getNombre(),
                        respuesta.getTopico().getTitulo()
                )
        );
    }

    public DatosRespuesta actualizar(Long id, DatosActualizarRespuesta datos) {
        var respuesta = respuestaRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("La respuesta no existe"));

        respuesta.setMensaje(datos.mensaje());
        respuestaRepository.save(respuesta);

        return new DatosRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getAutor().getNombre(),
                respuesta.getTopico().getTitulo()
        );
    }

    public void eliminar(Long id) {
        if (!respuestaRepository.existsById(id)) {
            throw new ValidacionException("La respuesta no existe");
        }
        respuestaRepository.deleteById(id);
    }
}
