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

public class ModLocation {
    protected String name;
    protected String description;
    private int id;

//    @Override
//    public String toString() {
//        return this.name;
//    }

    /**
     * Constructor for new element
     * @param name the location name
     * @param description the description name
     */
    public ModLocation(String name, String description) {
        this(-1, name, description);
    }

    /**
     * Constructor for existing elements
     * @param id the id in the DB
     * @param name the Location name
     * @param description the location Description
     */
    public ModLocation(int id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    /**
     * Get all non null entries from DB.
     * @return List of all non null locations from the DB.
     */
    public static ObservableList<ModLocation> fromDB() {
        // SQL request
        final String SQL_REQUEST = "SELECT LIEUX.ID_LIEU, LIEUX.ID_REV, LIEUX.NOM, LIEUX.DESCRIPTION\n" +
                "FROM LIEUX, (\n" +
                "    SELECT ID_LIEU, MAX(ID_REV) AS MAX_REV\n" +
                "    FROM LIEUX\n" +
                "    GROUP BY ID_LIEU\n" +
                "    ) a\n" +
                "WHERE LIEUX.ID_LIEU = a.ID_LIEU\n" +
                "AND LIEUX.ID_REV = a.MAX_REV\n" +
                "AND (LIEUX.NOM IS NOT NULL OR LIEUX.DESCRIPTION IS NOT NULL)";

        // Execute query and get results.
        ResultSet resultSet = DataBase.query(SQL_REQUEST);
        ObservableList<ModLocation> lieux = FXCollections.observableArrayList();

        // Parse results into a list of Locations.
        try {
            while (resultSet.next()) {
                String nom = resultSet.getString("NOM");
                String description = resultSet.getString("DESCRIPTION");
                int id_lieu = resultSet.getInt("ID_LIEU");

                lieux.add(new ModLocation(id_lieu, nom, description));
            }
        } catch (java.sql.SQLException e) {
            // EXCEPTION: error in SQL results. Not sure how to handle, crash expected for now.
            e.printStackTrace();
        }

        return lieux;
    }

    /**
     * Get a specific location from te DB, selected with it's ID.
     * @param idLieu The id of the location requested.
     * @return The location requested, as an object.
     */
    static ModLocation specificFromDB(int idLieu) {
        // SQL command
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

        // Execute command and get results.
        ResultSet resultSet = DataBase.query(SQL_REQUEST);
        ObservableList<ModLocation> lieux = FXCollections.observableArrayList();

        // FIXME: make sure a single element has been returned.

        // Parse results
        try {
            while (resultSet.next()) {
                String nom = resultSet.getString("NOM");
                String description = resultSet.getString("DESCRIPTION");
                int id_lieu = resultSet.getInt("ID_LIEU");

                lieux.add(new ModLocation(id_lieu, nom, description));
            }
        } catch (java.sql.SQLException e) {
            // EXCEPTION: error within the SQL result. Not sure how to handle, crash expected for now.
            e.printStackTrace();
        }

        return lieux.get(0);
    }

    /**
     * Add a new location (itself) to the database.
     */
    public void addToDB() {
        // CLEANUP: should be placed in a method, used by "remove" as well.
        // Determining the right id to be used.
        int idRev=0;
        if (this.id > -1 ) {
            ResultSet resultSet = DataBase.query("SELECT MAX(ID_REV) AS MAX_ID_REV FROM LIEUX WHERE ID_LIEU = " + this.id);
            try {
                resultSet.next();
                idRev = resultSet.getInt("MAX_ID_REV");
            } catch (SQLException e) {
                // EXCEPTION: SQL error when getting existing element element. Not sure how to handle, crash expected for now.
                e.printStackTrace();
            }
            idRev++;
        }

        // TODO : take active user instead of arbitrary value.
        int revUser = 2;

        // It makes the SQL command slightly easier to read.
        String name = this.name;
        String description = this.description;

        // Add the location to the DB.
        DataBase.write("INSERT INTO LIEUX (" +
                ( (this.id > -1)? "ID_LIEU, ID_REV, " : "") +
                "NOM, DESCRIPTION, REV_UTILISATEUR) VALUES (" +
                ((this.id > -1)? (id + ", " + (idRev) + ", ") : "" ) +
                "'" + name + "', '" + description + "', " + revUser + ")");

    }

    /**
     * Remove itself from the Database
     */
    public void removeFrom() {

        // CLEANUP: should be placed in a method, used by "addToDB" as well.
        // Determining the right id to be used.
        int idRev=0;
        if (this.id > -1 ) {
            ResultSet resultSet = DataBase.query("SELECT MAX(ID_REV) AS MAX_ID_REV FROM LIEUX WHERE ID_LIEU = " + this.id);
            try {
                resultSet.next();
                idRev = resultSet.getInt("MAX_ID_REV");
                idRev++;
            } catch (SQLException e) {
                // EXCEPTION: SQL error when getting existing element element. Not sure how to handle, crash expected for now.
                e.printStackTrace();
            }
        }

        // TODO : take active user instead of arbitrary value.
        int userRev = 2;

        // Make a new entry with increased "idRev" and null in all fields, so that this Location won't be showed any more.
        DataBase.write("INSERT INTO LIEUX (ID_LIEU, ID_REV, NOM, DESCRIPTION, REV_UTILISATEUR) VALUES (" + id + ", " + idRev + ", NULL, NULL, " + userRev + ")");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }
}
