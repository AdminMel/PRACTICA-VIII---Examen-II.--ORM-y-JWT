package com.upiiz.ligas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    private String posicion;

    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    @JsonIgnore
    private Equipo equipo;
}
