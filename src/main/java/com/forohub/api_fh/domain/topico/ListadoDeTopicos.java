package com.forohub.api_fh.domain.topico;
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
    private List<ValidadorDeListado> validadores;

    public Page<DatosListadoTopico> listar(String curso, Integer anio, Pageable paginacion) {
        // Ejecutar validaciones mediante los validadores registrados
        validadores.forEach(v -> v.validar(curso, anio));

        // Realizar la consulta según los filtros
        Page<Topico> topicos;
        if (curso != null && anio != null) {
            // Filtrar por curso (coincidencias parciales) y año
            topicos = topicoRepository.findByCursoNombreContainingAndFechaCreacionYearAndStatusTrue(curso, anio, paginacion);
        } else if (curso != null) {
            // Filtrar por curso (coincidencias parciales)
            topicos = topicoRepository.findByCursoNombreContainingAndStatusTrue(curso, paginacion);
        } else if (anio != null) {
            // Filtrar solo por año
            topicos = topicoRepository.findByFechaCreacionYearAndStatusTrue(anio, paginacion);
        } else {
            // Sin filtros, solo tópicos activos
            topicos = topicoRepository.findByStatusTrue(paginacion);
        }

        // Mapear los tópicos a DTOs
        return topicos.map(DatosListadoTopico::new);
    }
}

