package com.petshop.adocao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petshop.adocao.domain.Especie;
import com.petshop.adocao.domain.Porte;
import com.petshop.adocao.domain.Sexo;
import com.petshop.adocao.domain.StatusAdocao;
import com.petshop.adocao.dto.AnimalRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveListarAnimaisIniciais() throws Exception {
        mockMvc.perform(get("/animais"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void deveCadastrarConsultarAtualizarEExcluirAnimal() throws Exception {
        AnimalRequest cadastro = new AnimalRequest();
        cadastro.setNome("Mel");
        cadastro.setEspecie(Especie.CACHORRO);
        cadastro.setRaca("SRD");
        cadastro.setIdade(1);
        cadastro.setSexo(Sexo.FEMEA);
        cadastro.setPorte(Porte.MEDIO);
        cadastro.setDescricao("Dócil e brincalhona.");
        cadastro.setStatus(StatusAdocao.DISPONIVEL);

        String respostaCadastro = mockMvc.perform(post("/animais")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastro)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome", is("Mel")))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Long id = objectMapper.readTree(respostaCadastro).get("id").asLong();

        mockMvc.perform(get("/animais/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.raca", is("SRD")));

        cadastro.setStatus(StatusAdocao.ADOTADO);
        cadastro.setIdade(2);

        mockMvc.perform(put("/animais/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cadastro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("ADOTADO")))
                .andExpect(jsonPath("$.idade", is(2)));

        mockMvc.perform(delete("/animais/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/animais/" + id))
                .andExpect(status().isNotFound());
    }
}
