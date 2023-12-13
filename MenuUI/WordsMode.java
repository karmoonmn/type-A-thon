package MenuUI;

import javax.swing.JFrame;

public class WordsMode {
    public static void start(String mode) {
        JFrame newFrame = new JFrame(mode);
        newFrame.setLocation(150, 90);
        newFrame.setSize(1250, 650);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        newFrame.setVisible(true);
    }
}
