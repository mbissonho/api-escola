package com.escola.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.escola.api.model.Aluno;
import com.escola.api.service.AlunoService;

@RestController
@RequestMapping("/v1/aluno")
public class AlunoController {

	@Autowired
	private AlunoService service;
	
	@GetMapping
	public ResponseEntity<List<Aluno>> findAll(){
		return new ResponseEntity<List<Aluno>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/turma/{turmaId}")
	public ResponseEntity<List<Aluno>> findByTurma(@PathVariable Long turmaId){
		return new ResponseEntity<List<Aluno>>(service.findByTurma(turmaId), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> findById(@PathVariable Long id){
		return new ResponseEntity<Aluno>(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Aluno> create(@RequestBody @Valid Aluno aluno){
		Aluno alunoCreated = service.create(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoCreated);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Aluno> update(@PathVariable Long id, @RequestBody @Valid Aluno aluno){
		return ResponseEntity.ok(service.update(id, aluno));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.service.deleteById(id);
	}
}
