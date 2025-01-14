package com.forohub.api_fh.domain.topico;

import com.forohub.api_fh.domain.ValidacionException;
import com.forohub.api_fh.domain.curso.Curso;
import com.forohub.api_fh.domain.curso.CursoRepository;
import com.forohub.api_fh.domain.topico.validaciones.ValidadorDeTopico;
import com.forohub.api_fh.domain.usuario.Usuario;
import com.forohub.api_fh.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegistroDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidadorDeTopico> validadores;

    public DatosRespuestaTopico registrar(DatosRegistroTopico datos) {
        // Validar reglas de negocio
        validadores.forEach(v -> v.validar(datos));
        Usuario autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new ValidacionException("El autor no existe"));
        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new ValidacionException("El curso no existe"));

        var topico = new Topico(datos, autor, curso);
        topicoRepository.save(topico);

        return new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                autor.getNombre(),
                curso.getNombre()
        );
    }
}
