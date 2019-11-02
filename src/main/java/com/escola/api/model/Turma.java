package com.escola.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

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
@Table(name = "turma")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome do Professor é obrigatório")
	private String nomeDoProfessor;
	
	@NotBlank(message = "Série é obrigatório")
	private String serie;
	
	@NotBlank(message = "Título é obrigatório")
	private String titulo;
	
	@JsonIgnoreProperties("turma")
	@Valid
	@OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE)
	private List<Aluno> alunos;
	
	@Transient
	private Integer quantidadeDeAlunos;
	
	@PostLoad
	public void countQuantidadeDeAlunos() {
		this.quantidadeDeAlunos = this.alunos.size();
	}
	
}
