package com.visitor.payload;

public interface AppConstants {

    String DEFAULT_PAGE_NUMBER = "0";
    String DEFAULT_NULL_CONST = "0";
    String DEFAULT_PAGE_SIZE = "30";
    Integer MAX_PAGE_SIZE = 50;

    Integer ENTRE_STOCK = 1;
    Integer SORTIE_STOCK = 2;
    Integer DEPLACEMENT_STOCK = 3;

    Integer COMMANDE_EN_COURS = 0;
    Integer ADMIN_COMMANDE_VALIDE = 1;
    Integer GESTIONNAIRE_COMMANDE_VALIDE = 2;
    Integer COMMANDE_REJETTE= 3;

    Integer COMMANDE_GESTIONNAIRE = 1;
    Integer COMMANDE_EMPLOYE = 2;



    String[] STATUS_CODE_SUCCESS =  {"10", "Action(s) realisée(s) avec succès"};
    String[]  STATUS_CODE_UPDATED = {"11", "Mise a jour effectuée avec succès"};
    String[]  STATUS_CODE_CLIENT_CREATED_ERROR = {"12", "Client enregistré avec des erreurs (POSMs ou Photos non enregistrés)"};
    String[]  STATUS_CODE_ERROR = {"20", "Une erreur est survenue pendant le traitement de vôtre rêquete"};
    String[]  STATUS_CODE_BAD_CREDENTIALS = {"21", "Nom d'utilisateur ou Mot de passe incorrect"};
    String[]  STATUS_CODE_DISABLED_ACCOUNT = {"24", "Votre compte utilisateur a été desactivé"};
    String[]  STATUS_CODE_EMAIL_USERNAME_PHONE_EXISTS = {"22", "userId est déjà pris !"};
    String[]  STATUS_CODE_CLIENT_EXISTS = {"23", "Ce client existe deja ...!"};

    String[]  STATUS_CODE_OPERATION_EXISTS = {"25", "Ce client a deja effectué une operation de vente dans cette journée ...!"};

    String[] STATUS_CODE_TOKEN_GENERATE_SUCCESS =  {"12", "Token generé avec succès"};
    String[] STATUS_CODE_TOKEN_GENERATE_FAIL     =  {"22", "Generation du token a échoué"};


}
