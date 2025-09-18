package com.tchindaClovis.gestiondestock.repository;

import com.tchindaClovis.gestiondestock.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Optional<Utilisateur> findUtilisateurByNomUtilisateur(String nomUtilisateur);
    Optional<Utilisateur> findUtilisateurByPrenomUtilisateur(String prenomUtilisateur);
}
