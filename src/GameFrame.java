import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private GamePanel gamePanel = null;
    private TextSource textSource = null;
    public GameFrame() {
        setTitle("게임");
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

        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("Open"));
        fileMenu.add(new JMenuItem("Save"));
        fileMenu.add(new JMenuItem("Save As"));
        fileMenu.addSeparator(); // ---
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("insert"));
        editMenu.add(new JMenuItem("replace"));
        menuBar.add(editMenu);
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
