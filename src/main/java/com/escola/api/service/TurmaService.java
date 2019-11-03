package com.escola.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.escola.api.model.Turma;
import com.escola.api.repository.TurmaRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TurmaService {

	@Autowired
	private TurmaRepository repository;

	public List<Turma> findAll() {
		log.info("Buscando turmas");
		return this.repository.findAll();
	}

	public Turma create(Turma turma) {
		log.info("Criando nova turma");
		return this.repository.save(turma);
	}

	public Turma update(Long id, Turma turma) {
		log.info("Atualizando turma");
		Turma turmaSaved = findAlreadySaved(id);
		BeanUtils.copyProperties(turma, turmaSaved, "id");
		return repository.save(turmaSaved);
	}
	
	public void deleteById(Long id) {
		log.info("Deletando turma pelo id");
		Turma turmaSaved = findAlreadySaved(id);
		this.repository.delete(turmaSaved);
	}
	
	public Turma findById(Long id) {
		log.info("Buscando turma pelo id");
		Turma turmaSaved = this.findAlreadySaved(id);
		return turmaSaved;
	}
	
	private Turma findAlreadySaved(Long id) {
		Optional<Turma> turmaOp = repository.findById(id);
		if(!turmaOp.isPresent()) {
			log.info("Turma de id " + id + " não encontrada");
			throw new EmptyResultDataAccessException(1);
		}
		return turmaOp.get();
	}
}
