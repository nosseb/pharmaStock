package fr.nosseb.pharmaStock.client;

import fr.nosseb.pharmaStock.DB.DataBase;

import  java.util.prefs.Preferences;

/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class App {
    // Preferences Keys
    private static final String DB_VERSION = "db_version";
    private static final String DB_PATH = "db_path";
    private static final String SERVER = "server";
    private static final String SERVER_URL = "server_url";

    private static DataBase dataBase;

    /**
     * Main method of the client.
     * @param Args
     */
    public static void main(String[] Args) {
        Preferences preferences;

        preferences = Preferences.systemNodeForPackage(App.class);

        String db_version = preferences.get(DB_VERSION, "0");
        String db_path = preferences.get(DB_PATH, "");
        String server_url = preferences.get(SERVER_URL, "");
        boolean server = preferences.getBoolean(SERVER, true);

        if (db_path.equals("") || db_version.equals("0")) {
            // DB not set up yet

            // TODO : Ask user for DB path

            dataBase.build(db_path);
        } else {
            // DB already exist

            dataBase.connect(db_path);
        }

        if (server && server_url.equals("")) {
            // Server required but not configured

            // TODO : Ask for server URL.
        }


    }

}
