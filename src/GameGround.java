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
    private final JLabel timeLabel = new JLabel();
    private final JTextField input = new JTextField(20);
    private final Vector<Integer> answerIndex = new Vector<>();
    private final TimeThread timeThread = new TimeThread();
    private final GameThread gameThread = new GameThread();
    private boolean waitToggle = false;
    private final String playerName;

    private final Color[] colors = {new Color(0xE89898), new Color(0xD07951), new Color(0xD5C991), new Color(0x8FCE6C), new Color(0x98B8E8), new Color(0x3F4E94), new Color(0x815CAF)};

    public GameGround(ScorePanel scorePanel, String playerName) {
        this.scorePanel = scorePanel;
        this.playerName = playerName;
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

        input.addActionListener(new inputActionListener());

        timeLabel.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);
        timeLabel.setBounds(478, 7, 60, 20);
        add(timeLabel);

        JButton pause = new JButton("일시 정지");
        pause.setBounds(10, 7, 20, 20);
        pause.setBackground(new Color(77, 54, 39));
        pause.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 15));
        add(pause);

        pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!waitToggle) {
                    timeThread.pauseTime();
                    gameThread.pauseTime();
                } else {
                    timeThread.resumeTime();
                    gameThread.resumeTime();
                }
                waitToggle = !waitToggle;
            }
        });

        timeThread.start();
        gameThread.start();
    }

    class inputActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (waitToggle) {
                return;
            }
            JTextField textField = (JTextField)e.getSource();
            String text = textField.getText();
            textField.setText("");
            int index;

            for (int i = 0; i < label.length; i++) {
                if (text.equals(label[i].getText())) {
                    answerIndex.add(i);
                }
            }
            if (answerIndex.size() > 0) {
                index = clearIndex(answerIndex);
                if (label[index].getText().equals("참치캔")) { // 20점 추가 가산
                    scorePanel.increase();
                    scorePanel.increase();
                }
                scorePanel.increase();
                clear(index);
                answerIndex.removeAllElements();
            } else {
                scorePanel.decrease();
                answerIndex.removeAllElements();
            }
        }
    }

    private void clear(int index) {
        label[index].setForeground(new Color(77, 54, 39));
        label[index].setText("🐈맛있다!🐈");
        label[index].setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
    }

    // 삭제할 레이블 정하기, 똑같은 텍스트가 있다면 밑에 있는 걸 먼저 삭제
    private int clearIndex(Vector<Integer> answerIndex) {
        int clearTarget = 0;
        int clearTargetY = 0;
        if (answerIndex.size() < 2) {
            return answerIndex.get(0);
        } else {
            for (int i = 0; i < answerIndex.size(); i++) {
                int y = label[answerIndex.get(i)].getY();

                if (y > clearTargetY) {
                    clearTargetY = y;
                    clearTarget = answerIndex.get(i);
                }
            }
            return clearTarget;
        }
    }

    class GameThread extends Thread {
        private boolean pauseFlag = false;
        public void pauseTime() {
            pauseFlag = true;
        }

        synchronized public void resumeTime() {
            pauseFlag = false;
            notify();
        }

        synchronized public void pauseCheck() {
            if (pauseFlag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
        @Override
        public void run() {
            textInit(); // 처음 글자 생성
            input.requestFocus();
            int colorChange = 0;
            int speedLevel;

            JLabel levelLabel = new JLabel();
            levelLabel.setSize(150, 25);
            levelLabel.setLocation(200, 5);
            levelLabel.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 25));
            levelLabel.setForeground(new Color(0x49312F));
            add(levelLabel);

            while (true) {
                pauseCheck();

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

    class TimeThread extends Thread {
        private int sec = 0;
        private boolean pauseFlag = false;

        public int getSec() {
            return sec;
        }

        public void pauseTime() {
            pauseFlag = true;
        }

        synchronized public void resumeTime() {
            pauseFlag = false;
            notify();
        }

        synchronized public void pauseCheck() {
            if (pauseFlag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

        synchronized public void run() {
            while (true) {
                pauseCheck();
                sec++;
                if (sec == 30) {
                    gameThread.interrupt();
                    timeThread.interrupt();
                    GameOverFrame gameOver = new GameOverFrame(scorePanel.getScore(), playerName);
                    setVisible(false);
                }
                try {
                    timeLabel.setText(sec + "초");
                    sleep(1_000);
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
