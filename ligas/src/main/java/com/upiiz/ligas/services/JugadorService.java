package com.upiiz.ligas.services;

import com.upiiz.ligas.entities.Jugador;
import com.upiiz.ligas.repositories.JugadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public List<Jugador> findAll() {
        return jugadorRepository.findAll();
    }

    public Jugador findById(Long id) {
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado con id " + id));
    }

    public Jugador create(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    public Jugador update(Long id, Jugador jugador) {
        Jugador existente = findById(id);
        jugador.setId(existente.getId());
        return jugadorRepository.save(jugador);
    }

    public void delete(Long id) {
        jugadorRepository.deleteById(id);
    }
}
