import java.util.Scanner;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.*;
import java.util.*;

public class xlsxCreator {
    public static void main(String args[]) throws Exception
    {
        String PATH = "";
        String pass1 = "";
        String pass2 = "";
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a file name: ");
            PATH = System.getProperty("user.dir") + "\\" + scanner.nextLine() + ".xls";
            System.out.println("File created at " + PATH);

            System.out.println("Set password for sheet1:");
            pass1 = scanner.nextLine();
            System.out.println("Set password for sheet2:");
            pass2 = scanner.nextLine();

            // create xlsx
            Workbook wb = new HSSFWorkbook();
            OutputStream fileOut = new FileOutputStream(PATH);
            Sheet sheet1 = wb.createSheet("new sheet");
            Sheet sheet2 = wb.createSheet("second sheet");
            sheet1.protectSheet(pass1);
            sheet2.protectSheet(pass2);

            for(int i = 0; i < 10; i++)
            {
                Row row = sheet1.createRow(i);
                Cell cell = row.createCell(0);
                cell.setCellValue(i);
            }

            Row row = sheet1.getRow(3);
            Cell cell = row.createCell(3);
            cell.setCellFormula("SUM(A1:A10)");

            row = sheet2.createRow(0);
            cell = row.createCell(0);
            cell.setCellValue("sample text");

            wb.write(fileOut);

        }
        catch (Exception e)
        {
            System.out.println("An error occurred, try again with other values.");
            e.printStackTrace();
        }
    }
}
