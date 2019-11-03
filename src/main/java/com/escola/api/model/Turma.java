package com.escola.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome do Professor é obrigatório")
	private String nomeDoProfessor;
	
	@NotNull(message = "Série é obrigatório")
	private Integer serie;
	
	@NotNull(message = "Título é obrigatório")
	private Integer titulo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Aluno> alunos;
	
	@Transient
	private Integer quantidadeDeAlunos;
	
	@PostLoad
	public void countQuantidadeDeAlunos() {
		this.quantidadeDeAlunos = this.alunos.size();
	}
	
	@PreUpdate
	@PrePersist
	public void removeWhiteSpace() {
		this.nomeDoProfessor = this.nomeDoProfessor.trim();
	}
	
}
