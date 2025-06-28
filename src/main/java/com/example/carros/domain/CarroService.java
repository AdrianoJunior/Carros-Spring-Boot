package com.example.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;


@Service
public class CarroService {

    @Autowired
    private CarroRepository repository;

    public Iterable<Carro> getCarros() {
        return repository.findAll();
    }

    public Optional<Carro> getCarroById(Long id) {
        return repository.findById(id);
    }

    public List<Carro> getCarrosByTipo(String tipo) {

        return repository.findByTipo(tipo);

    }

    public Carro save(Carro carro) {
        return repository.save(carro);
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Carro> optional = getCarroById(id);

        if (optional.isPresent()) {
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + id + " atualizado com sucesso: " + db.getId());

            repository.save(db);
            return db;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        Optional<Carro> optional = getCarroById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
        }
    }
}
