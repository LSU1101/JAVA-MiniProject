import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class StartDisplay extends JFrame {
    private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("homeScreen.png")));
    private Image img = icon.getImage();
    private JTextField inputName = new JTextField();
    public GameFrame gameFrame;

    public StartDisplay() {
        setTitle("추추 간식주기 게임");
        setSize(800, 600);
        setContentPane(new startPanel());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        inputName.setBounds(320, 515, 100 ,30);
        inputName.setBorder(new LineBorder(new Color(0xF6F1E5), 2));
        inputName.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 18));

        inputName.setHorizontalAlignment(JTextField.CENTER);
        add(inputName);

        JButton startButton = new JButton("시작!");
        startButton.setBounds(430, 515, 60, 30);
        startButton.setBorder(new LineBorder(new Color(255, 240, 240), 5));
        startButton.setBackground(new Color(255, 240, 240, 255));
        startButton.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 15));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = inputName.getText();
                if (name.equals("")) { // 이름을 입력하지 않았다면
                    JOptionPane.showMessageDialog(StartDisplay.this, "이름을 입력해주세요!!");
                    inputName.requestFocus();
                    return;
                } else if (name.length() > 5) { // 5글자가 넘어가면 (게임 오버 창에서 5글자 이상은 프레임을 넘어감)
                    JOptionPane.showMessageDialog(StartDisplay.this, "최대 5글자입니다!!");
                    inputName.setText("");
                    inputName.requestFocus();
                    return;
                }
                gameFrame = new GameFrame(name);
                setVisible(false);
            }
        });
        add(startButton);

        setVisible(true);
    }


    class startPanel extends JPanel {
        // 배경 이미지
        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this); // this or null
        }
    }
}
