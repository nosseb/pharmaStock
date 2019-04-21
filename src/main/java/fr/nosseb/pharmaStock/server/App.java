package fr.nosseb.pharmaStock.server;

import fr.nosseb.pharmaStock.DB.DataBase;
import fr.nosseb.pharmaStock.settings.Manager;

import java.io.File;
import java.util.Scanner;

/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class App {
    private static DataBase dataBase;

    /**
     * Main method of the server.
     * @param Args
     */
    public static void main(String[] Args) {
        boolean validSettings = Manager.loadSettings(App.class, false);

        if (!validSettings) {
            if (!(Manager.checkDb_path() && Manager.checkDb_version())) {
                Manager.setDb_path(
                        getPath( "Enter path to DataBase :", true, false, true)
                        );
                dataBase.build(Manager.getDb_path());
            }
        } else {
            dataBase.connect(Manager.getDb_path());
        }
    }

    /**
     *
     * @param text Text to display during prompt
     * @return a valid path as string
     */
    private static String getPath(String text, boolean mustExist, boolean isFile, boolean isDirectory) {

        if (isFile && isDirectory) {
            throw new java.lang.IllegalArgumentException("Cannot be both file and directory.");
        }

        Scanner scanner = new Scanner(System.in);
        String path, input;
        File file;

        System.out.println(text);
        input = scanner.next();
        path = "";


        do {
            for (char c : input.toCharArray()) {
                if (c == '~') {
                    path += System.getProperty("user.home");
                } else {
                    path += c;
                }
            }
            file = new File(path);

        } while (
                (mustExist && ! file.exists())
                || (isFile && ! file.isFile())
                || (isDirectory && ! file.isDirectory())
        );



        return path;
    }
}
