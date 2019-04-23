package fr.nosseb.pharmaStock.models;

import fr.nosseb.pharmaStock.DB.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

/**
 * 
 * @author Loan Veyer
 * @date 20/03/2019
 *
 */

public class Lieu extends NomDescription {
	
	public Lieu(String nom, String description) {
		super(nom, description);
	}

	public static ObservableList<Lieu> from(DataBase db) {
		final String SQL_REQUEST = "SELECT LIEUX.ID_LIEU, LIEUX.ID_REV, LIEUX.NOM, LIEUX.DESCRIPTION\n" +
				"FROM LIEUX, (\n" +
				"    SELECT ID_LIEU, MAX(ID_REV) AS MAX_REV\n" +
				"    FROM LIEUX\n" +
				"    GROUP BY ID_LIEU\n" +
				"    ) a\n" +
				"WHERE LIEUX.ID_LIEU = a.ID_LIEU\n" +
				"AND LIEUX.ID_REV = a.MAX_REV";

		ResultSet resultSet = db.query(SQL_REQUEST);
		ObservableList<Lieu> lieux = FXCollections.observableArrayList();


		try {
			while (resultSet.next()) {
				String nom = resultSet.getString("NOM");
				String description = resultSet.getString("DESCRIPTION");

				lieux.add(new Lieu(nom, description));

			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}
