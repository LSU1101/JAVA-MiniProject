import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private GamePanel gamePanel = null;
    public String playerName;

    public GameFrame(String playerName) {
        setTitle("떨어지는 단어를 입력하자!");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        makeMenu();
        gamePanel = new GamePanel(playerName);

        getContentPane().add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void makeMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        menuBar.setBackground(new Color(0x6B5553));

        JMenu game = new JMenu("Game");
        JMenuItem exitItem = new JMenuItem("Exit");
        game.add(exitItem);

        JMenu edit = new JMenu("Edit");
        JMenuItem addWord = new JMenuItem("Add Word");
        edit.add(addWord);

        game.setForeground(Color.WHITE);
        edit.setForeground(Color.WHITE);

        menuBar.add(game);
        menuBar.add(edit);

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartDisplay startDisplay = new StartDisplay();

                setVisible(false);
            }
        });

        addWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TextAddFrame addText = new TextAddFrame();
            }
        });
    }
}
