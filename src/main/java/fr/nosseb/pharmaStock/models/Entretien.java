package fr.nosseb.pharmaStock.models;

import java.util.GregorianCalendar;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

public class Entretien {
    private String nom;
    private String description;
    private GregorianCalendar date;

    public Entretien(String nom, String description, GregorianCalendar date) {
        this.nom = nom;
        this.description = description;
        this.date = date;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
