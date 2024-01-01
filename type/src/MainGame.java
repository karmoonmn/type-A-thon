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


    public MainGame(int gameDuration, String textFile, int numOfWord, String mode) {
        gameLogic = new TimerGameLogic(gameDuration, textFile, numOfWord);
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


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the gamemode : ");
        int gamemode = scanner.nextInt();
        scanner.nextLine();


        SwingUtilities.invokeLater(() -> {
            switch (gamemode) {
                case 0: //quickstart
                    new MainGame(50, "dictionary.txt", 10, "Default Mode").setVisible(true);
                    break;
                case 1: //can edit timer, choose yes or no punctuation
                    new MainGame(30, "dictionary2.txt", 50, "Timed Mode").setVisible(true);
                    break;
                case 2: //stopwatch, choose num of word
                    new MainGame(0, "dictionary.txt", 100, true).setVisible(true);
                    break;
                case 3: //quote
                    new MainGame(30, "quote.txt", 5, "Quotes Mode").setVisible(true);
                    break;
                case 4: //sudden death
                    new MainGame(30, "dictionary.txt", 40, 3).setVisible(true);
                default:
                    System.out.println();
            }


        });
    }
}