package FilePathTest;

import java.io.File;
import java.util.Scanner;

public class App {

    static public void main(String[] Args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter path :");

        String string = scanner.next();

        String path = "";

        System.out.println("User dir : " + System.getProperty("user.dir"));
        System.out.println("User home : " + System.getProperty("user.home"));

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '~') {
                path += System.getProperty("user.home");
            } else {
                path += string.charAt(i);
            }
        }

        File file = new File(path);

        System.out.println("Found : " + ((file == null) ? "no" : "yes" ));
        System.out.println("Exist : " + (file.exists() ? "yes" : "no" ));
        System.out.println("File : " + (file.isFile() ? "yes" : "no"));
        System.out.println("Directory : " + (file.isDirectory() ? "yes" : "no"));

    }

}
