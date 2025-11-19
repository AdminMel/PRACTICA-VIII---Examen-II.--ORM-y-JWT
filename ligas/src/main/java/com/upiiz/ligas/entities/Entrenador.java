package com.upiiz.ligas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "entrenadores")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String nombre;

    private Integer experiencia; // años

    private String nacionalidad;

    @OneToOne
    @JoinColumn(name = "equipo_id")
    @JsonIgnore
    private Equipo equipo;
}
