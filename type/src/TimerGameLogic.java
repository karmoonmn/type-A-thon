import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Timer;
import java.util.TimerTask;

public class TimerGameLogic extends GameLogic{

    public TimerGameLogic(int gameDuration, String textFile, int numOfWord) {
        super(gameDuration, textFile, numOfWord);
    }

    @Override
    public void textfieldListener() {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (textField.getText().length() == 1) {
                    timerCountDown();
                }
                checkCharacter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }

    @Override
    public void displayScore() {
        score.calAccuracyWPM(totalCharTyped, wrongTyped, gameDuration);
    }

    public void timerCountDown() {
//        if (timer == null || timer.purge() > 0) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (second >= 1) {
                    second--;
                    updateTimerDisplay();
                } else {
                    endGame();
                }
            }
        }, 0, 1000);
//        }
    }
}