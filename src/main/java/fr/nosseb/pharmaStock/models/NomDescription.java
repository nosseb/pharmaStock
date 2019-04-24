package fr.nosseb.pharmaStock.models;

import fr.nosseb.pharmaStock.DB.DataBase;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

abstract class NomDescription {
    protected String nom;
    protected String description;

    NomDescription(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}
