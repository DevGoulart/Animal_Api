package com.petshop.adocao.service;

import com.petshop.adocao.domain.Animal;
import com.petshop.adocao.domain.StatusAdocao;
import com.petshop.adocao.dto.AnimalRequest;
import com.petshop.adocao.dto.AnimalResponse;
import com.petshop.adocao.exception.RecursoNaoEncontradoException;
import com.petshop.adocao.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Transactional
    public AnimalResponse cadastrar(AnimalRequest request) {
        Animal animal = new Animal(
                request.getNome().trim(),
                request.getEspecie(),
                request.getRaca().trim(),
                request.getIdade(),
                request.getSexo(),
                request.getPorte(),
                request.getDescricao(),
                request.getStatus() != null ? request.getStatus() : StatusAdocao.DISPONIVEL
        );
        return AnimalResponse.from(animalRepository.save(animal));
    }

    @Transactional(readOnly = true)
    public List<AnimalResponse> listarTodos() {
        return animalRepository.findAll()
                .stream()
                .map(AnimalResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public AnimalResponse buscarPorId(Long id) {
        return AnimalResponse.from(buscarEntidade(id));
    }

    @Transactional
    public AnimalResponse atualizar(Long id, AnimalRequest request) {
        Animal animal = buscarEntidade(id);
        animal.setNome(request.getNome().trim());
        animal.setEspecie(request.getEspecie());
        animal.setRaca(request.getRaca().trim());
        animal.setIdade(request.getIdade());
        animal.setSexo(request.getSexo());
        animal.setPorte(request.getPorte());
        animal.setDescricao(request.getDescricao());
        if (request.getStatus() != null) {
            animal.setStatus(request.getStatus());
        }
        return AnimalResponse.from(animalRepository.save(animal));
    }

    @Transactional
    public void excluir(Long id) {
        Animal animal = buscarEntidade(id);
        animalRepository.delete(animal);
    }

    private Animal buscarEntidade(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Animal não encontrado com o ID " + id));
    }
}
