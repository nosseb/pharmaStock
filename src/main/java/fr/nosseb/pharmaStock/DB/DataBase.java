package fr.nosseb.pharmaStock.DB;

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
    // URL
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
        // Assemble DB URL.
        DRIVER_URL = DRIVER_URL_PREFIX + db_path + DRIVER_URL_SUFIX;

        try {

            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(DRIVER_URL);
        } catch (SQLException e) {
            e.printStackTrace();

            // TODO : Properly manage exception.
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        runScript(getFileFromResources("sql/CreationTables.sql"));
    }



    /**
     * Run a multi lines script
     * @param file .SQL script
     */
    private static void runScript(File file) {
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
    private static void runScript(String sql) {
        try (Statement statement = connection.createStatement()) {
            // TODO : cleanup debug
            //System.out.println(sql);
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
    public static void connect(String db_path) {
        // Assemble DB URL.
        DRIVER_URL = DRIVER_URL_PREFIX + db_path;

        try {
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(DRIVER_URL);
        } catch (SQLException e) {
            e.printStackTrace();

            // TODO : Properly manage exception.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    /**
     * Get file from inside the .jar archive.
     * @param fileName the name of the file
     * @return file File recovered from the .jar archive
     */
    // TODO : cleanup after test : set private
    public static File getFileFromResources(String fileName) {
        ClassLoader classLoader = DataBase.class.getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }

    }



    /**
     * Separate commands from SQL script
     * @param file script to parse
     * @return ArrayList of single lince SQL commands
     */
    // TODO : set private
    public static ArrayList<String> commandParser (File file) {

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
                // TODO : Cleanup debug
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
     *
     * @param sqlRequest
     * @return semi-parsed SQL result
     */
    public static ResultSet query(String sqlRequest) {

        ResultSet resultSet = null;

        try {
            Statement stmt = connection.createStatement();
            //TODO : Cleanup debug
            //System.out.println(stmt);
            resultSet = stmt.executeQuery(sqlRequest);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        // TODO : Cleanup debug
        //System.out.println("result : " + resultSet);

        return resultSet;
    }

    public void wirte(String s) {
        try {
            Statement stmt = connection.createStatement();
            //TODO : Cleanup debug
            //System.out.println(stmt);
            stmt.execute(s);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
