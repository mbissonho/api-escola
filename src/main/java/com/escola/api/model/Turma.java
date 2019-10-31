package com.escola.api.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.escola.api.model.Aluno;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String serie;
	
	private String titulo;
	
	private List<Aluno> alunos;
	
}
