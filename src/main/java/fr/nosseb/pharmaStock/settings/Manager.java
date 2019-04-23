package fr.nosseb.pharmaStock.settings;


import java.io.File;
import java.util.prefs.Preferences;

/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class Manager {
    // Preferences Keys
    private static final String DB_VERSION = "db_version";
    private static final String DB_PATH = "db_path";
    private static final String SERVER = "server";
    private static final String SERVER_URL = "server_url";

    private static boolean isClient;

    // Settings
    private static String db_version, db_path, server_url;
    private static boolean server;

    public static String getDb_version() {
        return db_version;
    }

    public static void setDb_version(String db_version) {
        Manager.db_version = db_version;
    }

    public static String getDb_path() {
        return db_path;
    }

    public static void setDb_path(String db_path) {
        Manager.db_path = db_path;
    }

    public static String getServer_url() throws IllegalAccessException{
        if (isClient) return server_url;
        else throw new IllegalAccessException("No server_url outside of client mode !");
    }

    public static void setServer_url(String server_url) throws IllegalAccessException{
        if (isClient) Manager.server_url = server_url;
        else throw new IllegalAccessException("No server_url outside of client mode !");
    }

    public static boolean getServer() throws IllegalAccessException{
        if (isClient) return server;
        else throw new IllegalAccessException("No server outside of client mode !");
    }

    public static void setServer(boolean server) throws IllegalAccessException{
        if (isClient) Manager.server = server;
        else throw new IllegalAccessException("No server outside of client mode !");
    }

    /**
     * Load Settings
     * @param app App.class
     * @param client Are those parameters for client side ?
     * @return true if settings are valid, false otherwise.
     */
    public static boolean loadSettings(Class app, boolean client) {
        isClient = client;

        Preferences preferences;
        preferences = Preferences.userNodeForPackage(app);

        db_version = preferences.get(DB_VERSION, "0");
        db_path = preferences.get(DB_PATH, "");

        if (isClient) {
            server_url = preferences.get(SERVER_URL, "");
            server = preferences.getBoolean(SERVER, true);
        }

        return checkSettings();
    }



    /**
     * Check settings validity.
     * @return true if settings are valid, false otherwise.
     */
    public static boolean checkSettings() {
        boolean valid = checkDb_version() && checkDb_path();

        if (isClient && server) {
           valid = valid && checkServer_url();
        }

        return valid;
    }


    /**
     * Check db_version
     * @return true if version is valid, false otherwise.
     */
    public static boolean checkDb_version() {
        return !(db_version.equals("") || db_version.equals("0"));
    }


    /**
     * check db_path
     * @return true if path is valid, false otherwise
     */
    public static boolean checkDb_path() {
        File file = new File(db_path);

        return file.exists() && file.isDirectory();
    }


    /**
     * Check if server_url is defined
     * @return true if server_url is defined, false otherwise
     */
    public static boolean checkServer_url() {
        // TODO : check if valid url.

        return !(server_url.equals("") && server);
    }

}