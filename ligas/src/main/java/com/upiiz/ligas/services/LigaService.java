package com.upiiz.ligas.services;

import com.upiiz.ligas.entities.Liga;
import com.upiiz.ligas.repositories.LigaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LigaService {

    private final LigaRepository ligaRepository;

    public List<Liga> findAll() {
        return ligaRepository.findAll();
    }

    public Liga save(Liga liga) {
        return ligaRepository.save(liga);
    }
}
