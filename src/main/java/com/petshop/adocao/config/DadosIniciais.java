package com.petshop.adocao.config;

import com.petshop.adocao.domain.Animal;
import com.petshop.adocao.domain.Especie;
import com.petshop.adocao.domain.Porte;
import com.petshop.adocao.domain.Sexo;
import com.petshop.adocao.domain.StatusAdocao;
import com.petshop.adocao.repository.AnimalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DadosIniciais implements CommandLineRunner {

    private final AnimalRepository animalRepository;

    public DadosIniciais(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void run(String... args) {
        if (animalRepository.count() > 0) {
            return;
        }

        animalRepository.save(new Animal(
                "James",
                Especie.GATO,
                "Siames",
                11,
                Sexo.MACHO,
                Porte.PEQUENO,
                "Raivoso e odeia toque.",
                StatusAdocao.DISPONIVEL
        ));

        animalRepository.save(new Animal(
                "Luna",
                Especie.CACHORRO,
                "Pasto Alemão",
                5,
                Sexo.FEMEA,
                Porte.MEDIO,
                "Carente e precisa de local espaçoso.",
                StatusAdocao.DISPONIVEL
        ));
    }
}
