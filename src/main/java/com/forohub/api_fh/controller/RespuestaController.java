package com.forohub.api_fh.controller;

import com.forohub.api_fh.domain.respuesta.DatosActualizarRespuesta;
import com.forohub.api_fh.domain.respuesta.DatosRegistroRespuesta;
import com.forohub.api_fh.domain.respuesta.DatosRespuesta;
import com.forohub.api_fh.domain.respuesta.GestionDeRespuestas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private GestionDeRespuestas gestionDeRespuestas;

    @PostMapping
    public ResponseEntity<DatosRespuesta> registrar(@RequestBody @Valid DatosRegistroRespuesta datos, UriComponentsBuilder uriBuilder) {
        var respuesta = gestionDeRespuestas.registrar(datos);
        var uri = uriBuilder.path("/respuestas/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuesta>> listar(@PageableDefault(size = 10, sort = "id") Pageable paginacion) {
        return ResponseEntity.ok(gestionDeRespuestas.listar(paginacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuesta> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarRespuesta datos) {
        return ResponseEntity.ok(gestionDeRespuestas.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        gestionDeRespuestas.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
