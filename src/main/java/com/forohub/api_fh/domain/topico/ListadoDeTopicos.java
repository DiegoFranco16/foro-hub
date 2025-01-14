package com.forohub.api_fh.domain.topico;

import com.forohub.api_fh.domain.ValidacionException;
import com.forohub.api_fh.domain.curso.Curso;
import com.forohub.api_fh.domain.curso.CursoRepository;
import com.forohub.api_fh.domain.topico.validaciones.ValidadorDeListado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListadoDeTopicos {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private List<ValidadorDeListado> validadores;

    public Page<DatosListadoTopico> listar(String curso, Integer anio, Pageable paginacion) {
        // Ejecutar validaciones
        validadores.forEach(v -> v.validar(curso, anio));

        Page<Topico> topicos;

        if (curso != null && anio != null) {
            // Buscar coincidencias parciales en cursos por nombre y año
            var nombresDeCursos = cursoRepository.findByNombreContaining(curso).stream()
                    .map(Curso::getNombre)
                    .toList();

            if (nombresDeCursos.isEmpty()) {
                throw new ValidacionException("No se encontraron cursos que contengan: " + curso);
            }

            topicos = topicoRepository.findByCursoNombreInAndFechaCreacionYearAndStatusTrue(nombresDeCursos, anio, paginacion);
        } else if (curso != null) {
            // Buscar coincidencias parciales en cursos por nombre
            var nombresDeCursos = cursoRepository.findByNombreContaining(curso).stream()
                    .map(Curso::getNombre)
                    .toList();

            if (nombresDeCursos.isEmpty()) {
                throw new ValidacionException("No se encontraron cursos que contengan: " + curso);
            }

            topicos = topicoRepository.findByCursoNombreInAndStatusTrue(nombresDeCursos, paginacion);
        } else if (anio != null) {
            // Filtrar solo por año
            topicos = topicoRepository.findByFechaCreacionYearAndStatusTrue(anio, paginacion);
        } else {
            // Sin filtros, solo activos
            topicos = topicoRepository.findByStatusTrue(paginacion);
        }

        // Mapear los tópicos a DTOs
        return topicos.map(DatosListadoTopico::new);
    }
}

