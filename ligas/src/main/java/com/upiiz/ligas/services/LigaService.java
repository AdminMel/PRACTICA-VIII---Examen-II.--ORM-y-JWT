package com.upiiz.ligas.services;

import com.upiiz.ligas.entities.Liga;
import com.upiiz.ligas.repositories.LigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio de negocio para la gestión de Ligas.
 * 
 * Encapsula el acceso al repositorio y la lógica básica de CRUD.
 */
@Service
@RequiredArgsConstructor
public class LigaService {

    private final LigaRepository ligaRepository;

    public List<Liga> findAll() {
        return ligaRepository.findAll();
    }

    public Liga findById(Long id) {
        return ligaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Liga no encontrada con id " + id));
    }

    public Liga create(Liga liga) {
        return ligaRepository.save(liga);
    }

    public Liga update(Long id, Liga liga) {
        // Verificamos que exista
        Liga existente = findById(id);
        // Forzamos el ID correcto
        liga.setId(existente.getId());
        return ligaRepository.save(liga);
    }

    public void delete(Long id) {
        ligaRepository.deleteById(id);
    }
}
