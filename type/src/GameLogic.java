import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameLogic extends MyPanel {
    public int totalCharTyped;
    public int wrongTyped;

    public Score score;
    public Timer timer;
    private JLabel timerLabel;
    public int second = gameDuration;

    public GameLogic(int gameDuration, String textFile, int numOfWord) {
        super(gameDuration, textFile, numOfWord);
        setRandomTextButton();
        setSameTextButton();
        showTimer();
        score = new Score();

    }

    @Override
    public void setRandomTextButton() {
        super.setRandomTextButton();
        textField.setText("");
        startGame();
    }

    @Override
    public void setSameTextButton() {
        super.setSameTextButton();
    }

    public void startGame() {
        resetCount();
        textfieldListener();

        //document listener for textfield

    }

    public void textfieldListener() {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                //when the user type the first character, timer will be started
                if (textField.getText().length() == 1) {
//                    timerCountDown();
                }

                //each character is being checked when the user is typing
//                checkCharacter();
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
        SwingUtilities.invokeLater(() -> {
            //stop timer
            timer.cancel();

            //calculate score& display score
            displayScore();

            //reset timer
            second = gameDuration;
            refreshShowTimer();

            //reset label text colour
            resetPanelColour();

            //reset textfield
            textField.setText("");
            textField.enable(false);
//        JOptionPane.showMessageDialog(this, "Timer stopped");

            //reset the count for WPM, accuracy
            resetCount();
        });

    }
    public void displayScore () {
//        score.calAccuracyWPM(totalCharTyped, wrongTyped, gameDuration);
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

    //display timer
    public void showTimer() {
        int hr = gameDuration / 3600;
        int min = (gameDuration % 3600) / 60;
        int sec = gameDuration % 60;

        String timeString = String.format("%02d:%02d:%02d", hr, min, sec);
        timerLabel = new JLabel(timeString, JLabel.CENTER);
        timePanel.add(timerLabel, BorderLayout.CENTER);
    }

    public void refreshShowTimer() {
        int hr = gameDuration / 3600;
        int min = (gameDuration % 3600) / 60;
        int sec = gameDuration % 60;

        String timeString = String.format("%02d:%02d:%02d", hr, min, sec);
        timerLabel.setText(timeString);
    }

    public void updateTimerDisplay() {
        int hr = second / 3600;
        int min = (second % 3600) / 60;
        int sec = second % 60;

        String timeString = String.format("%02d:%02d:%02d", hr, min, sec);
        timerLabel.setText(timeString);
    }
}