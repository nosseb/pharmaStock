package fr.nosseb.pharmaStock.settings;

import org.jetbrains.annotations.Contract;

import java.io.File;
import java.util.prefs.Preferences;

/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class Settings {
    // Preferences Keys
    private static final String DB_VERSION = "db_version";
    private static final String DB_PATH = "db_path";
    private static final String SERVER = "server";
    private static final String SERVER_URL = "server_url";

    // Settings
    private static String db_version, db_path, server_url;
    private static boolean isClient, server;

    // Preferences object, fromDB 'java.util.prefs.Preferences' API.
    private static Preferences preferences = null;


    /**
     * Getter for 'db_version'.
     * @return db_version
     */
    @Contract(pure = true)
    public static String getDb_version() {
        return db_version;
    }

    /**
     * Setter for 'db_version'.
     * @param db_version
     */
    public static void setDb_version(String db_version) {
        // TODO: check if valid version number.

        // Save in 'Settings' object
        Settings.db_version = db_version;
        // Save on system.
        preferences.put(DB_VERSION, db_version);
    }

    /**
     * Getter for 'db_path'.
     * @return db_path
     */
    @Contract(pure = true)
    public static String getDb_path() {
        return db_path;
    }

    /**
     * Setter for 'db_path'.
     * @param db_path
     */
    public static void setDb_path(String db_path) {
        // TODO: check if valid path

        // Save to 'Settings' object.
        Settings.db_path = db_path;
        // Save on system.
        preferences.put(DB_PATH, db_path);
    }

    /**
     * Getter for 'server_url'.
     * @return server_url
     * @throws IllegalAccessException When outside of client mode.
     */
    @Contract(pure = true)
    public static String getServer_url() throws IllegalAccessException{
        if (isClient) {
            return server_url;
        }
        else {
            // EXCEPTION: No 'server_url' outside of client mode, hard crash expected.
            throw new IllegalAccessException("No server_url outside of client mode !");
        }
    }

    /**
     * Setter for 'server_url'.
     * @param server_url
     * @throws IllegalAccessException When outside of client mode.
     */
    public static void setServer_url(String server_url) throws IllegalAccessException{
        if (isClient) {
            // Save in 'Settings' object.
            Settings.server_url = server_url;
            // Save on system.
            preferences.put(SERVER_URL, server_url);
        }
        else {
            // EXCEPTION: No 'server_url' outside of client mode, hard crash expected.
            throw new IllegalAccessException("No server_url outside of client mode !");
        }
    }

    /**
     * Getter for 'server'.
     * @return server
     * @throws IllegalAccessException When outside of client mode.
     */
    @Contract(pure = true)
    public static boolean getServer() throws IllegalAccessException{
        if (isClient) {
            return server;
        }
        else {
            // EXCEPTION: No 'server' outside of client mode, hard crash expected.
            throw new IllegalAccessException("No server outside of client mode !");
        }
    }

    /**
     * Setter for 'server'.
     * @param server
     * @throws IllegalAccessException When outside of client mode.
     */
    public static void setServer(boolean server) throws IllegalAccessException{
        if (isClient) {
            // Save in 'Settings' object.
            Settings.server = server;
            // Save on system.
            preferences.putBoolean(SERVER, server);
        }
        else {
            // EXCEPTION: No 'server' outside of client mode, hard crash expected.
            throw new IllegalAccessException("No server outside of client mode !");
        }
    }

    /**
     * Load Settings and check their validity.
     * @param app Class for which the parameters are defined, typically Launcher.class
     * @param client Set if the settings are those of the client side. In such case, 'server_url' and 'server' need to be assigned.
     * @return Settings validity: true if settings are valid, false otherwise.
     */
    public static boolean loadSettings(Class app, boolean client) {
        isClient = client;

        preferences = Preferences.userNodeForPackage(app);

        db_version = preferences.get(DB_VERSION, "0");
        db_path = preferences.get(DB_PATH, "");

        // Settings only used on client side
        if (isClient) {
            server_url = preferences.get(SERVER_URL, "");
            server = preferences.getBoolean(SERVER, true);
        }

        return checkSettings();
    }



    /**
     * Check settings validity.
     * @return Settings validity: true if settings are valid, false otherwise.
     */
    public static boolean checkSettings() {
        // Check DB Settings.
        boolean validity = checkDb_version() && checkDb_path();

        // Check client-side settings.
        if (isClient && server) {
           validity = validity && checkServer_url();
        }

        return validity;
    }


    /**
     * Check db_version.
     * @return Version validity: true if version is valid, false otherwise.
     */
    public static boolean checkDb_version() {
        // Check if 'db_version' is set and valid.
        // TODO: check if 'db_version' match le latest database model or if the DB need to be updated.
        return !(db_version.equals("") || db_version.equals("0"));
    }


    /**
     * check db_path.
     * @return db_path validity: true if path is valid, false otherwise.
     */
    public static boolean checkDb_path() {
        // Try to load file
        File file = new File(db_path);
        return (file.exists() && file.isDirectory());
    }


    /**
     * Check if server_url is defined
     * @return true if server_url is defined, false otherwise
     */
    @Contract(pure = true)
    public static boolean checkServer_url() {
        // TODO : check if valid url.
        return !(server_url.equals("") && server);
    }

}
