package fr.nosseb.pharmaStock.models;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Loan Veyer
 * @date 05/04/2019
 */

public class FicheSortie {
    private String nom;
    private String contenu;

    private GregorianCalendar dateCreation;


    public FicheSortie(String nom, Calendar dateCreation) {
        this.nom = nom;
        this.dateCreation = (GregorianCalendar) dateCreation;
    }

    public String getNom() {
        return this.nom;
    }

    public String getContenu() {
        return this.contenu;
    }


    public GregorianCalendar getDateCreation() {
        return this.dateCreation;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
