package com.tchindaClovis.gestiondestock.services;

import com.tchindaClovis.gestiondestock.dto.ArticleDto;
import com.tchindaClovis.gestiondestock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {
    CommandeClientDto save(CommandeClientDto dto);
    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
}
