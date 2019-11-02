package com.escola.api.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotNull(message = "Quantidade de Faltas é obrigatório")
	private Integer quantidadeDeFaltas;
	
	@NotNull(message = "Média de Notas é obrigatório")
	private BigDecimal mediaDeNotas;
	
	@JsonIgnoreProperties("alunos")
	@ManyToOne()
	@JoinColumn(name = "turma_id")
	@NotNull(message = "Turma é obrigatório")
	private Turma turma;
}
