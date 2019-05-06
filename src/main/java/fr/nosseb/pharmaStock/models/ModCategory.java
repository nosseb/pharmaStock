package fr.nosseb.pharmaStock.models;

import org.jetbrains.annotations.Contract;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

class ModCategory {
    protected String nom;
    protected String description;

    /**
     * Constructor
     * @param name Name of the category
     * @param description Description of the category
     */
    public ModCategory(String name, String description) {
        this.nom = name;
        this.description = description;
    }

    /**
     * Getter for the category's name.
     * @return the category name.
     */
    @Contract(pure = true)
    public String getNom() {
        return nom;
    }

    /**
     * Getter for the category's description.
     * @return the category description.
     */
    public String getDescription() {
        return description;
    }
}
