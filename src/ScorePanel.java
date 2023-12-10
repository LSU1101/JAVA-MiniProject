import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ScorePanel extends JPanel {
    private int score = 0;
    private ImageIcon img;
    private final JLabel scoreLabel = new JLabel(Integer.toString(score));
    private final JLabel image = new JLabel(new ImageIcon("scoreImage/default.png"));
    private int correctCount = 0;
    private int incorrectCount = 0;

    public ScorePanel() {
        setBackground(Color.GRAY);
        JLabel scoreText = new JLabel("점수: ");

        scoreText.setForeground(Color.WHITE);
        scoreText.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 30));

        scoreLabel.setForeground(new Color(241, 215, 189));
        scoreLabel.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 30));

        add(scoreText);
        add(scoreLabel);
        add(image);
    }

    public void increase() {
        score += 10;
        scoreLabel.setText(Integer.toString(score));
        incorrectCount = 0;
        correctCount++;
        
        if (correctCount > 1) {
            img = new ImageIcon("scoreImage/happy.png");
            image.setIcon(img);
        } else if (correctCount == 1){
            img = new ImageIcon("scoreImage/default.png");
            image.setIcon(img);
        }
    }

    public void decrease() {
        score -= 10;
        correctCount = 0;
        incorrectCount++;
        
        if (score < 0) {
            score = 0;
        }
        
        if (incorrectCount == 1) {
            img = new ImageIcon("scoreImage/sleep.png");
            image.setIcon(img);
        } else if (incorrectCount > 1) {
            img = new ImageIcon("scoreImage/angry.png");
            image.setIcon(img);
        }

        scoreLabel.setText(Integer.toString(score));
    }
}
