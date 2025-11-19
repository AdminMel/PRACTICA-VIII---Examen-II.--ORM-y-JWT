package com.upiiz.ligas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    private String ciudad;

    // Muchos equipos en una liga
    @ManyToOne
    @JoinColumn(name = "liga_id")
    @JsonIgnore
    private Liga liga;

    // Un entrenador por equipo (simple)
    @OneToOne(mappedBy = "equipo", cascade = CascadeType.ALL)
    private Entrenador entrenador;

    // Muchos jugadores en un equipo
    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Jugador> jugadores;
}
