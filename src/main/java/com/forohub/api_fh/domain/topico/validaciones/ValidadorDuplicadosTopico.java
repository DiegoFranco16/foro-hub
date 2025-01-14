package com.forohub.api_fh.domain.topico.validaciones;

import com.forohub.api_fh.domain.ValidacionException;
import com.forohub.api_fh.domain.topico.DatosRegistroTopico;
import com.forohub.api_fh.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDuplicadosTopico implements ValidadorDeTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroTopico datos) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidacionException("Ya existe un tópico con el mismo título y mensaje");
        }
    }
}

