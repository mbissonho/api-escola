package com.escola.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escola.api.model.Aluno;
import com.escola.api.model.Turma;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	public List<Aluno> findByTurma(Turma turma);
	
}
