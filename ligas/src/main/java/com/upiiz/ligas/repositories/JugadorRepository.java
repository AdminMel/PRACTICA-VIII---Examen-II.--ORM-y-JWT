package com.upiiz.ligas.repositories;

import com.upiiz.ligas.entities.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
}
