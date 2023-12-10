import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGround extends JPanel {
    private ScorePanel scorePanel = null;
    private TextSource textSource = null;
    private final JLabel[] label = new JLabel[7];
    private JTextField input = new JTextField(20);
    private ImageIcon img;

    public GameGround(ScorePanel scorePanel) {
        this.scorePanel = scorePanel;
        setLayout(null);

        Color beige = new Color(240, 236, 225);
        setBackground(beige);
        textSource = new TextSource(this);

        input.setSize(300, 20);
        input.setLocation(100, 300);
        add(input);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField)e.getSource();
                String text = textField.getText();
                textField.setText("");

                for (int i = 0; i < label.length; i++) {
                    if (text.equals(label[i].getText())) {
                        scorePanel.increase();
                        label[i].setForeground(new Color(0x4D3627));
                        label[i].setText("🐈맛있다!🐈");
                        label[i].setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
                        return;// 맞추면 탈출
                    }
                }
                scorePanel.decrease();
            }
        });

        MyThread thread = new MyThread();
        thread.start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            textInit(); // 처음 글자 생성

            while (true) {
                for (int i = 0; i < label.length; i++) {
                    if (label[i].getY() > 300 && !label[i].getText().equals("🐈맛있다!🐈")) {
                        label[i].setLocation((int)(Math.random() * 450), 0);
                    }

                    if (label[i].getText().equals("🐈맛있다!🐈") && label[i].getY() > 300) {
                        label[i].setText("");
                        addText(i);
                    }
                }

                for (int i = 0; i < label.length; i++) {
                    label[i].setLocation(label[i].getX(), label[i].getY() + 1);
                    if (label[i].getText().equals("참치캔") || label[i].getText().equals("🐈맛있다!🐈")) {
                        label[i].setLocation(label[i].getX(), label[i].getY() + 4);
                    }
                }

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    private void addText(int index) {
        label[index] = new JLabel();
        label[index].setSize(100, 20);
        String word = textSource.next();

        int x = (int)(Math.random() * 4);
        int y = (int)(Math.random() * 10);

        for (int j = 0; j < index; j++) {
            if (label[j].getX() / 100 == x) { // 위치가 겹치면
                y += 10;
            }
            if (label[j].getText().equals(word)) {
                word = textSource.next();
            }
        }

        label[index].setText(word);
        label[index].setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 15));
        label[index].setLocation((x * 100) + (int)(Math.random() * 100), (y * 10) + 10);
        add(label[index]);
    }

    private void textInit() {
        for (int i = 0; i < label.length; i++) {
            addText(i);
        }
    }
}
