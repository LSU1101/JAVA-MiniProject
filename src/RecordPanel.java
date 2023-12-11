import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class RecordPanel extends JPanel {
    private File file = new File("textSource/records.txt");
    public RecordPanel() {
        setBackground(new Color(185, 152, 112));


    }

//    public void addRecord {
//        try {
//            FileReader fileReader = new FileReader(file);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//        } catch (FileNotFoundException e) {
//            System.out.println("File open error.");
//        }
//    }
}
