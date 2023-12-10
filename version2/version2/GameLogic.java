package game.version2;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class GameLogic extends JFrame {
    private MyPanel panel;
    private Timer timer;

    private int numOfWord;
    private boolean gameRunning = false;
    private int gameDuration;
    private String textfile;
    private Score score;
    private int totalCharTyped ;
    private int wrongTyped ;
    private String labelText;


    public GameLogic(int gameDuration, String textfile, int numOfWord, JTextField textField, JLabel [] label, String labelText) {
        this.gameDuration = gameDuration;
        this.textfile = textfile;
        this.numOfWord = numOfWord;
        startGame(textField, label, labelText);
    }


    public void startGame(JTextField textField, JLabel [] label, String labelText) {
        this.labelText = labelText;
        gameRunning = true;
        resetCount();


        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (textField.getText().length() == 1) {
                    System.out.println("Timer started");
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            endGame(textField);
                            resetPanelColour(label);
                        }
                    }, gameDuration * 1000);
                }
                checkCharacter(textField,label);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {}
        });

    }


    public void endGame(JTextField textField) {
        gameRunning = false;
        timer.cancel();

        //read user's input
        String typedWord = textField.getText();
        textField.setText("");
        JOptionPane.showMessageDialog(this, "Timer stopped");
        score = new Score();
        score.calAccuracyWPM(totalCharTyped, wrongTyped, gameDuration);
        resetCount();
    }

    public void checkCharacter(JTextField textField, JLabel[] label) {
        totalCharTyped++;
        System.out.println("total char : " + totalCharTyped);

        int currentPosition = textField.getText().length() - 1;
        String currentText = textField.getText();

        char currentChar = currentText.charAt(currentPosition);
        char currentLabelChar = labelText.charAt(currentPosition);

        if (currentChar == currentLabelChar){
            label[currentPosition].setForeground(Color.BLUE);
        } else {
            label[currentPosition].setForeground(Color.RED);
            wrongTyped++;
            System.out.println("total wrong : " + wrongTyped);
        }


    }
    public void resetCount (){
        totalCharTyped = 0;
        wrongTyped = 0;
    }

    public void resetPanelColour(JLabel[] label){
        for (JLabel lbl : label){
            lbl.setForeground(Color.BLACK);
        }
    }
}
