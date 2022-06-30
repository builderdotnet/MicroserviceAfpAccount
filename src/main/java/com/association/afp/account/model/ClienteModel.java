package com.association.afp.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteModel {
    private Integer id;
    private String nombres;
    private String apellidos;
    private Integer dni;

}
