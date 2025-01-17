package com.forohub.api_fh.controller;

import com.forohub.api_fh.domain.usuario.DatosActualizarUsuario;
import com.forohub.api_fh.domain.usuario.DatosRegistroUsuario;
import com.forohub.api_fh.domain.usuario.DatosRespuestaUsuario;
import com.forohub.api_fh.domain.usuario.GestionDeUsuarios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private GestionDeUsuarios gestionDeUsuarios;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> registrar(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriBuilder) {
        var respuesta = gestionDeUsuarios.registrar(datos);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(respuesta.id()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<DatosRespuestaUsuario>> listar(@PageableDefault(size = 10, sort = "nombre") Pageable paginacion) {
        return ResponseEntity.ok(gestionDeUsuarios.listar(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> obtenerUsuarioPorId(@PathVariable Long id) {
        var usuario = gestionDeUsuarios.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizarUsuario datos) {
        return ResponseEntity.ok(gestionDeUsuarios.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        gestionDeUsuarios.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado con éxito");
    }
}
