package fr.nosseb.pharmaStock.models;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

public class ModUtilisateur {
    protected String nom;
    protected String description;
    private String email;

    ModUtilisateur(String nom, String description, String email) {
        this.nom = nom;
        this.description = description;

        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}
