package com.forohub.api_fh.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {


    // Filtrar por curso y año
    @Query("SELECT t FROM Topico t WHERE t.curso.nombre IN :nombres AND YEAR(t.fechaCreacion) = :anio AND t.status = true")
    Page<Topico> findByCursoNombreInAndFechaCreacionYearAndStatusTrue(@Param("nombres") List<String> nombres, @Param("anio") Integer anio, Pageable pageable);

    // Filtrar por cursos
    @Query("SELECT t FROM Topico t WHERE t.curso.nombre IN :nombres AND t.status = true")
    Page<Topico> findByCursoNombreInAndStatusTrue(@Param("nombres") List<String> nombres, Pageable pageable);

    // Filtrar por año
    @Query("SELECT t FROM Topico t WHERE YEAR(t.fechaCreacion) = :anio AND t.status = true")
    Page<Topico> findByFechaCreacionYearAndStatusTrue(@Param("anio") Integer anio, Pageable pageable);

    // Sin filtros, solo tópicos activos
    Page<Topico> findByStatusTrue(Pageable pageable);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);


    // Filtrar por curso y año con coincidencias parciales
    @Query("SELECT t FROM Topico t WHERE t.curso.nombre LIKE %:nombre% AND YEAR(t.fechaCreacion) = :anio AND t.status = true")
    Page<Topico> findByCursoNombreContainingAndFechaCreacionYearAndStatusTrue(@Param("nombre") String nombre, @Param("anio") Integer anio, Pageable pageable);

    // Filtrar por curso con coincidencias parciales
    @Query("SELECT t FROM Topico t WHERE t.curso.nombre LIKE %:nombre% AND t.status = true")
    Page<Topico> findByCursoNombreContainingAndStatusTrue(@Param("nombre") String nombre, Pageable pageable);



}

