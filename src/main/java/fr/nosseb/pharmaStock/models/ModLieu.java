package fr.nosseb.pharmaStock.models;

import fr.nosseb.pharmaStock.DB.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

public class ModLieu {

    protected String nom;
    protected String description;
    private int idLieu;

    @Override
    public String toString() {
        return this.nom;
    }

    // TODO : check if needed
    public Integer getIdLieu() {
        return idLieu;
    }

    // TODO : check if needed
    public void setIdLieu(Integer i) {
        this.idLieu = i;
    }

    public ModLieu(String nom, String description) {
        this.nom = nom;
        this.description = description;

        this.idLieu = -1;
    }

    public ModLieu(int idLieu, String nom, String description) {
        this.nom = nom;
        this.description = description;

        this.idLieu = idLieu;
    }

    public static ObservableList<ModLieu> from(DataBase db) {
        final String SQL_REQUEST = "SELECT LIEUX.ID_LIEU, LIEUX.ID_REV, LIEUX.NOM, LIEUX.DESCRIPTION\n" +
                "FROM LIEUX, (\n" +
                "    SELECT ID_LIEU, MAX(ID_REV) AS MAX_REV\n" +
                "    FROM LIEUX\n" +
                "    GROUP BY ID_LIEU\n" +
                "    ) a\n" +
                "WHERE LIEUX.ID_LIEU = a.ID_LIEU\n" +
                "AND LIEUX.ID_REV = a.MAX_REV\n" +
                "AND (LIEUX.NOM IS NOT NULL OR LIEUX.DESCRIPTION IS NOT NULL)";

        ResultSet resultSet = db.query(SQL_REQUEST);
        ObservableList<ModLieu> lieux = FXCollections.observableArrayList();


        try {
            while (resultSet.next()) {
                // TODO : Cleanup debug
                //System.out.println("Result" + resultSet);

                String nom = resultSet.getString("NOM");
                String description = resultSet.getString("DESCRIPTION");
                int id_lieu = resultSet.getInt("ID_LIEU");

                // TODO : Cleanup debug
                //System.out.println("description SQL : " + description);

                lieux.add(new ModLieu(id_lieu, nom, description));

            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        // TODO : Cleanup debug
//		for (ModLieu l : lieux) {
//			System.out.println("lieu : " + l.getNom());
//			System.out.println("description : " + l.getDescription());
//		}
        return lieux;
    }

    public static ModLieu specificFrom(int idLieu, fr.nosseb.pharmaStock.DB.DataBase dataBase) {
        final String SQL_REQUEST = "SELECT LIEUX.ID_LIEU, LIEUX.ID_REV, LIEUX.NOM, LIEUX.DESCRIPTION\n" +
                "FROM LIEUX, (\n" +
                "    SELECT ID_LIEU, MAX(ID_REV) AS MAX_REV\n" +
                "    FROM LIEUX\n" +
                "    GROUP BY ID_LIEU\n" +
                "    ) a\n" +
                "WHERE LIEUX.ID_LIEU = a.ID_LIEU\n" +
                "AND LIEUX.ID_REV = a.MAX_REV\n" +
                "AND (LIEUX.NOM IS NOT NULL OR LIEUX.DESCRIPTION IS NOT NULL)\n" +
                "AND LIEUX.ID_LIEU = " + idLieu;

        ResultSet resultSet = dataBase.query(SQL_REQUEST);
        ObservableList<ModLieu> lieux = FXCollections.observableArrayList();


        try {
            while (resultSet.next()) {
                // TODO : Cleanup debug
                //System.out.println("Result" + resultSet);

                String nom = resultSet.getString("NOM");
                String description = resultSet.getString("DESCRIPTION");
                int id_lieu = resultSet.getInt("ID_LIEU");

                // TODO : Cleanup debug
                //System.out.println("description SQL : " + description);

                lieux.add(new ModLieu(id_lieu, nom, description));

            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        return lieux.get(0);
    }

    public void addTo(DataBase db) {
        int id_rev=0;
        if (this.idLieu > -1 ) {
            ResultSet resultSet = DataBase.query("SELECT MAX(ID_REV) AS MAX_ID_REV FROM LIEUX WHERE ID_LIEU = " + this.idLieu);
            try {
                resultSet.next();
                id_rev = resultSet.getInt("MAX_ID_REV");
                id_rev++;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // TODO : take active user
        int rev_utilisateur = 2;

        String nom = this.nom;
        String description = this.description;

        DataBase.write("INSERT INTO LIEUX (" +
                ( (this.idLieu > -1)? "ID_LIEU, ID_REV, " : "") +
                "NOM, DESCRIPTION, REV_UTILISATEUR) VALUES (" +
                ((this.idLieu > -1)? (idLieu + ", " + (id_rev) + ", ") : "" ) +
                "'" + nom + "', '" + description + "', " + rev_utilisateur + ")");

    }

    public void removeFrom(DataBase dataBase) {

        int id_rev=0;
        if (this.idLieu > -1 ) {
            ResultSet resultSet = DataBase.query("SELECT MAX(ID_REV) AS MAX_ID_REV FROM LIEUX WHERE ID_LIEU = " + this.idLieu);
            try {
                resultSet.next();
                id_rev = resultSet.getInt("MAX_ID_REV");
                id_rev++;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // TODO : take active user
        int rev_utilisateur = 2;

        DataBase.write("INSERT INTO LIEUX (ID_LIEU, ID_REV, NOM, DESCRIPTION, REV_UTILISATEUR) VALUES (" + idLieu + ", " + id_rev + ", NULL, NULL, " + rev_utilisateur + ")");

    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}