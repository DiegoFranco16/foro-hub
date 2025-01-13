package com.forohub.api_fh.domain.topico;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String autor,
        String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
