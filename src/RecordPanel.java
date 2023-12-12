import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.Buffer;
import java.util.Objects;
import java.util.Vector;



public class RecordPanel extends JPanel {
    private ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("bye.png")));
    private Image image = icon.getImage();
    private File file = new File("records.txt");

    public RecordPanel() {
        setBackground(new Color(114, 102, 84));
        setLayout(null);

        JLabel title = new JLabel("TOP 5");
        title.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 5, 250, 20);
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title);

        // 상위 5명 출력
        JLabel[] player = new JLabel[5];
        for (int i = 0; i < player.length; i++) {
            player[i] = new JLabel("");
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String oneLine = "";

            int i = 0;
            while ((oneLine = bufferedReader.readLine()) != null && i < 5) {
                String[] nameAndScore = oneLine.split(",");
                String name = nameAndScore[0];
                String score = nameAndScore[1];

                player[i].setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
                player[i].setForeground(Color.WHITE);
                player[i].setText(name + " | " + score);
                player[i].setBounds(0, 80 + (i * 50), 250, 20);
                player[i].setHorizontalAlignment(JLabel.CENTER);
                add(player[i]);
                i++;
            }
        } catch (Exception e) {
            System.out.println("records File error.");
        }

        JLabel easterEgg = new JLabel();
        easterEgg.setText("나.. 사실 다이어트 해야해..");
        easterEgg.setForeground(Color.WHITE);
        easterEgg.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
        easterEgg.setBounds(350, 10, 250, 30);
        easterEgg.setHorizontalAlignment(JLabel.CENTER);

        JLabel easterEgg2 = new JLabel();
        easterEgg2.setText("하지만 맛있어!!!!!!");
        easterEgg2.setForeground(Color.WHITE);
        easterEgg2.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
        easterEgg2.setBounds(350, 45, 250, 30);
        easterEgg2.setHorizontalAlignment(JLabel.CENTER);

        JLabel easterEgg3 = new JLabel();
        easterEgg3.setText("모두들 한 학기 동안 수고 많았어!");
        easterEgg3.setForeground(new Color(208, 180, 194));
        easterEgg3.setFont(new Font("Apple SD Gothic Neo", Font.BOLD, 20));
        easterEgg3.setBounds(330, 80, 300, 30);
        easterEgg3.setHorizontalAlignment(JLabel.CENTER);

        JLabel image = new JLabel(icon);
        image.setBounds(350, 120, 250, 208);
        add(image);

        add(easterEgg);
        add(easterEgg2);
        add(easterEgg3);
    }
}