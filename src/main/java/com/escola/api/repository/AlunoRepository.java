package com.escola.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.escola.api.model.Aluno;

public interface AlunoRepository extends PagingAndSortingRepository<Aluno, Long>{

}
