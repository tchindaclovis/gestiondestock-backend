package com.tchindaClovis.gestiondestock.repository;

import com.tchindaClovis.gestiondestock.model.CommandeFournisseur;
import com.tchindaClovis.gestiondestock.model.LigneCommandeClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {
}
