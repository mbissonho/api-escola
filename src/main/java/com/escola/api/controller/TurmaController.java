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

import com.escola.api.model.Turma;
import com.escola.api.service.TurmaService;



@RestController
@RequestMapping("/v1/turma")
public class TurmaController {

	@Autowired
	private TurmaService service;
	
	@GetMapping
	public ResponseEntity<List<Turma>> findAll(){
		return new ResponseEntity<List<Turma>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turma> findById(@PathVariable Long id){
		return new ResponseEntity<Turma>(service.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Turma> create(@RequestBody @Valid Turma turma){
		Turma turmaCreated = service.create(turma);
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaCreated);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Turma> update(@PathVariable Long id, @RequestBody @Valid Turma turma){
		return ResponseEntity.ok(service.update(id, turma));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		this.service.deleteById(id);
	}
}
