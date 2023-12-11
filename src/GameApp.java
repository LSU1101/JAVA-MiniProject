import javax.swing.*;
import java.util.Vector;

public class GameApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) { }
        new StartDisplay();
    }
}