package fr.nosseb.pharmaStock.models;

import fr.nosseb.pharmaStock.DB.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.util.*;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */
public class ModEquipement {
    private int id;
    private String nom;
    private String description;
    private String numeroSerie;

    private int lieu;
    private String lieuNom;

    private ArrayList<ModUpkeep> entretiens;

    public ModEquipement(int id, String nom, String description, String numeroSerie, ModLieu lieu) {
        this.id = id;

        this.nom = nom;
        this.description = description;
        this.numeroSerie = numeroSerie;

        this.lieu = lieu.getIdLieu();
        this.lieuNom = lieu.getNom();

        this.entretiens = new ArrayList<ModUpkeep>();
    }


    public ModEquipement(int id, String nom, String description, String numeroSerie, int lieu, String lieuNom) {
        this.id = id;

        this.nom = nom;
        this.description = description;
        this.numeroSerie = numeroSerie;

        this.lieu = lieu;
        this.lieuNom = lieuNom;

        this.entretiens = new ArrayList<ModUpkeep>();
    }

    // Getters
    public String getNom() {
        return this.nom;
    }


	public String getLieuNom() {
		return this.lieuNom;
	}

    public String getDescription() {
        return this.description;
    }

    public String getNumeroSerie() {
        return this.numeroSerie;
    }

    public ArrayList<ModUpkeep> getEntretiens() {
        return this.entretiens;
    }

    public int getLieu() {
        return this.lieu;
    }

    public ModLieu getLieuObject(fr.nosseb.pharmaStock.DB.DataBase dataBase) {
        return ModLieu.specificFrom(this.id, dataBase);
    }

    public int getId() {
        return this.id;
    }

    // Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

//    public void setLieu(int lieu) {
//        this.lieu = lieu;
//        this.lieuNom = lieu.getNom();
//    }

    // Methode pour les entretiens
    public void ajouterEntretien(ModUpkeep nouveauEntretien) {
        this.entretiens.add(nouveauEntretien);
    }

    public static ObservableList<ModEquipement> from(DataBase db) {
        // TODO : check for latest version
        final String SQL_REQUEST = "SELECT EQUIPEMENTS.ID_EQUIPEMENT, EQUIPEMENTS.NOM, EQUIPEMENTS.CF_LIEU, EQUIPEMENTS.DESCRIPTION, EQUIPEMENTS.NUMSERIE, LIEUX.ID_LIEU, LIEUX.NOM AS NOM_LIEU\n" +
                "FROM EQUIPEMENTS, LIEUX, (\n" +
                "    SELECT ID_EQUIPEMENT, MAX(ID_REV) AS MAX_REV\n" +
                "    FROM EQUIPEMENTS\n" +
                "    GROUP BY ID_EQUIPEMENT\n" +
                "    ) a, (\n" +
                "    SELECT ID_LIEU, MAX(ID_REV) AS MAX_REV\n" +
                "    FROM LIEUX\n" +
                "    GROUP BY ID_LIEU\n" +
                "    ) b\n" +
                "WHERE EQUIPEMENTS.ID_EQUIPEMENT = a.ID_EQUIPEMENT\n" +
                "AND EQUIPEMENTS.ID_REV = a.MAX_REV\n" +
                "AND EQUIPEMENTS.CF_LIEU = LIEUX.ID_LIEU\n" +
                "AND LIEUX.ID_LIEU = b.ID_LIEU\n" +
                "AND LIEUX.ID_REV = b.MAX_REV";

        ResultSet results = db.query(SQL_REQUEST);
        ObservableList<ModEquipement> equipements = FXCollections.observableArrayList();;

        try {
            while (results.next()) {
                int id = results.getInt("ID_EQUIPEMENT");
                String nom = results.getString("NOM");
                String description = results.getString("DESCRIPTION");
                String numeroSerie = results.getString("NUMSERIE");
                int lieu = results.getInt("CF_LIEU");
                String lieuNom = results.getString("NOM_LIEU");

                equipements.add(new ModEquipement(id, nom, description, numeroSerie, lieu, lieuNom));

            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


        return equipements;
    }
}
