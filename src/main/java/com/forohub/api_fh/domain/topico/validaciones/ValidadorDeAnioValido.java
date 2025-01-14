package com.forohub.api_fh.domain.topico.validaciones;

import com.forohub.api_fh.domain.ValidacionException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidadorDeAnioValido implements ValidadorDeListado {

    @Override
    public void validar(String curso, Integer anio) {
        if (anio != null && (anio < 2000 || anio > LocalDate.now().getYear())) {
            throw new ValidacionException("El año '" + anio + "' no es válido");
        }
    }
}