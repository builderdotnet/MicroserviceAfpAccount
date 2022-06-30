package com.association.afp.account.repository;

import com.association.afp.account.domain.Cliente;
import com.association.afp.account.domain.Informacioncliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InformacionclienteRepository extends JpaRepository<Informacioncliente, Integer> {
     Optional<Informacioncliente > findByidcliente_Dni(Integer dni);
}