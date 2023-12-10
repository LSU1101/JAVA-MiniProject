import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class GameGround extends JPanel {
    private ScorePanel scorePanel = null;
    private TextSource textSource = null;
    private final JLabel[] label = new JLabel[7];
    private JTextField input = new JTextField(20);
    private Vector<JLabel> answerLabel = new Vector<>();
    private ImageIcon img;

    private Color[] colors = {new Color(0xE89898), new Color(0xD07951), new Color(0xD5C991), new Color(0x8FCE6C), new Color(0x98B8E8), new Color(0x3F4E94), new Color(0x815CAF)};

    public GameGround(ScorePanel scorePanel) {
        this.scorePanel = scorePanel;
        setLayout(null);

        Color beige = new Color(240, 236, 225);
        setBackground(beige);
        textSource = new TextSource();

        input.setSize(300, 40);
        input.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setForeground(new Color(77, 54, 39));
        input.setBorder(new LineBorder(new Color(77, 54, 39), 2));
        input.setLocation(120, 500);
        add(input);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = (JTextField)e.getSource();
                String text = textField.getText();
                textField.setText("");

                for (int i = 0; i < label.length; i++) {
                    if (text.equals(label[i].getText()) && !text.equals("참치캔")) {
                        clear(i);
                        scorePanel.increase();
                        return;// 맞추면 탈출
                    } else if (text.equals(label[i].getText()) && text.equals("참치캔")) {
                        clear(i);
                        scorePanel.increase();
                        scorePanel.increase();
                        return;
                    }
                }
                scorePanel.decrease();
            }

            private void clear(int index) {
                label[index].setForeground(new Color(77, 54, 39));
                label[index].setText("🐈맛있다!🐈");
                label[index].setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
            }
        });

        MyThread thread = new MyThread();
        thread.start();
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            textInit(); // 처음 글자 생성
            int colorChange = 0;
            int speedLevel;

            JLabel levelLabel = new JLabel();
            levelLabel.setSize(150, 25);
            levelLabel.setLocation(200, 5);
            levelLabel.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 25));
            levelLabel.setForeground(new Color(0x49312F));
            add(levelLabel);

            while (true) {
                if (scorePanel.scoreCheck() == 1) {
                    speedLevel = 0;
                    levelLabel.setText("LEVEL 1 !");
                } else if (scorePanel.scoreCheck() == 2) {
                    speedLevel = 3;
                    levelLabel.setText("LEVEL 2 !!");
                    levelLabel.setForeground(new Color(79, 121, 210));
                } else {
                    speedLevel = 6;
                    levelLabel.setText("LEVEL 3 !!!");
                    levelLabel.setForeground(new Color(222, 68, 74));
                }

                for (int i = 0; i < label.length; i++) {
                    if (label[i].getY() > 500 && !label[i].getText().equals("🐈맛있다!🐈")) {
                        addText(i);
                    }

                    if (label[i].getText().equals("🐈맛있다!🐈") && label[i].getY() > 500) { // 맞춘거
                        label[i].setText("");
                        addText(i);
                    }
                }

                for (int i = 0; i < label.length; i++) { // 아래로 떨어뜨리기
                    label[i].setLocation(label[i].getX(), label[i].getY() + (1 + speedLevel));
                    if (label[i].getText().equals("참치캔") || label[i].getText().equals("🐈맛있다!🐈")) {
                        label[i].setLocation(label[i].getX(), label[i].getY() + (5 + speedLevel));

                        label[i].setForeground(colors[colorChange++]);
                        if (colorChange == 6) colorChange = 0;
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
        label[index].setSize(100, 20);
        String word = textSource.next();

        int x = (int)(Math.random() * 450);
        int y = (int)(Math.random() * 30);
        int xDifference;
        int yDifference;

        for (int i = 0; i < label.length; i++) {
            xDifference = Math.abs(label[i].getX() - x); // 절댓값
            yDifference = Math.abs(label[i].getY() - y); // 절댓값

            if (xDifference < 50) { // 위치가 겹치면
                x += 100;
                if (x > 450) {
                    x = (int)(Math.random() * 100);
                }
            }
            if (yDifference < 20) {
                y += 20;
            }

            if (label[i].getText().equals(word)) { // 이전의 생성된 것과 같은 문자라면
                word = textSource.next();
            }
        }

        textSet(index, x, y, word);
    }

    private void textInit() {
        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel();
            label[i].setSize(100, 20);
            String word = textSource.next();

            int x = (int)(Math.random() * 450);
            int y = (int)(Math.random() * 200 + 50);
            int xDifference;
            int yDifference;

            for (int j = 0; j < i; j++) {
                xDifference = Math.abs(label[j].getX() - x); // 절댓값
                yDifference = Math.abs(label[j].getY() - y); // 절댓값

                if (xDifference < 100) { // 위치가 겹치면
                    x += 100;
                    if (x > 450) {
                        x = 0;
                    }
                }
                if (yDifference < 20) {
                    y += 20;
                }
                if (label[j].getText().equals(word)) { // 이전의 생성된 것과 같은 문자라면
                    word = textSource.next();
                }
            }

            textSet(i, x, y, word);
        }
    }

    private void textSet(int index, int x, int y, String word) {
        label[index].setText(word);
        label[index].setForeground(new Color(77, 54, 39));
        label[index].setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
        label[index].setLocation(x, y);
        add(label[index]);
    }
}
