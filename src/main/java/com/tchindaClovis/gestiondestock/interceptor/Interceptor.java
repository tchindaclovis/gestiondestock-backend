package com.tchindaClovis.gestiondestock.interceptor;

import org.hibernate.resource.jdbc.spi.StatementInspector;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class Interceptor implements StatementInspector {

    @Override
    public String inspect(String sql) {
        if (StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")) {
            // select utilisateu0_.
            final String entityName = sql.substring(7, sql.indexOf("."));
            final String idEntreprise = MDC.get("idEntreprise"); //récupérer l'idEntreprise de mon MDC
            if (StringUtils.hasLength(entityName)
                    && !entityName.toLowerCase().contains("entreprise") //la classe "Entreprise" ne contient pas l'identreprise
                    && !entityName.toLowerCase().contains("roles") //la classe "Roles" ne contient pas l'idEntreprise
                    && StringUtils.hasLength(idEntreprise)) {   // vérifier que l'idEntreprise n'est pas null

                if (sql.contains("where")) {
                    sql = sql + " and " + entityName + ".identreprise = " + idEntreprise;
                } else {
                    sql = sql + " where " + entityName + ".identreprise = " + idEntreprise;
                }
            }
        }
        return sql;
    }
}









//package com.tchindaClovis.gestiondestock.interceptor;
//
//import org.hibernate.resource.jdbc.spi.StatementInspector;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//@Component
//public class Interceptor implements StatementInspector {
//
//    private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);
//
//    @Override
//    public String inspect(String sql) {
//        // Formater le SQL pour une meilleure lisibilité
//        String formattedSql = sql.replaceAll("\\s+", " ").trim();
//
//        logger.info("""
//            🐢 SQL Intercepté (Hibernate 6):
//            ┌────────────────────────────────────────────────────
//            │ {}
//            └────────────────────────────────────────────────────
//            """, formattedSql);
//
//        // Vous pouvez modifier le SQL ici si nécessaire
//        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){
//            if(sql.contains("where")){
//                sql = sql + " and identreprise = 3";
//            } else {
//            sql = sql + " where identreprise = 3";
//            }
//        }
//
//        // return formattedSql + " /* intercepté */";
//        return sql; // Retourner le SQL original ou modifié
//    }
//}





//package com.tchindaClovis.gestiondestock.interceptor;
//
//import org.hibernate.resource.jdbc.spi.StatementInspector;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Interceptor implements StatementInspector {
//    @Override
//    public String inspect(String sql) {
//        System.out.println("SQL préparé: " + sql);


// Modifier le SQL si nécessaire
//        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){
//            if(sql.contains("where")){
//                sql = sql + " and idEntreprise = 1";
//            } else {
//            sql = sql + " idEntreprise = 1";
//            }
//        }


//        return sql;    // intercepte la requête sql entre la couche repo et la BD
//    }
//}


//package com.tchindaClovis.gestiondestock.interceptor;
//
//import org.hibernate.internal.EmptyInterceptor;
//import org.hibernate.resource.jdbc.spi.StatementInspector;
//
//public class Interceptor extends StatementInspector {
//    @Override
//    public String onPrepareStatement(String sql) {
//        return super.onPrepareStatement(sql);
//    }
//}