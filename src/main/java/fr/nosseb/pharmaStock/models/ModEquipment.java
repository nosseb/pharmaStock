package fr.nosseb.pharmaStock.models;

import fr.nosseb.pharmaStock.DB.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

/**
 *
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */
public class ModEquipment {
    // Fields value
    private int id;
    private String name;
    private String description;
    private String serialNumber;
    private int location;

    // Stored for ease of aces.
    private String locationName;

    // CLEANUP: Are Upkeeps required to be stored ?
//    private ArrayList<ModUpkeep> upkeeps;

    /**
     * Constructor for equipment using Location object.
     * @param id equipment's ID.
     * @param name equipment's name.
     * @param description equipment's description.
     * @param serialNumber equipment's serial number.
     * @param location equipment's location as an object.
     */
    public ModEquipment(int id, String name, String description, String serialNumber, @NotNull ModLocation location) {
        this(id, name, description, serialNumber, location.getId(), location.getName());
    }


    /**
     * Constructor for equipment using Location's fields values.
     * @param id equipment's ID.
     * @param name equipment's name.
     * @param description equipment's description.
     * @param serialNumber equipment's serial number.
     * @param locationId equipment's location's id.
     * @param locationName equipment's location's name.
     */
    public ModEquipment(int id, String name, String description, String serialNumber, int locationId, String locationName) {
        this.id = id;

        this.name = name;
        this.description = description;
        this.serialNumber = serialNumber;

        this.location = locationId;
        this.locationName = locationName;

        // CLEANUP: Are Upkeeps required to be stored ?
//        // CLEANUP: check if <ModUpkeep> cans be safely removed.
//        this.upkeeps = new ArrayList<ModUpkeep>();
    }


    /**
     * Get latest revision of all equipments fromDB the DB. Null entries are ignored.
     * @return A list composed of the latest version of every equipment fromDB the DB. Null entries are ignored.
     */
    @Contract(pure = true)
    public static ObservableList<ModEquipment> fromDB() {
        // SQL request script
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

        // Perform query and get results
        ResultSet results = DataBase.query(SQL_REQUEST);
        ObservableList<ModEquipment> equipments = FXCollections.observableArrayList();

        // Parse results
        try {
            while (results.next()) {
                int id = results.getInt("ID_EQUIPEMENT");
                String name = results.getString("NOM");
                String description = results.getString("DESCRIPTION");
                String serialNumber = results.getString("NUMSERIE");
                int LocationId = results.getInt("CF_LIEU");
                String LocationName = results.getString("NOM_LIEU");

                equipments.add(new ModEquipment(id, name, description, serialNumber, LocationId, LocationName));
            }
        } catch (java.sql.SQLException e) {
            // EXCEPTION: SQL error in the results. Not sure how to handle, crash expected in the meantime.
            e.printStackTrace();
        }
        return equipments;
    }

    public String getName() {
        return this.name;
    }

	public String getLocationName() {
		return this.locationName;
	}

    public String getDescription() {
        return this.description;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    // CLEANUP: Are Upkeeps required to be stored ?
//    public ArrayList<ModUpkeep> getUpkeeps() {
//        return this.upkeeps;
//    }

    public ModLocation getLieuObject(fr.nosseb.pharmaStock.DB.DataBase dataBase) {
        return ModLocation.specificFrom(this.id, dataBase);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void setLieu(int location) {
//        this.location = location;
//        this.locationName = location.getName();
//    }

    // CLEANUP: Are Upkeeps required to be stored ?
    // Methode pour les upkeeps
//    public void ajouterEntretien(ModUpkeep nouveauEntretien) {
//        this.upkeeps.add(nouveauEntretien);
//    }

}
