import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class CorrectionFacility extends TimerGameLogic{
    public CorrectionFacility(int gameDuration, String textFile, int numOfWord, String userName) throws IOException {
        super(gameDuration, textFile, numOfWord, userName);
    }

    @Override
    public void initialiseTextFile() {
        this.textFile = userName + ".csv";
    }

    @Override
    public void getRandomWord()  {
        try {
            randomWord = new MistypedWordCSV(userName).getRandomWordsArray(numOfWord);
            if (randomWord == null){
                JOptionPane.showMessageDialog(null,"You have no mistyped word");
                new Menu(userName).setVisible(true);
                simulateButtonPress();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void simulateButtonPress() {
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButton.doClick();
            }
        });
        timer.setRepeats(false); //  timer execute only once
        timer.start();
    }

    @Override
    public void endGame() {
        super.endGame();

    }

    public static void main(String[] args) throws IOException {
        new MainGame(30, "m", 100, "moon").setVisible(true);
    }
}
