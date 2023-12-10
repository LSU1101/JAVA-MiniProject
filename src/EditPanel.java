import javax.swing.*;
import java.awt.*;

public class EditPanel extends JPanel {
    private JTextField wordInput = new JTextField(10);
    public EditPanel() {
        setBackground(Color.DARK_GRAY);
        add(wordInput);
        add(new JButton("Save"));
        add(new JButton("Save As"));

    }
}
