package com.petshop.adocao.dto;

import com.petshop.adocao.domain.Especie;
import com.petshop.adocao.domain.Porte;
import com.petshop.adocao.domain.Sexo;
import com.petshop.adocao.domain.StatusAdocao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AnimalRequest {

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 80, message = "O nome deve ter no máximo 80 caracteres")
    private String nome;

    @NotNull(message = "A espécie é obrigatória")
    private Especie especie;

    @NotBlank(message = "A raça é obrigatória")
    @Size(max = 80, message = "A raça deve ter no máximo 80 caracteres")
    private String raca;

    @NotNull(message = "A idade é obrigatória")
    @Min(value = 0, message = "A idade não pode ser negativa")
    private Integer idade;

    @NotNull(message = "O sexo é obrigatório")
    private Sexo sexo;

    @NotNull(message = "O porte é obrigatório")
    private Porte porte;

    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    private StatusAdocao status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Porte getPorte() {
        return porte;
    }

    public void setPorte(Porte porte) {
        this.porte = porte;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusAdocao getStatus() {
        return status;
    }

    public void setStatus(StatusAdocao status) {
        this.status = status;
    }
}
