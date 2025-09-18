package com.tchindaClovis.gestiondestock.services.impl;

import com.tchindaClovis.gestiondestock.dto.CommandeClientDto;
import com.tchindaClovis.gestiondestock.dto.LigneCommandeClientDto;
import com.tchindaClovis.gestiondestock.exception.EntityNotFoundException;
import com.tchindaClovis.gestiondestock.exception.ErrorCodes;
import com.tchindaClovis.gestiondestock.exception.InvalidEntityException;
import com.tchindaClovis.gestiondestock.model.Article;
import com.tchindaClovis.gestiondestock.model.Client;
import com.tchindaClovis.gestiondestock.model.CommandeClient;
import com.tchindaClovis.gestiondestock.model.LigneCommandeClient;
import com.tchindaClovis.gestiondestock.repository.ArticleRepository;
import com.tchindaClovis.gestiondestock.repository.ClientRepository;
import com.tchindaClovis.gestiondestock.repository.CommandeClientRepository;
import com.tchindaClovis.gestiondestock.repository.LigneCommandeClientRepository;
import com.tchindaClovis.gestiondestock.services.CommandeClientService;
import com.tchindaClovis.gestiondestock.validator.CommandeClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {
    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
                                     LigneCommandeClientRepository ligneCommandeClientRepository,
                                     ClientRepository clientRepository, ArticleRepository articleRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto dto) {

        List<String> errors = CommandeClientValidator.validate(dto);

        if(!errors.isEmpty()){
            log.error(" Commande client n'est pas valide", dto);
            throw new InvalidEntityException("La commande client n'est pas valide",
                    ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }
        Optional<Client> client = clientRepository.findById(dto.getClient().getId());
        if(!client.isEmpty()) {
            log.error(" Client with ID {} was not found in the DB", dto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID" + dto.getClient().getId() +
                    "n'a été trouvé dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if(dto.getLigneCommandeClients() != null) {
            dto.getLigneCommandeClients().forEach(ligCmdClt -> {
                if (ligCmdClt.getArticle() != null) {
                    Optional<Article> article = articleRepository.findById(ligCmdClt.getArticle().getId());
                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID " + ligCmdClt.getArticle().getId() +
                                " n'existe pas");
                    }
                }else{
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });
            }

            if (!articleErrors.isEmpty()){
              log.warn("");
              throw new InvalidEntityException("L'article n'existe pas dans la BDD",
                      ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
            }

            CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

            if(dto.getLigneCommandeClients() != null) {
                dto.getLigneCommandeClients().forEach(ligCmdClt -> {
                    LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt); //transformer l'objet DTO en entité
                    ligneCommandeClient.setCommandeClient(savedCmdClt); // lier la ligne de commande à la commande enregistrée
                    ligneCommandeClientRepository.save(ligneCommandeClient);
                });
            }
            return CommandeClientDto.fromEntity(savedCmdClt);
        }


    @Override
    public CommandeClientDto findById(Integer id) {
        if(id == null){
            log.error("Commande client ID is NULL");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client n'a été trouvé avec l'ID " + id,ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if(!StringUtils.hasLength(code)){
            log.error("Commande client CODE is null");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client n'a été trouvé avec le CODE " +
                                code,ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if(id == null){
            log.error("Commande client ID is NULL");
            return ;
        }
        commandeClientRepository.deleteById(id);
    }
}
