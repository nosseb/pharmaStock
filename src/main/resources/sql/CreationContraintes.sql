ALTER TABLE lieux               ADD CONSTRAINT rev_lieux                        FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);

ALTER TABLE hierarchies_lieux   ADD CONSTRAINT rev_hierarchies                  FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE hierarchies_lieux   ADD CONSTRAINT hierarchie_sup                   FOREIGN KEY (cf_sup)                    REFERENCES lieux(id_lieu);
ALTER TABLE hierarchies_lieux   ADD CONSTRAINT hierarchie_inf                   FOREIGN KEY (cf_inf)                    REFERENCES lieux(id_lieu);

ALTER TABLE utilisateurs        ADD CONSTRAINT rev_utilisateurs                 FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);

ALTER TABLE formulaires         ADD CONSTRAINT rev_formulaires                  FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE formulaires         ADD CONSTRAINT formulaire_redacteur             FOREIGN KEY (cf_redacteur)              REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE formulaires         ADD CONSTRAINT formulaire_aprobateur            FOREIGN KEY (cf_aprobateur)             REFERENCES utilisateurs(id_utilisateur);

ALTER TABLE retraits            ADD CONSTRAINT rev_retraits                     FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE retraits            ADD CONSTRAINT retrait_formulaire               FOREIGN KEY (cf_formulaire_retrait)     REFERENCES formulaires(id_formulaire);
ALTER TABLE retraits            ADD CONSTRAINT retrait_compteur                 FOREIGN KEY (cf_compteur_consomable)    REFERENCES compteurs(id_compteur);

ALTER TABLE compteurs           ADD CONSTRAINT rev_compteurs                    FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE compteurs           ADD CONSTRAINT compteur_lieu                    FOREIGN KEY (cf_lieu)                   REFERENCES lieux(id_lieu);
ALTER TABLE compteurs           ADD CONSTRAINT compteur_proprietaire            FOREIGN KEY (cf_proprietaire)           REFERENCES lieux(id_lieu);
ALTER TABLE compteurs           ADD CONSTRAINT compteur_categorie               FOREIGN KEY (cf_categorie)              REFERENCES categories(id_categorie);

ALTER TABLE categories          ADD CONSTRAINT rev_categories                   FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);

ALTER TABLE factures            ADD CONSTRAINT rev_factures                     FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);

ALTER TABLE equipements         ADD CONSTRAINT rev_equipements                  FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(rev_utilisateur);
ALTER TABLE equipements         ADD CONSTRAINT equipement_lieu                  FOREIGN KEY (cf_lieu)                   REFERENCES lieux(id_lieu);
ALTER TABLE equipements         ADD CONSTRAINT equipement_proprietaire          FOREIGN KEY (cf_proprietaire)           REFERENCES lieux(id_lieu);
ALTER TABLE equipements         ADD CONSTRAINT equipement_facture               FOREIGN KEY (cf_facture)                REFERENCES factures(id_facture);

ALTER TABLE entretients         ADD CONSTRAINT rev_entretients                  FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE entretients         ADD CONSTRAINT entretient_equipement            FOREIGN KEY (cf_equipement)             REFERENCES equipements(id_equipement);
ALTER TABLE entretients         ADD CONSTRAINT entretient_facture               FOREIGN KEY (cf_facture)                REFERENCES factures(id_facture);

ALTER TABLE futurEntretients    ADD CONSTRAINT rev_futurEntretients             FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE futurEntretients    ADD CONSTRAINT futurEntretients_equipement      FOREIGN KEY (cf_equipement)             REFERENCES equipements(id_equipement);

ALTER TABLE consomables         ADD CONSTRAINT rev_consomables                  FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE consomables         ADD CONSTRAINT consomable_lieu                  FOREIGN KEY (cf_lieu)                   REFERENCES lieux(id_lieu);
ALTER TABLE consomables         ADD CONSTRAINT consomable_categorie             FOREIGN KEY (cf_categorie)              REFERENCES categories(id_categorie);
ALTER TABLE consomables         ADD CONSTRAINT consomable_proprietaire          FOREIGN KEY (cf_proprietaire)           REFERENCES lieux(id_lieu);

ALTER TABLE droits              ADD CONSTRAINT rev_droits                       FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE droits              ADD CONSTRAINT droits_lieu                      FOREIGN KEY (cf_lieu)                   REFERENCES lieux(id_lieu);

ALTER TABLE utilisateurDroits   ADD CONSTRAINT rev_utilisateurDroits            FOREIGN KEY (rev_utilisateur)           REFERENCES utilisateurs(id_utilisateur);
ALTER TABLE utilisateurDroits   ADD CONSTRAINT utilisateurDroits_droits         FOREIGN KEY (cf_droits)                 REFERENCES droits(id_droits);
ALTER TABLE utilisateurDroits   ADD CONSTRAINT utilisateurDroits_utilisateur    FOREIGN KEY (cf_utilisateur)            REFERENCES utilisateurs(id_utilisateur);