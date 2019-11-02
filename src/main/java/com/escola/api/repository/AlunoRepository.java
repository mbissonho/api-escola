package com.escola.api.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.escola.api.model.Aluno;
import com.escola.api.model.Turma;

public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long>{

	public List<Aluno> findByTurma(Turma turma);
	
}
