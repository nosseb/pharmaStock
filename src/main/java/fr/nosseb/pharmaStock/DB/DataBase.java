package fr.nosseb.pharmaStock.DB;


import fr.nosseb.pharmaStock.settings.Settings;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Robinson Besson
 * @version 0.1
 * @since 0.1
 */
public class DataBase {
    // DB URL
    private static final String DRIVER_URL_PREFIX = "jdbc:derby:";
    private static final String DRIVER_URL_SUFIX = ";create=true";
    private static String DRIVER_URL;
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";

    private static Connection connection;


    /**
     * Build the database for first use.
     * @param db_path path to the DataBase
     */
    public static void build(String db_path) {
        // Connect to the DB.
        connect(db_path);

        // Run script to generate the tables.
        runScript(getFileFromResources("sql/CreationTables.sql"));

        // Save DB version.
        Settings.setDb_version("0.1");
    }



    /**
     * Run a multi lines script.
     * @param file .SQL script
     */
    private static void runScript(File file) {
        // Split script into multiples one line commands.
        ArrayList<String> cmds = commandParser(file);

        // Run all the commands
        for (String cmd : cmds) {
            runScript(cmd);
        }

    }



    /**
     * Run a single line script.
     * @param sql Single line SQL command.
     */
    private static void runScript(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            // EXCEPTION: in case of incorrect SQL, crash expected.
            // FIXME: check if this could be managed in somme way. In that case would need to be managed on a higher level.
            e.printStackTrace();
        }
    }



    /**
     * Connect to an already existing database
     * @param db_path path to the DataBase
     */
    public static void connect(String db_path) {
        // Assemble DB URL.
        DRIVER_URL = DRIVER_URL_PREFIX + db_path + DRIVER_URL_SUFIX;

        // Launch DB driver
        // TODO: check if really required.
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (java.lang.NoSuchMethodException e) {
            // EXCEPTION: java couldn't find a constructor, crash expected.
            e.printStackTrace();
        } catch (java.lang.reflect.InvocationTargetException e) {
            // EXCEPTION : ???
            // FIXME: research and handle correctly.
            e.printStackTrace();
        } catch (java.lang.ClassNotFoundException e) {
            // EXCEPTION: Did not found the driver class, crash expected.
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            // EXCEPTION: Java was unable to instantiate the driver, crash expected
            e.printStackTrace();
        } catch (java.lang.IllegalAccessException e) {
            // EXCEPTION: Not allowed to aces the requested class ? Crash expected.
            e.printStackTrace();
        }

        // Init connection to DB.
        try {
            connection = DriverManager.getConnection(DRIVER_URL);
        } catch (SQLException e) {
            // EXCEPTION: SQL error, either internal or caused by a bad DRIVER_URL, thus not recoverable and a crash is expected.
            e.printStackTrace();
        }
    }



    /**
     * Get file fromDB inside the .jar archive.
     * Used to aces SQL scripts.
     * @param fileName the name of the file
     * @return file File recovered fromDB the .jar archive
     */
    @NotNull
    @Contract("_ -> new")
    private static File getFileFromResources(String fileName) {
        ClassLoader classLoader = DataBase.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File is not found!");
            // FIXME: check if error is adapted. This one does not require any catch clause.
        } else {
            return new File(resource.getFile());
        }

    }



    /**
     * Separate commands fromDB SQL script
     * @param file script to parse
     * @return ArrayList of single line SQL commands
     */
    private static ArrayList<String> commandParser (File file) {
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<String> cmds = new ArrayList<>();

        int parentheses = 0;
        StringBuilder temp = null;

        // Fetch file into String Array
        try (
                // Resources automatically closed, no mater how the try end is terminated.
                // Objects need to implement closeable.
                BufferedReader reader = new BufferedReader(new FileReader(file))
                ) {
            while (reader.ready()) {
                String line = reader.readLine();
                lines.add(line);
            }
        } catch (IOException e) {
            // EXCEPTION: caused by the BufferedReader.readLine(). Not sure how to handle.
            // FIXME: handle correctly.
            e.printStackTrace();
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
                        //temp.append(';');
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


    /**
     * Run an SQL query.
     * @param sqlRequest the single line script to be executed.
     * @return semi-parsed SQL result
     */
    public static ResultSet query(String sqlRequest) {
        ResultSet resultSet = null;

        Statement stmt;

        try {
            stmt = connection.createStatement();
            resultSet = stmt.executeQuery(sqlRequest);
        } catch (java.sql.SQLException e) {
            // EXCEPTION: Both lines throw SQLException, probably cannot be handled properly. Crash expected in the meantime.
            // FIXME: Research on how those can be handled.
            e.printStackTrace();
        }

        return resultSet;
    }

    /**
     * Run an SQL write single line script.
     * @param sqlCommand The one line command to be executed.
     */
    public static void write(String sqlCommand) {
        runScript(sqlCommand);
    }
}
