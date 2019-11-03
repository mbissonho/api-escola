package com.escola.api;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.escola.api.controller.TurmaController;
import com.escola.api.model.Aluno;
import com.escola.api.model.Turma;
import com.escola.api.service.TurmaService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(TurmaController.class)
public class TurmaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
    @Autowired
    private ObjectMapper objectMapper;
	
	@MockBean
	private TurmaService service;
	
	
	@Test
	public void doFindAll_AndShouldReturnTurmas() throws Exception {
		
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(new Aluno(6L, "João", new Integer(6), new BigDecimal(8.9), new Turma()));
		Turma turma = new Turma(4L, "Arthur Nogueira", "7ª", "702",  alunos, new Integer(5));
		List<Turma> turmas = new ArrayList<>();
		turmas.add(turma);
	
		when(service.findAll()).thenReturn(turmas);
			
		mockMvc.perform(get("/v1/turma")
				.contentType(MediaType.APPLICATION_JSON)
		)
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].nomeDoProfessor", is("Arthur Nogueira")));	
	}
	
	
	
	@Test
	public void doFindById_AndShouldReturnTurmaWithGivenId() throws Exception {
		
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(new Aluno(6L, "João", new Integer(6), new BigDecimal(8.9), new Turma()));
		
		when(service.findById(4L))
		.thenReturn(
				new Turma(4L, "Arthur Nogueira", "7ª", "702",  alunos, new Integer(5))
		);
		
		mockMvc.perform(get("/v1/turma/{id}", "4"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.nomeDoProfessor", is("Arthur Nogueira")))
		.andExpect(jsonPath("$.serie", is("7ª")));
	}
	
	@Test
	public void doCreate_AndShouldReturnCreatedTurma() throws Exception {
		
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(new Aluno(6L, "João", new Integer(6), new BigDecimal(8.9), new Turma()));
		
		Turma turma = new Turma(4L, "Arthur Nogueira", "7ª", "702",  alunos, new Integer(5));
		
		
		this.mockMvc.perform(post("/v1/turma")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(turma))
		).andExpect(status().isCreated());
		
		
	}
	
	@Test
	public void doUpdate_AndShouldReturnUpdatedTurma() throws Exception {
		
		List<Aluno> alunos = new ArrayList<>();
		alunos.add(new Aluno(6L, "João", new Integer(6), new BigDecimal(8.9), new Turma()));
		
		Turma turma = new Turma(4L, "Arthur Nogueira", "7ª", "702",  alunos, new Integer(5));
		
		this.mockMvc.perform(put("/v1/turma/{id}", "4")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(turma))
		).andExpect(status().isOk());
		
	}
	
	@Test
	public void doDeleteById_AndShouldReturnHttpStatusNoContent() throws Exception {
		this.mockMvc.perform(delete("/v1/turma/{id}","13")).andExpect(status().isNoContent());
	}
	
}
