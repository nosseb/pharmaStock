CREATE TABLE lieux (
    id_lieu         INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev          INT NOT NULL DEFAULT 0 ,
    rev_utilisateur INT NOT NULL ,
    rev_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,

    nom             VARCHAR(64) ,
    description     VARCHAR (512) ,

    CONSTRAINT pk_lieux PRIMARY KEY (id_lieu, id_rev)
)


CREATE TABLE hierarchies_lieux (
    id_hierarchie_lieux INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev              INT NOT NULL DEFAULT 0 ,
    rev_utilisateur     INT NOT NULL ,
    rev_date            TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,

    cf_sup              INT NOT NULL ,
    cf_inf              INT NOT NULL ,

    CONSTRAINT pk_hierarchies_lieux PRIMARY KEY (id_hierarchie_lieux, id_rev)
)


CREATE TABLE utilisateurs (
    id_utilisateur  INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev          INT NOT NULL DEFAULT 0 ,
    rev_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur INT NOT NULL ,

    nom             VARCHAR(64) ,
    description     VARCHAR(512) ,
    email           VARCHAR(218) ,
    pwd_hash        VARCHAR(512) ,

    CONSTRAINT pk_utilisateurs PRIMARY KEY (id_utilisateur, id_rev)
)


CREATE TABLE formulaires (
    id_formulaire   INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev          INT NOT NULL DEFAULT 0 ,
    rev_date        TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur INT NOT NULL ,

    cf_redacteur    INT NOT NULL ,
    cf_aprobateur   INT ,

    date_redaction  TIMESTAMP NOT NULL ,
    date_aprobation TIMESTAMP ,

    CONSTRAINT pk_formulaires PRIMARY KEY (id_formulaire, id_rev)
)


CREATE TABLE retraits (
    id_retrait              INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev                  INT NOT NULL DEFAULT 0 ,
    rev_date                TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur         INT NOT NULL ,

    cf_formulaire_retrait   INT NOT NULL ,
    cf_compteur_consomable  INT NOT NULL ,

    description             VARCHAR(512) ,

    CONSTRAINT pk_retraits PRIMARY KEY (id_retrait, id_rev)
)


CREATE TABLE compteurs (
    id_compteur     INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev          INT NOT NULL DEFAULT 0 ,
    rev_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur INT NOT NULL ,

    cf_lieu         INT NOT NULL ,
    cf_proprietaire INT NOT NULL ,
    cf_categorie    INT NOT NULL ,

    quantite        INT NOT NULL ,
    nom             VARCHAR(64) ,

    CONSTRAINT pk_compteurs PRIMARY KEY (id_compteur, id_rev)
)


CREATE TABLE categories (
    id_categorie    INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev          INT NOT NULL DEFAULT 0 ,
    rev_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur INT NOT NULL ,

    nom             VARCHAR(64) ,
    description     VARCHAR(512) ,

    CONSTRAINT pk_categories PRIMARY KEY (id_categorie, id_rev)
)


CREATE TABLE factures (
    id_facture      INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev          INT NOT NULL DEFAULT 0 ,
    rev_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur INT NOT NULL ,

    jour            DATE NOT NULL DEFAULT CURRENT_DATE ,
    description     VARCHAR(512) ,

    CONSTRAINT pk_factures PRIMARY KEY (id_facture, id_rev)
)


CREATE TABLE equipements (
    id_equipement   INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev          INT NOT NULL DEFAULT 0 ,
    rev_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur INT NOT NULL ,

    cf_lieu         INT NOT NULL ,
    cf_proprietaire INT NOT NULL ,
    cf_facture      INT ,

    nom             VARCHAR(64) ,
    description     VARCHAR(512) ,
    numSerie        varchar(64) ,

    CONSTRAINT pk_equipements PRIMARY KEY (id_equipement, id_rev)
)


CREATE TABLE entretients (
    id_entretient   INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev          INT NOT NULL DEFAULT 0 ,
    rev_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur INT NOT NULL ,

    cf_equipement   INT NOT NULL ,
    cf_facture      INT NOT NULL ,

    nom             VARCHAR(64) ,
    description     VARCHAR(512) ,
    jour            DATE NOT NULL DEFAULT CURRENT_DATE ,

    CONSTRAINT pk_entretients PRIMARY KEY (id_entretient, id_rev)
)


CREATE TABLE futurEntretients (
    id_futurEntretients INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev              INT NOT NULL DEFAULT 0 ,
    rev_date            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur     INT NOT NULL ,

    cf_equipement       INT NOT NULL ,

    jour                DATE NOT NULL ,
    nom                 VARCHAR(64) ,
    description         VARCHAR(512) ,

    CONSTRAINT pk_futurEntretients PRIMARY KEY (id_futurEntretients, id_rev)
)


CREATE TABLE consomables (
    id_consomable   INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev          INT NOT NULL DEFAULT 0 ,
    rev_date        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur INT NOT NULL ,

    cf_lieu         INT NOT NULL ,
    cf_categorie    INT NOT NULL ,
    cf_proprietaire INT NOT NULL ,

    peremption      DATE ,
    numSerie        VARCHAR(64) ,
    quantite        INT NOT NULL ,

    CONSTRAINT pk_consomable PRIMARY KEY (id_consomable, id_rev)
)


CREATE TABLE droits (
    id_droits               INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev                  INT NOT NULL DEFAULT 0 ,
    rev_date                TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur         INT NOT NULL ,

    cf_lieu                 INT NOT NULL ,

    stockGeneral            BOOLEAN NOT NULL ,
    stockDetail             BOOLEAN NOT NULL ,
    alertesMail             BOOLEAN NOT NULL ,
    materielAjout           BOOLEAN NOT NULL ,
    materielModification    BOOLEAN NOT NULL ,
    materielSupression      BOOLEAN NOT NULL ,
    formulaireRedaction     BOOLEAN NOT NULL ,
    formulaireModification  BOOLEAN NOT NULL ,
    formulaireAprobation    BOOLEAN NOT NULL ,
    consomableAjout         BOOLEAN NOT NULL ,
    consomableModification  BOOLEAN NOT NULL ,
    consomableSupression    BOOLEAN NOT NULL ,

    CONSTRAINT pk_droits PRIMARY KEY (id_droits, id_rev)
)


CREATE TABLE utilisateurDroits(
    id_utilisateurDroits    INT NOT NULL GENERATED BY DEFAULT AS IDENTITY ,
    id_rev                  INT NOT NULL DEFAULT 0 ,
    rev_date                TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    rev_utilisateur         INT NOT NULL ,

    cf_droits               INT ,
    cf_utilisateur          INT ,

    nom                     VARCHAR(64),

    CONSTRAINT pk_utilisateurDroits PRIMARY KEY (id_utilisateurDroits, id_rev)
)