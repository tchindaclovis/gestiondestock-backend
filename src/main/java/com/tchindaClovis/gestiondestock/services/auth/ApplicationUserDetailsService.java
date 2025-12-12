package com.tchindaClovis.gestiondestock.services.auth;

import com.tchindaClovis.gestiondestock.dto.UtilisateurDto;
import com.tchindaClovis.gestiondestock.model.auth.ExtendedUser;
import com.tchindaClovis.gestiondestock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private UtilisateurService service;
    @Autowired
    public ApplicationUserDetailsService(UtilisateurService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateur = service.findByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));

        return new ExtendedUser(utilisateur.getEmail(), utilisateur.getMotDePasse(), utilisateur.getEntreprise().getId(), authorities);
    }

//    public int getIdEntreprise() {
//        return 0;
//    }
}





//package com.tchindaClovis.gestiondestock.services.auth;
//
//        import com.tchindaClovis.gestiondestock.exception.EntityNotFoundException;
//        import com.tchindaClovis.gestiondestock.exception.ErrorCodes;
//        import com.tchindaClovis.gestiondestock.model.Utilisateur;
//        import com.tchindaClovis.gestiondestock.repository.UtilisateurRepository;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.security.core.userdetails.User;
//        import org.springframework.security.core.userdetails.UserDetails;
//        import org.springframework.security.core.userdetails.UserDetailsService;
//        import org.springframework.security.core.userdetails.UsernameNotFoundException;
//        import org.springframework.stereotype.Service;
//        import java.util.Collections;
//
//@Service
//public class ApplicationUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UtilisateurRepository repository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
//        Utilisateur utilisateur = repository.findByEmail(email).orElseThrow(() ->
//                new EntityNotFoundException("Aucun utilisateur avec l'email fourni", ErrorCodes.UTILISATEUR_NOT_FOUND));
//
//        return new User(utilisateur.getEmail(), utilisateur.getMotDePasse(), Collections.emptyList());
//    }
//}
