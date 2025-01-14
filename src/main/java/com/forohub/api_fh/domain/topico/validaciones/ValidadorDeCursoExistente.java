package com.forohub.api_fh.domain.topico.validaciones;

import com.forohub.api_fh.domain.ValidacionException;
import com.forohub.api_fh.domain.curso.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeCursoExistente implements ValidadorDeListado {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void validar(String curso, Integer anio) {
        if (curso != null && !cursoRepository.existsByNombre(curso)) {
            throw new ValidacionException("El curso '" + curso + "' no existe");
        }
    }
}
