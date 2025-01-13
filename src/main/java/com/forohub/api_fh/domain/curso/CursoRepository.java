package com.forohub.api_fh.domain.curso;

import org.apache.catalina.startup.ContextRuleSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
