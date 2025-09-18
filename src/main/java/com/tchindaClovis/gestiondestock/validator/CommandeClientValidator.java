package com.tchindaClovis.gestiondestock.validator;
import com.tchindaClovis.gestiondestock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {
    public static List<String> validate(CommandeClientDto commandeClientDto){
        List< String> errors = new ArrayList<>();
        if(commandeClientDto == null || !StringUtils.hasLength(commandeClientDto.getCode())){
            errors.add("Veuillez renseigner le code de la CommandeClient");
        }
        return errors;
    }
}
