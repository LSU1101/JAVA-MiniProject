import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.util.Objects;

public class ScorePanel extends JPanel {
    private int score = 0;
    private ImageIcon img;
    private final JLabel scoreLabel = new JLabel(Integer.toString(score));
    private final JLabel image = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("default.png"))));
    private int correctCount = 0;
    private int incorrectCount = 0;

    public int getScore() {
        return score;
    }

    public ScorePanel() {
        setBackground(new Color(171, 151, 134));
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

        // 두 번 연속 맞았으면
        if (correctCount > 1) {
            img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("happy.png")));
            image.setIcon(img);
        } else if (correctCount == 1){ // 한 번 맞았으면
            img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("default.png")));
            image.setIcon(img);
        }
    }

    public void decrease() { // 감소
        score -= 10;
        correctCount = 0;
        incorrectCount++;
        
        if (score < 0) {
            score = 0;
        }
        
        if (incorrectCount == 1) { // 한 번 틀리면
            img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("sleep.png")));
            image.setIcon(img);
        } else if (incorrectCount > 1) { // 두 번 틀리면
            img = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("angry.png")));
            image.setIcon(img);
        }

        scoreLabel.setText(Integer.toString(score));
    }

    public int scoreCheck() { // 레벨 관리
        if (score >= 100 && score < 200) { // 100 점 이상
            return 2; // level 2
        } else if (score >= 200) { // 200 이상
            return 3; // level 3
        } else { // 100 미만
            return 1; // level 1
        }
    }
}
