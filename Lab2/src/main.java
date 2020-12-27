import java.io.*;
import java.util.*;

public class main {
    public static void main(String args[]) throws Exception {
        String PATH = "";

        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a file name: ");
            PATH = scanner.nextLine();
            File file = new File(PATH);
            iniParser parser = new iniParser(PATH);
        }
        catch (Exception e)
        {
            System.out.println("An error occurred, try again with other values.");
            e.printStackTrace();
        }
    }
}
