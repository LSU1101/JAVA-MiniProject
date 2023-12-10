import java.io.*;
import java.util.Vector;

public class TextSource {
    private Vector<String> wordVector = new Vector<String>();

    public TextSource() {
        fileRead();
    }
    private void fileRead() {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("textSource/words.txt"); // File Read.
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
            String word;
            int i = 0;
            while ((word = bufferedReader.readLine()) != null) {
                wordVector.add(word);
                i++;
            }
            // JOptionPane.showMessageDialog(parent, "File read succeed.");
        } catch (Exception e) {
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