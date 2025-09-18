package com.tchindaClovis.gestiondestock.controller.api;

import com.tchindaClovis.gestiondestock.dto.CommandeClientDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.tchindaClovis.gestiondestock.utils.Constants.APP_ROOT;

@Tag(name = "CommandeClients", description = "API de gestion des commandeClients")
public interface CommandeClientApi {
    @PostMapping(value = APP_ROOT + "/commandeClients/create")
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @GetMapping(value = APP_ROOT + "/commandeClients/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer id);

    @GetMapping(value = APP_ROOT + "/commandeClients/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(value = APP_ROOT + "/commandeClients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandeClients/delete/{idCommandeClient}")
    ResponseEntity delete(@PathVariable("idCommandeClient") Integer id);
}
