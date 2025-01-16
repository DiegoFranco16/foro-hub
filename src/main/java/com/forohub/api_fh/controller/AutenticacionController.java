package com.forohub.api_fh.controller;

import com.forohub.api_fh.domain.usuario.DatosLogin;
import com.forohub.api_fh.domain.usuario.Usuario;
import com.forohub.api_fh.infra.security.DatosJWTToken;
import com.forohub.api_fh.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> realizarLogin(@RequestBody @Valid DatosLogin datos) {
        var authToken = new UsernamePasswordAuthenticationToken(datos.correoElectronico(), datos.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTToken(JWTtoken));
    }

    //Otra forma de hacerlo
//    @PostMapping
//    public ResponseEntity<DatosJWTToken> login(@RequestBody @Valid DatosLogin datosLogin) {
//        var authenticationToken = new UsernamePasswordAuthenticationToken(datosLogin.correoElectronico(), datosLogin.contrasena());
//        var authentication = authenticationManager.authenticate(authenticationToken);
//
//        var usuario = (Usuario) authentication.getPrincipal();
//        var token = tokenService.generarToken(usuario);
//
//        return ResponseEntity.ok(new DatosJWTToken(token));
//    }
}

