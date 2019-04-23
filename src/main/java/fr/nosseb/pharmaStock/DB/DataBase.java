package fr.nosseb.pharmaStock.DB;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class DataBase {
    // URL
    private static final String DRIVER_URL_PREFIX = "jdbc:derby:";
    private static final String DRIVER_URL_SUFIX = ";create=true";
    private static String DRIVER_URL;

    private Connection connection;



    /**
     * Build the database for first use.
     * @param db_path path to the DataBase
     */
    public void build(String db_path) {
        // Assemble DB URL.
        DRIVER_URL = DRIVER_URL_PREFIX + db_path + DRIVER_URL_SUFIX;

        try {
            connection = DriverManager.getConnection(DRIVER_URL);
        } catch (SQLException e) {
            e.printStackTrace();

            // TODO : Properly manage exception.
        }

        runScript(getFileFromRessources("CreationTables.sql"));
    }



    /**
     * Run a multi lines script
     * @param file .SQL script
     */
    private void runScript(File file) {
        ArrayList<String> cmds = new ArrayList<>();

        cmds = commandParser(file);

        for (String cmd : cmds) {
            runScript(cmd);
        }

    }



    /**
     * Run a single line script
     * @param sql Single line SQL command
     */
    private void runScript(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();

            // TODO : properly manage exception
        }
    }



    /**
     * Connect to an already existing database
     * @param db_path path to the DataBase
     */
    public void connect(String db_path) {
        // Assemble DB URL.
        DRIVER_URL = DRIVER_URL_PREFIX + db_path;

        try {
            connection = DriverManager.getConnection(DRIVER_URL);
        } catch (SQLException e) {
            e.printStackTrace();

            // TODO : Properly manage exception.
        }
    }



    /**
     * Get file from inside the .jar archive.
     * @param fileName the name of the file
     * @return file File recovered from the .jar archive
     */
    private File getFileFromRessources (String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            // Resource does not exist
            throw new IllegalArgumentException("File not found !");
        } else {
            return new File(resource.getFile());
        }

    }



    /**
     * Separate commands from SQL script
     * @param file script to parse
     * @return ArrayList of single lince SQL commands
     */
    private ArrayList<String> commandParser (File file) {

        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> cmds = new ArrayList<>();

        int parentheses = 0;
        StringBuilder temp = null;

        // Fetch file into String Array
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file))
                ) {
            while (reader.ready()) {
                String line = reader.readLine();
                //System.out.println("line in: " + line);
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();

            // TODO : Properly handle exception.
        }

        // Parsing
        for (String line : lines) {
            for (int j = 0; j < line.length(); j++) {

                if (temp == null) {
                    temp = new StringBuilder();
                }

                switch (line.charAt(j)) {
                    case '(':
                        parentheses++;
                        temp.append('(');
                        break;

                    case ')':
                        parentheses--;
                        temp.append(')');
                        break;

                    case '\r':
                    case '\n':
                        temp.append(' ');
                        break;

                    case ';':
                        temp.append(';');
                        if (parentheses == 0) {
                            cmds.add(temp.toString());
                            temp = null;
                        }
                        break;

                    default :
                        temp.append(line.charAt(j));
                }
            }
        }


        return cmds;
    }
}
