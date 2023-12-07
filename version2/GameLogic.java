package game.version2;

import game.version1.GenerateRandomText;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameLogic extends Component {
    private JLabel label;
    private JTextField textField;
    private Timer timer;
    private boolean gameRunning;
    private int correctCharCnt;
    private int gameDuration;
    private String textfile;
    private int numOfWord;
    private Score score;


    public GameLogic (int gameDuration, String textfile, int numOfWord){
        label = new JLabel();
        textField = new JTextField();
        this.gameDuration = gameDuration;
        this.textfile = textfile;
        this.numOfWord = numOfWord;
    }
    public GameLogic(){}

    public JLabel getLabel() {
        return label;
    }

    public JTextField getTextField() {
        return textField;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public void startGame(int gameDuration, String textfile, int numOfWord) {
        gameRunning = true;
        correctCharCnt = 0;

        setRandomWord();
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (textField.getText().length() == 1){
                    System.out.println("Timer started");
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            endGame();
                        }
                    }, GameLogic.this.gameDuration * 1000);
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
    public void setRandomWord() {
        game.version1.GenerateRandomText generateRandomText = new GenerateRandomText(textfile);
        String[] word = generateRandomText.generateWord(numOfWord);
        label.setText("<html><div style='text-align: center;'>" + String.join(" ", word) + "</div></html>");
    }

    public void endGame() {
        gameRunning = false;
        timer.cancel();

        //read user's input
        String typedWord = textField.getText();
        textField.setText("");
        JOptionPane.showMessageDialog(this,"Timer stopped");
        score.checkWord(typedWord);

        //score
        //how to count mistakes ???
//        double minute = (double) gameDuration / 60;
//        double wpm = (correctCharCnt / 5.0) / minute;
//        JOptionPane.showMessageDialog(this, "WPM = " + ((int)wpm));
//        System.out.println((int) wpm);
//        correctCharCnt = 0;
        JOptionPane.showMessageDialog(this,
                "Press the button to get a new random text or you may just start");
    }
}
