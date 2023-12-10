import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {
    private JTextField wordInput = new JTextField(10);
    public EditPanel() {
        setBackground(new Color(185, 152, 112));
        add(wordInput);
        add(new JButton("Save"));
        add(new JButton("Save As"));

    }
}
