package com.escola.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.escola.api.model.Aluno;
import com.escola.api.repository.AlunoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AlunoService {

	@Autowired
	private AlunoRepository repository;
	
	public List<Aluno> findAll() {
		log.info("Buscando alunos");
		return this.repository.findAll();
	}

	public Aluno create(Aluno aluno) {
		log.info("Criando novo aluno");
		return this.repository.save(aluno);
	}

	public Aluno update(Long id, Aluno aluno) {
		log.info("Atualizando aluno");
		Aluno alunoSaved = findAlreadySaved(id);
		BeanUtils.copyProperties(aluno, alunoSaved, "id");
		return repository.save(alunoSaved);
	}
	
	public void deleteById(Long id) {
		log.info("Deletando aluno pelo id");
		Aluno alunoSaved = findAlreadySaved(id);
		this.repository.delete(alunoSaved);
	}
	
	public Aluno findById(Long id) {
		log.info("Buscando aluno pelo id");
		Aluno alunoSaved = this.findAlreadySaved(id);
		return alunoSaved;
	}

	private Aluno findAlreadySaved(Long id) {
		Optional<Aluno> alunoOp = repository.findById(id);
		if(!alunoOp.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return alunoOp.get();
	}

}
