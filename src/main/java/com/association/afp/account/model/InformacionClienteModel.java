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
public class InformacionClienteModel {
    @Builder.Default
    private Integer id=null;
    @JsonProperty("cliente")
    @Builder.Default
    private Cliente idcliente = null ;
    @JsonProperty("afp")
    @Builder.Default
    private Afp idafp = null;
    @Builder.Default
    private BigDecimal montodisponible=null;
    @Builder.Default
    private Instant fecharetiro=null;
    @Builder.Default
    private String nrocuenta=null;

}
