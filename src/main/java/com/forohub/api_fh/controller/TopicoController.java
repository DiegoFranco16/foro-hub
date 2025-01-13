package com.forohub.api_fh.controller;

import com.forohub.api_fh.domain.curso.CursoRepository;
import com.forohub.api_fh.domain.topico.*;
import com.forohub.api_fh.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;


import java.net.URI;

@RestController
@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private RegistroDeTopicos registroDeTopicos;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        var datosRespuesta = registroDeTopicos.registrar(datos);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosRespuesta.id()).toUri();
        return ResponseEntity.created(uri).body(datosRespuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        registroDeTopicos.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
