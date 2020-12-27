import java.io.*;
import java.util.*;
import org.zeroturnaround.zip.*;

public class zipFolder {
    public static void main(String args[]) throws Exception {
        String PATH = "";
        String name = "";
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a folder path: ");
            PATH = scanner.nextLine();

            System.out.println("Enter a zip name: ");
            name = scanner.nextLine();

            File file_folder = new File(PATH);
            System.out.println("Folder: " + file_folder.getParentFile());
            System.out.println("Archive: " + file_folder.getParentFile() + "\\name.zip");

            ZipUtil.pack(file_folder, new File(file_folder.getParentFile() + "\\name.zip"));
//            ZipUtil.pack(new File("D:\\Projects\\Java\\MISIS_JAVA\\Lab3\\MyFOLDER"), new File("D:\\Projects\\Java\\MISIS_JAVA\\Lab3\\name.zip"));


        }
        catch (Exception e)
        {
            System.out.println("An error occurred, try again with other values.");
            e.printStackTrace();
        }
    }

}
