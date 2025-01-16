package com.forohub.api_fh.domain.respuesta;

import com.forohub.api_fh.domain.topico.Topico;
import com.forohub.api_fh.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String mensaje;

    private LocalDateTime fechaCreacion;

    private Boolean solucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;


    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        this.solucion = false;
    }
}

