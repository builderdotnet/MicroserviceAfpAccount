package com.association.afp.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "afp")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Afp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "descripcion", nullable = false, length = 250)
    private String descripcion;



}