package fr.nosseb.pharmaStock.models;

import java.util.GregorianCalendar;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

public class ModConsommable {
    // Attribut
    private String quantite;
    private String numeroSerie;
    private GregorianCalendar datePeremption;

    private ModCategory categorie;

    private ModLieu lieu;

    private ModProprietaire proprietaire;


    // Constructeur
    public ModConsommable(String quantite,
                          String numeroSerie,
                          GregorianCalendar datePeremption,
                          ModCategory categorie,
                          ModLieu lieu,
                          ModProprietaire proprietaire){
        this.quantite = quantite;
        this.numeroSerie = numeroSerie;
        this.datePeremption = datePeremption;

        this.categorie = categorie;

        this.lieu = lieu;

        this.proprietaire = proprietaire;
    }

    // Getters
    public String getQuantite() {
        return this.quantite;
    }

    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    public GregorianCalendar getDatePeremption() {
        return this.datePeremption;
    }

    public ModCategory getCategorie() {
        return categorie;
    }

    public ModLieu getLieu() {
        return lieu;
    }

    public ModProprietaire getProprietaire() {
        return proprietaire;
    }
}
