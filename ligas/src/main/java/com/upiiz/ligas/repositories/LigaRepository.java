package com.upiiz.ligas.repositories;

import com.upiiz.ligas.entities.Liga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigaRepository extends JpaRepository<Liga, Long> {
}
