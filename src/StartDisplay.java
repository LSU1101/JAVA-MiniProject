import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartDisplay extends JFrame {
    private ImageIcon icon = new ImageIcon("homeScreen.png");
    private Image img = icon.getImage();
    public StartDisplay() {
        setTitle("추추 간식주기 게임");
        setSize(800, 600);
        setContentPane(new startPanel());

        setLayout(null);
        setResizable(false);

        JButton startButton = new JButton("시작!");
        startButton.setSize(70, 50);
        startButton.setBorder(new LineBorder(new Color(255, 240, 240), 5));
        startButton.setBackground(new Color(255, 240, 240, 255));
        startButton.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 15));
        startButton.setLocation(120, 440);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame gameFrame = new GameFrame();
                setVisible(false);
            }
        });
        add(startButton);

        setVisible(true);
    }


    class startPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            //super.paintComponent(g); // 기존의 그림을 지우고 다시 그리도록 추가

            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); // this or null

        }
    }
}
