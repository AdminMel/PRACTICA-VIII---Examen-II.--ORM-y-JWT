package com.upiiz.ligas.services;

import com.upiiz.ligas.entities.Entrenador;
import com.upiiz.ligas.repositories.EntrenadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntrenadorService {

    private final EntrenadorRepository entrenadorRepository;

    public List<Entrenador> findAll() {
        return entrenadorRepository.findAll();
    }

    public Entrenador findById(Long id) {
        return entrenadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con id " + id));
    }

    public Entrenador create(Entrenador entrenador) {
        return entrenadorRepository.save(entrenador);
    }

    public Entrenador update(Long id, Entrenador entrenador) {
        Entrenador existente = findById(id);
        entrenador.setId(existente.getId());
        return entrenadorRepository.save(entrenador);
    }

    public void delete(Long id) {
        entrenadorRepository.deleteById(id);
    }
}
