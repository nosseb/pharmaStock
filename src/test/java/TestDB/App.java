package TestDB;

import fr.nosseb.pharmaStock.DB.DataBase;

import java.util.ArrayList;

public class App {
    static public void main(String[] Args) {
        ArrayList<String> cmds = new ArrayList<>();

        DataBase dataBase = new DataBase();


        cmds = dataBase.commandParser(dataBase.getFileFromResources("sql/CreationTables.sql"));

        for (String cmd : cmds) {
            System.out.println(cmd);
            //System.out.println("line out");
        }

    }
}
