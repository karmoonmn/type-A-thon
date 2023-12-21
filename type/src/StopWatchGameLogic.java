import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatchGameLogic extends GameLogic {

    public StopWatchGameLogic(int gameDuration, String textFile, int numOfWord) {
        super(gameDuration, textFile, numOfWord);
    }

    @Override
    public void textfieldListener() {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (textField.getText().length() == 1) {
                    startStopWatch();
                }
                checkCharacter();

                if (textField.getText().length() == labelText.length()){
                    endGame();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    public void startStopWatch() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                second++;
                updateTimerDisplay();
            }
        }, 0, 1000);

    }

    @Override
    public void displayScore() {
        score.calAccuracyWPM(totalCharTyped, wrongTyped, second);
    }
}