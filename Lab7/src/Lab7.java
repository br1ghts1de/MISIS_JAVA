import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.zeroturnaround.zip.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class Lab7 {
    public static void main(String args[]) throws Exception {
        List<String>paths_to_zips = new ArrayList<String>();
        List<String>paths_to_folders = new ArrayList<String>();
        List<String>path_to_txts = new ArrayList<String>();
        List<String>values = new ArrayList<String>();
        int n = 0;

        String PATH = "";
        String OutPATH = "";
        try
        {
            // input
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter num of zips: ");
            n = scanner.nextInt();
            scanner.nextLine();
            for(int i = 1; i <= n; i++)
            {
                System.out.println("Enter path to zip " + i);
                PATH = scanner.nextLine();
                paths_to_zips.add(PATH);
                paths_to_folders.add(PATH.substring(0, PATH.length()-4));
                System.out.println(paths_to_folders.get(i-1));
                ZipUtil.unpack(new File(paths_to_zips.get(i-1)), new File(paths_to_folders.get(i-1)));
            }

            System.out.println("Enter out PATH: ");
            OutPATH = scanner.nextLine();


            // get list of all txts

            FilenameFilter textFilter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(".txt");
                }
            };

            for(int i = 0; i < n; i++)
            {
                File[] files = new File(paths_to_folders.get(i)).listFiles(textFilter);
                for (File file : files)
                {
                    // get File lines and puh back to values array
                    System.out.println(file);
                    BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
                    String line;
                    while ((line = br.readLine()) != null)
                    {
                        values.add(line);
                    }
                }
            }


            System.out.println(values);
            Workbook wb = new HSSFWorkbook();
            OutputStream fileOut = new FileOutputStream(OutPATH);
            Sheet sheet1 = wb.createSheet("new sheet");

            for(int i = 0; i < values.size(); i++)
            {
                Row row = sheet1.createRow(i);
                Cell cell = row.createCell(0);
                cell.setCellValue(values.get(i));
            }
//            debug out all files check
//            wb.write(fileOut);
        }
        catch (Exception e)
        {
            System.out.println("An error occurred, try again with other values.");
            e.printStackTrace();
        }
    }
}
