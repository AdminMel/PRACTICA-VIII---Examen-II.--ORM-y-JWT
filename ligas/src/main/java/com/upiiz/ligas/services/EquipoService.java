package com.upiiz.ligas.services;

import com.upiiz.ligas.entities.Equipo;
import com.upiiz.ligas.repositories.EquipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public Equipo findById(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con id " + id));
    }

    public Equipo create(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Equipo update(Long id, Equipo equipo) {
        Equipo existente = findById(id);
        equipo.setId(existente.getId());
        return equipoRepository.save(equipo);
    }

    public void delete(Long id) {
        equipoRepository.deleteById(id);
    }
}
