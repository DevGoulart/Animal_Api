package com.petshop.adocao.dto;

import com.petshop.adocao.domain.Animal;
import com.petshop.adocao.domain.Especie;
import com.petshop.adocao.domain.Porte;
import com.petshop.adocao.domain.Sexo;
import com.petshop.adocao.domain.StatusAdocao;

public class AnimalResponse {

    private Long id;
    private String nome;
    private Especie especie;
    private String raca;
    private Integer idade;
    private Sexo sexo;
    private Porte porte;
    private String descricao;
    private StatusAdocao status;

    public AnimalResponse() {
    }

    public static AnimalResponse from(Animal animal) {
        AnimalResponse response = new AnimalResponse();
        response.id = animal.getId();
        response.nome = animal.getNome();
        response.especie = animal.getEspecie();
        response.raca = animal.getRaca();
        response.idade = animal.getIdade();
        response.sexo = animal.getSexo();
        response.porte = animal.getPorte();
        response.descricao = animal.getDescricao();
        response.status = animal.getStatus();
        return response;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public Porte getPorte() {
        return porte;
    }

    public String getDescricao() {
        return descricao;
    }

    public StatusAdocao getStatus() {
        return status;
    }
}
