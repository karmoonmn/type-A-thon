package game.version2;

import javax.swing.*;
import java.awt.*;

public class Score extends Component {
    private GameLogic gameLogic;
    private int correctCharCnt;


    public void checkWord(String typedWord) {
        String[] wordTyped = typedWord.replaceAll("\\<.*?>", "").split("\\s+");
        String[] wordDisplayed = gameLogic.getLabel().getText().replaceAll("\\<.*?>", "").split("\\s+");
        int totalCnt = wordTyped.length;
        for (int i = 0; i < totalCnt; i++) {
            if (wordTyped[i].equals(wordDisplayed[i])) {
                correctCharCnt += wordTyped[i].length();
            }
        }
        double minute = (double) gameLogic.getGameDuration() / 60;
        double wpm = (correctCharCnt / 5.0) / minute;
        JOptionPane.showMessageDialog(this, "WPM = " + ((int)wpm));
        System.out.println((int) wpm);

    }
}
