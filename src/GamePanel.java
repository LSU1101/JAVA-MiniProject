import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GamePanel extends JPanel {
    private ScorePanel scorePanel = new ScorePanel();

    public GamePanel() {
        setBackground(Color.GRAY);
        setLayout(new BorderLayout());
        splitPanel();
    }

    private void splitPanel() {
        JSplitPane hPane = new JSplitPane();
        hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        hPane.setDividerLocation(500);
        add(hPane);

        JSplitPane vPane = new JSplitPane();
        vPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        vPane.setDividerLocation(200);
        hPane.setRightComponent(vPane);

        vPane.setTopComponent(scorePanel);
        vPane.setBottomComponent(new EditPanel());
        // vPane.setBorder(new LineBorder(Color.GRAY, 20, true));

        hPane.setRightComponent(vPane);
        hPane.setLeftComponent(new GameGround(scorePanel));
    }
}