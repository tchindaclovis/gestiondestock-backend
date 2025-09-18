package com.tchindaClovis.gestiondestock.validator;

import com.tchindaClovis.gestiondestock.dto.LigneVenteDto;
import com.tchindaClovis.gestiondestock.dto.MvtStockDto;

import java.util.ArrayList;
import java.util.List;

public class MvtStockValidator {
    public static List<String> validate(MvtStockDto dto){
        List< String> errors = new ArrayList<>();
        if(dto == null){
            errors.add("Veuillez renseigner la quantite dumouvement de stosk");
            return errors;
        }

        if(dto.getQuantite() == null){
            errors.add("Veuillez renseigner la quantite de la ligne de la Vente");
        }
        return errors;
    }
}
