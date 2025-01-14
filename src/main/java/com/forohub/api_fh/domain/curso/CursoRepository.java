package com.forohub.api_fh.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    boolean existsByNombre(String nombre);
    @Query("SELECT c FROM Curso c WHERE c.nombre LIKE %:nombre%")
    List<Curso> findByNombreContaining(@Param("nombre") String nombre);
}
