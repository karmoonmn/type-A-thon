import javax.swing.*;
import java.util.Scanner;

public class MainGame extends JFrame {
    private TimerGameLogic gameLogic;
    private StopWatchGameLogic gameLogic2;
    private SuddenDeathGameLogic gameLogic3;

    public void frameSetting() {
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(false);
        setLocationRelativeTo(null);
        setResizable(false);
    }


   public MainGame(int gameDuration, String textFile, int numOfWord, String mode, boolean punctuation) {
        if (punctuation) {
            gameLogic = new TimerGameLogic(gameDuration, textFile, numOfWord, punctuation);
        } else {
            gameLogic = new TimerGameLogic(gameDuration, textFile, numOfWord);
        }
        getContentPane().add(gameLogic);
        setTitle(mode);
        frameSetting();
    }

    public MainGame(int gameDuration, String textFile, int numOfWord, boolean isStopWatch) {
        gameLogic2 = new StopWatchGameLogic(gameDuration, textFile, numOfWord);
        getContentPane().add(gameLogic2);
        setTitle("Words Mode");
        frameSetting();
    }

    public MainGame(int gameDuration, String textFile, int numOfWord, int n) {
        gameLogic3 = new SuddenDeathGameLogic(gameDuration, textFile, numOfWord);
        getContentPane().add(gameLogic3);
        setTitle("Sudden Death");
        frameSetting();
    }


}
