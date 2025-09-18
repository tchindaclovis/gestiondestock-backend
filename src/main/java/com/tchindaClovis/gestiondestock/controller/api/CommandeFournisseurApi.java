package com.tchindaClovis.gestiondestock.controller.api;

import com.tchindaClovis.gestiondestock.dto.CommandeFournisseurDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tchindaClovis.gestiondestock.utils.Constants.*;

@Tag(name = "CommandeFournisseurs", description = "API de gestion des commandeFournisseurs")
public interface CommandeFournisseurApi {
    @PostMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/create")
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);

    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}")
    CommandeFournisseurDto findById(@PathVariable Integer id);

    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/{codeCommandeFournisseur}")
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/all")
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur}")
    void delete(@PathVariable("idCommandeFournisseur") Integer id);

}
