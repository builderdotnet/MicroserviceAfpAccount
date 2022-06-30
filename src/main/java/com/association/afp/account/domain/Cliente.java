package com.association.afp.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombres", nullable = false, length = 250)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 250)
    private String apellidos;

    @Column(name = "dni", nullable = false)
    private Integer dni;

    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @Column(name = "correo", nullable = false, length = 30)
    private String correo;

    @ManyToOne(  optional = false)
    @JoinColumn(name = "idafp", nullable = false)
    private Afp idafp;


}