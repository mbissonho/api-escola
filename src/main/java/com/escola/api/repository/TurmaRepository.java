package com.escola.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.escola.api.model.Turma;

public interface TurmaRepository extends PagingAndSortingRepository<Turma, Long> {

}
