import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;



public class pdfToTxt
{
    public static void main(String args[]) throws IOException
    {
        try
        {
            String PATH = "passport.pdf";
            //loading pdf
            File file = new File(PATH);
            PDDocument document = PDDocument.load(file);

            PDFTextStripper pdfStripper = new PDFTextStripper();

            //get text
            String text = pdfStripper.getText(document);

            // text load to txt
            FileWriter myWriter = new FileWriter("out.txt");
            myWriter.write(text);
            myWriter.close();

            //text load to console
            System.out.println(text);
            document.close();
        }
        catch (Exception e)
        {
            System.out.println("An error occurred, try again with other values.");
            e.printStackTrace();
        }
    }
}
