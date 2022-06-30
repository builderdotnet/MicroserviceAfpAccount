package com.association.afp.account.repository;

import com.association.afp.account.domain.Afp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AfpRepository extends JpaRepository<Afp, Integer> {
}