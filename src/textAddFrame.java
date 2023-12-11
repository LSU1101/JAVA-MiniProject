import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class textAddFrame extends JFrame {
    private JTextField textField;
    File file = new File("textSource/words.txt");

    public textAddFrame() {
        setTitle("어떤 단어를 추가 할까요?");
        setSize(200, 200);
        Container c = getContentPane();

        c.setLayout(null);

        JLabel label = new JLabel("입력해주세요");
        label.setSize(100, 20);
        label.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 15));
        label.setLocation(60, 40);
        c.add(label);

        JButton btn = new JButton("확인");
        btn.setSize(50, 30);
        btn.setLocation(73, 100);
        btn.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 13));
        c.add(btn);
        btn.addActionListener(new addWordAction());

        textField = new JTextField();
        textField.setLocation(50, 70);
        textField.setSize(100, 20);
        c.add(textField);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private class addWordAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String word = textField.getText();
            textField.setText("");
            addWord(word);
            textField.requestFocus();
        }
    }

    private void addWord(String word) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write("\n" + word);
            writer.close();
        } catch (Exception e) {
            System.out.println("저장 오류");
            System.exit(1);
        }
    }
}
