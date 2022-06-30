package com.association.afp.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "retirocliente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Retirocliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id ;

    @ManyToOne(  optional = false)
    @JoinColumn(name = "idcliente", nullable = false)
    private Cliente idcliente;

    @ManyToOne( optional = false)
    @JoinColumn(name = "idafp", nullable = false)
    private Afp idafp;

    @Column(name = "montoretiro", nullable = false, precision = 14, scale = 4)
    private BigDecimal montoretiro;

    @Column(name = "fecharetiro", nullable = false)
    private Instant fecharetiro;


}