package fr.nosseb.pharmaStock.models;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

public class Utilisateur extends NomDescription {
    private String email;

    Utilisateur(String nom, String description, String email) {
        super(nom, description);

        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
