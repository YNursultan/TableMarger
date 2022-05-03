import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (PDDocument document = PDDocument.load(new File("C:\\Users\\Админ\\IdeaProjects\\teacher\\Omirgaliyev.pdf"))) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                //System.out.println("Text:" + st);
                // split by whitespace
                String[] lines = pdfFileInText.split("\\r?\\n");
                int repeat = 0;
                for (String line : lines) {
                    if( line.equals("Day of the week Time Discipline Classroom Type Lecturer")){
                        if (repeat == 1){
                            System.out.println("----------------------New Teacher Table-------------------");
                            repeat = 0;
                        }
                        repeat = 1;
                    }
                    System.out.println(line);
                }
                //System.out.println(i);
                //System.out.println(teacher);
            }
        } catch (IOException exception) {exception.printStackTrace();}
    }
}
