import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

public class TextSource {
    private Vector<String> wordVector = new Vector<String>(30_000);
    public TextSource(Component parent) {
        try {
            Scanner scanner = new Scanner(new FileReader("words.txt")); // File Read.
            while (scanner.hasNext()) {
                String word = scanner.nextLine();
                wordVector.add(word);
            }
            scanner.close();
            // JOptionPane.showMessageDialog(parent, "File read succeed.");
        } catch (FileNotFoundException e) {
            System.out.println("No File");
            System.exit(0);
        }
    }

    public String next() { // 랜덤으로 텍스트 불러오기.
        int n = wordVector.size();
        int index = (int)(Math.random() * n);
        return wordVector.get(index);
    }
}
