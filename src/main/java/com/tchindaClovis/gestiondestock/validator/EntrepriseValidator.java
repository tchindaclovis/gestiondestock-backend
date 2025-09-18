package com.tchindaClovis.gestiondestock.validator;

import com.tchindaClovis.gestiondestock.dto.EntrepriseDto;
import com.tchindaClovis.gestiondestock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {
    public static List<String> validate(EntrepriseDto dto){
        List< String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner le nom de l'entreprise");
            errors.add("Veuillez renseigner la description de l'entreprise");
            errors.add("Veuillez renseigner le Code Fiscal de l'entreprise");
            errors.add("Veuillez renseigner la photo de l'entreprise");
            errors.add("Veuillez renseigner l'email de l'entreprise");
            errors.add("Veuillez renseigner le numero de telephone de l'entreprise");
            errors.add("Veuillez renseigner le site internet de l'entreprise");
            return errors;
        }

        if(!StringUtils.hasLength(dto.getNomEntreprise())){
            errors.add("Veuillez renseigner le nom de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getDescription())){
            errors.add("Veuillez renseigner la description de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getCodeFiscal())){
            errors.add("Veuillez renseigner le Code Fiscal de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getPhoto())){
            errors.add("Veuillez renseigner la photo de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getEmail())){
            errors.add("Veuillez renseigner l'email de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getNumTel())){
            errors.add("Veuillez renseigner le numero de telephone de l'entreprise");
        }
        if(!StringUtils.hasLength(dto.getSiteWeb())){
            errors.add("Veuillez renseigner le site internet de l'entreprise");
        }
        return errors;
    }
}
