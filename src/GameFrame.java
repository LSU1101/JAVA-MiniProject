import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private GamePanel gamePanel = null;
    private TextSource textSource = null;
    public GameFrame() {
        setTitle("추추 간식주기");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

        makeMenu();
        makeToolbar();
        gamePanel = new GamePanel();
        getContentPane().add(gamePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void makeMenu() {
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu exitMenu = new JMenu("Exit");
        JMenu edit = new JMenu("Edit");
        JMenuItem addWord = new JMenuItem("Add Word");
        edit.add(addWord);

        menuBar.add(exitMenu);
        menuBar.add(edit);

        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        addWord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAddFrame addText = new textAddFrame();
            }
        });
    }

    public void makeToolbar() {
        JToolBar bar = new JToolBar();
        bar.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(bar, BorderLayout.NORTH);
        JButton b = new JButton("Play");
        bar.add(b);

        ImageIcon normalIcon = new ImageIcon("angry.png");
        ImageIcon rolloverIcon = new ImageIcon("rollover.png");
        ImageIcon pressedIcon = new ImageIcon("pressed.png");
        JButton imageButton = new JButton(normalIcon);
        imageButton.setRolloverIcon(rolloverIcon);
        imageButton.setPressedIcon(pressedIcon);
        bar.add(imageButton);
        bar.setFloatable(false);
    }
}
