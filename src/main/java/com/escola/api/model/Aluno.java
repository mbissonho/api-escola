package com.escola.api.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "turma")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private Integer quantidadeDeFaltas;
	
	private BigDecimal mediaDeNotas;
}
