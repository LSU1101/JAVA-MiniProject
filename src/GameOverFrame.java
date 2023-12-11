import javax.swing.*;
import java.awt.*;

public class GameOverFrame extends JFrame {
    public GameOverFrame(int score, String playName) {
        setTitle("게임 오버!");
        setSize(200, 200);
        Container c = getContentPane();

        c.setLayout(null);

        JLabel scoreLabel = new JLabel(playName + " | "  + String.valueOf(score) + "점!!");
        scoreLabel.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));
        scoreLabel.setBounds(0, 40, 200, 30);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        c.add(scoreLabel);

        JLabel comment = new JLabel();
        if (score < 100) {
            comment.setText("아직 배고파!!!");
        } else if (score < 200) {
            comment.setText("배가 아직 안 찼어..");
        } else if (score < 300) {
            comment.setText("이제 좀 배부르다! 고마워!!");
        }
        comment.setForeground(new Color(77, 54, 39));
        comment.setBounds(0, 90, 200, 30);
        comment.setHorizontalAlignment(JLabel.CENTER);
        comment.setFont(new Font("Apple SD Gothic Neo", Font.PLAIN, 20));
        c.add(comment);

        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
