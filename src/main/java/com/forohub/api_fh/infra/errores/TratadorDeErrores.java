package com.forohub.api_fh.infra.errores;

import com.forohub.api_fh.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    // Manejo de errores 404 para entidades no encontradas
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> manejarError404() {
        return ResponseEntity.notFound().build();
    }

    // Manejo de errores 400 por validaciones fallidas (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> manejarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream()
                .map(DatosErrorValidacion::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    // Manejo de errores personalizados de validación
    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<String> manejarErrorDeValidacion(ValidacionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // Clase record para estructurar los errores de validación
    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
