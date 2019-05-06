package fr.nosseb.pharmaStock.models;

class ModProprietaire {

    protected String nom;
    protected String description;

    ModProprietaire(String nom, String description) {
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
