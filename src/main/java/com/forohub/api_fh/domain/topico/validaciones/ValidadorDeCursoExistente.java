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
        if (curso == null) {
            return; // Si el curso es nulo, no hacemos nada.
        }

        boolean existe;

        if (curso.startsWith("=")) {
            // Validación exacta (se espera el prefijo "=" para identificar este caso)
            String nombreExacto = curso.substring(1); // Eliminar el prefijo "="
            existe = cursoRepository.existsByNombre(nombreExacto);
        } else {
            // Validación por coincidencia parcial
            existe = !cursoRepository.findByNombreContaining(curso).isEmpty();
        }

        if (!existe) {
            throw new ValidacionException("No se encontró ningún curso que coincida con: " + curso);
        }
    }
}

