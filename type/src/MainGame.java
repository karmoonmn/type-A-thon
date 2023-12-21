import javax.swing.*;
import java.util.Scanner;

public class MainGame extends JFrame {
    private TimerGameLogic gameLogic;
    private StopWatchGameLogic gameLogic2;

    public MainGame (int gameDuration, String textFile, int numOfWord){
        gameLogic = new TimerGameLogic(gameDuration, textFile, numOfWord);

        getContentPane().add(gameLogic);
        setTitle("Type-A-Thon");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MainGame(int gameDuration, String textFile, int numOfWord, boolean isStopWatch){
        gameLogic2 = new StopWatchGameLogic(gameDuration, textFile, numOfWord);
        getContentPane().add(gameLogic2);
        setTitle("Type-A-Thon");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose the gamemode : ");
        int gamemode = scanner.nextInt();
        scanner.nextLine();


        SwingUtilities.invokeLater(() -> {
            switch (gamemode){
                case 0 : //quickstart
                    new MainGame(10, "dictionary.txt", 50).setVisible(true);
                    break;
                case 1 : //can edit timer, choose yes or no punctuation
                    new MainGame(30, "dictionary2.txt", 50).setVisible(true);
                    break;
                case 2 : //stopwatch, choose num of word
                    new MainGame(0, "dictionary.txt", 10, true).setVisible(true);
                    break;
                case 3 : //quote
                    new MainGame(30, "quote.txt", 5).setVisible(true);
                    break;
                default:
                    System.out.println();
            }


        });
    }
}