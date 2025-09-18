package com.tchindaClovis.gestiondestock.services;

import com.tchindaClovis.gestiondestock.dto.UtilisateurDto;
import java.util.List;

public interface UtilisateurService {
    UtilisateurDto save(UtilisateurDto dto);
    UtilisateurDto findById(Integer id);
    UtilisateurDto findByNomUtilisateur(String  nomUtilisateur);
    UtilisateurDto findByPrenomUtilisateur(String prenomUtilisateur);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
}
