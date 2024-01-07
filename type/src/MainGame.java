import java.io.IOException;

import javax.swing.JFrame;

public class MainGame extends JFrame {
    private TimerGameLogic gameLogic;
    private StopWatchGameLogic gameLogic2;
    private SuddenDeathGameLogic gameLogic3;
    private CorrectionFacility gameLogic4;

    public void frameSetting() {
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(false);
        setLocationRelativeTo(null);
        setResizable(false);
    }


    public MainGame(int gameDuration, String textFile, int numOfWord, String mode, boolean punctuation, String userName) {
        if (punctuation) {
            gameLogic = new TimerGameLogic(gameDuration, textFile, numOfWord, punctuation, userName);
        } else {
            gameLogic = new TimerGameLogic(gameDuration, textFile, numOfWord, userName);
        }
        getContentPane().add(gameLogic);
        setTitle(mode);
        frameSetting();
    }

    public MainGame(int gameDuration, String textFile, int numOfWord, String mode, String userName) {
        gameLogic2 = new StopWatchGameLogic(gameDuration, textFile, numOfWord, userName);
        getContentPane().add(gameLogic2);
        setTitle(mode);
        frameSetting();
    }

    public MainGame(int gameDuration, String textFile, int numOfWord, int n, String userName)  {
        gameLogic3 = new SuddenDeathGameLogic(gameDuration, textFile, numOfWord, userName);
        getContentPane().add(gameLogic3);
        setTitle("Sudden Death");
        frameSetting();
    }

    public MainGame (int gameDuration, String textFile, int numOfWord, String userName) {
        try {
            gameLogic4 = new CorrectionFacility(gameDuration, textFile, numOfWord, userName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getContentPane().add(gameLogic4);
        setTitle("Correction Facility");
        frameSetting();
    }


}