package com.forohub.api_fh.domain.topico;

import com.forohub.api_fh.domain.ValidacionException;
import com.forohub.api_fh.domain.topico.validaciones.ValidadorDuplicadosTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActualizacionDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private ValidadorDuplicadosTopico validadorDuplicados;

    public DatosListadoTopico actualizar(Long id, DatosActualizarTopico datos) {
        // Verificar si el tópico existe
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El tópico con ID " + id + " no existe"));

        var validarDatos = new DatosRegistroTopico(datos.titulo(), datos.mensaje(), null, null);
        // Validar que no haya duplicados
        validadorDuplicados.validar(validarDatos);

        // Actualizar los campos permitidos
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());

        // Guardar el tópico actualizado
        topicoRepository.save(topico);

        // Devolver los datos actualizados en un DTO
        return new DatosListadoTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}