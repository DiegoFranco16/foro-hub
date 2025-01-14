package com.forohub.api_fh.controller;

import com.forohub.api_fh.domain.ValidacionException;
import com.forohub.api_fh.domain.curso.CursoRepository;
import com.forohub.api_fh.domain.topico.*;
import com.forohub.api_fh.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


import java.net.URI;

@RestController
@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private RegistroDeTopicos registroDeTopicos;
    @Autowired
    private ListadoDeTopicos listadoDeTopicos;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos,
                                                          UriComponentsBuilder uriComponentsBuilder) {
        var datosRespuesta = registroDeTopicos.registrar(datos);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosRespuesta.id()).toUri();
        return ResponseEntity.created(uri).body(datosRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion) {
        var topicos = listadoDeTopicos.listar(curso, anio, paginacion);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerDetalleTopico(@PathVariable Long id) {
        // Buscar el tópico por ID
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ValidacionException("El tópico con ID " + id + " no existe"));

        // Mapear los detalles del tópico al DTO
        var detalleTopico = new DatosDetalleTopico(
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );

        return ResponseEntity.ok(detalleTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id) {
        registroDeTopicos.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

