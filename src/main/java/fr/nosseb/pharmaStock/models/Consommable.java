package fr.nosseb.pharmaStock.models;

import java.util.GregorianCalendar;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

public class Consommable {
    // Attribut
    private String quantite;
    private String numeroSerie;
    private GregorianCalendar datePeremption;

    private Categorie categorie;

    private Lieu lieu;

    private Proprietaire proprietaire;


    // Constructeur
    public Consommable(String quantite, String numeroSerie, GregorianCalendar datePeremption, Categorie categorie, Lieu lieu, Proprietaire proprietaire){
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

    public Categorie getCategorie() {
        return categorie;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }
}
