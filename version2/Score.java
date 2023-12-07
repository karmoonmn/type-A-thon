package game.version2;

import javax.swing.*;
import java.awt.*;

public class Score extends JPanel{
    private MyPanel panel;
    private GameLogic gameLogic;
    private int correctCharCnt = 0;


    public void checkWord(JLabel label, String typedWord, int gameDuration) {
        String[] wordTyped = typedWord.replaceAll("\\<.*?>", "").split("\\s+");
        String[] wordDisplayed = label.getText().replaceAll("\\<.*?>", "").split("\\s+");
        int totalCnt = wordTyped.length;
        for (int i = 0; i < totalCnt; i++) {
            if (wordTyped[i].equals(wordDisplayed[i])) {
                correctCharCnt += wordTyped[i].length();
            }
        }
        double minute = (double) gameDuration / 60;
        double wpm = (correctCharCnt / 5.0) / minute;
        JOptionPane.showMessageDialog(this, "WPM = " + ((int)wpm));
        System.out.println((int) wpm);

    }
}
