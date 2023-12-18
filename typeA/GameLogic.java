import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameLogic extends MyPanel {
    private int totalCharTyped;
    private int wrongTyped;

    private Score score;
    private Timer timer;

    public GameLogic(int gameDuration, String textFile, int numOfWord) {
        super(gameDuration, textFile, numOfWord);
        setStartButton();
        setSameText();
        score = new Score();

    }

    @Override
    public void setStartButton() {
        super.setStartButton();
        startGame();
    }

    @Override
    public void setSameText() {
        super.setSameText();
    }

    public void startGame() {
        System.out.println("startGame is executed");
        resetCount();

        //document listener for textfield
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                //when the user type the first character, timer will be started
                if (textField.getText().length() == 1) {
                    setTimer();
                }

                //each character is being checked when the user is typing
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

    public void endGame() {
        //stop timer
        timer.cancel();

        //reset textfield
        textField.setText("");
        JOptionPane.showMessageDialog(this, "Timer stopped");

        //calculate score & display score
        score.calAccuracyWPM(totalCharTyped, wrongTyped, gameDuration);

        //reset the count for WPM, accuracy
        resetCount();

    }


    public void checkCharacter() {
        //with each character typed, the totalCharTyped will increase
        totalCharTyped++;
        System.out.println("total char : " + totalCharTyped);

        int currentPosition = textField.getText().length() - 1;
        String currentText = textField.getText();

        char currentChar = currentText.charAt(currentPosition);
        char currentLabelChar = labelText.charAt(currentPosition);

        //when the character typed matches with the current character shown on the label,
        //the label coloured blue.
        //if it does not match, the label will be coloured red
        if (currentChar == currentLabelChar) {
            label[currentPosition].setForeground(Color.BLUE);
        } else {
            label[currentPosition].setForeground(Color.RED);
            wrongTyped++;
            System.out.println("total wrong : " + wrongTyped);
        }


    }

    //when the timer is up, endGame() will be executed
    //the colour of label text will return to black
    public void setTimer() {
        timer = new Timer();
        System.out.println("Timer Started");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endGame();
                resetPanelColour();
                textField.enable(false);
            }
        }, gameDuration * 1000);
    }

    public void resetCount() {
        totalCharTyped = 0;
        wrongTyped = 0;
    }

    public void resetPanelColour() {
        for (JLabel lbl : label) {
            lbl.setForeground(Color.BLACK);
        }
    }
}
