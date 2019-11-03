package com.escola.api.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "turma_id")
	@ManyToOne
	@NotNull(message = "Turma é obrigatório")
	private Turma turma;
	
	@PreUpdate
	@PrePersist
	public void removeWhiteSpace() {
		this.nome = this.nome.trim();
	}
}
