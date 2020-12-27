import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import org.apache.pdfbox.pdmodel.graphics.image.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.text.PDFTextStripper;


public class pdfStamper {
    public static void main(String args[]) throws Exception {
        String PATHPDF = "";
        String PATHPNG = "";
        String OUTPDF = "";
        try
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a pdf path: ");
            PATHPDF = scanner.nextLine();
            System.out.println("Enter a png path: ");
            PATHPNG = scanner.nextLine();
            System.out.println("Enter out path:");
            OUTPDF = scanner.nextLine();


            PDDocument doc = PDDocument.load(new File(PATHPDF));
            PDPage page = doc.getDocumentCatalog().getPages().get( 0 );

            PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);

            PDImageXObject pdImage = PDImageXObject.createFromFileByContent(new File(PATHPNG), doc);
            contentStream.drawImage(pdImage, 500f, 20f, 100, 100);
            contentStream.close();
            doc.save(new File(OUTPDF));

        } catch (Exception e)
        {
            System.out.println("An error occurred, try again with other values.");
            e.printStackTrace();
        }
    }
}
