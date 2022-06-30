package com.association.afp.account.model;

import com.association.afp.account.domain.Afp;
import com.association.afp.account.domain.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetiroClienteModel {
    private Integer dni;

    private String  nrocuenta;

    private BigDecimal montoretiro;

    private Instant fecharetiro;
}
