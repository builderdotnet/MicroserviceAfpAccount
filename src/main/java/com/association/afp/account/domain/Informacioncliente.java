package com.association.afp.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "informacioncliente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Informacioncliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(  optional = false)
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente idcliente;

    @ManyToOne( optional = false)
    @JoinColumn(name = "idafp", nullable = false)
    private Afp idafp;

    @Column(name = "montodisponible", nullable = false, precision = 14, scale = 4)
    private BigDecimal montodisponible;

    @Column(name = "fecharetiro", nullable = false)
    private Instant fecharetiro;

    @Column(name = "nrocuenta", nullable = false, length = 250)
    private String nrocuenta;



}