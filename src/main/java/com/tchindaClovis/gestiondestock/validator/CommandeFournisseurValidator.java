package com.tchindaClovis.gestiondestock.validator;

import com.tchindaClovis.gestiondestock.dto.CommandeClientDto;
import com.tchindaClovis.gestiondestock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {
    public static List<String> validate(CommandeFournisseurDto commandeFournisseurDto){
        List< String> errors = new ArrayList<>();
        if(commandeFournisseurDto == null || !StringUtils.hasLength(commandeFournisseurDto.getCode())){
            errors.add("Veuillez renseigner le code de la CommandeFournisseur");
        }
        return errors;
    }
}
