package game.version2;

import game.version1.TypeDemo1;

import javax.swing.*;
import java.util.Scanner;

public class Game extends JFrame {
    private GameLogic gameLogic;
    private MyPanel panel;

    public Game (int gameDuration, String textfile, int numOfWord){
        panel = new MyPanel(gameDuration, textfile, numOfWord);
//        gameLogic = new GameLogic(gameDuration, textfile, numOfWord);
    }
    public Game (){//default mode

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to TYPE-A-THON");
        System.out.println("Gamemode 0 (default)");
        System.out.println("Gamemode 1 (timed)");
        System.out.println("Please choose the gamemode : ");

        //gamemode
        //0 : default
        //1 : timed (can choose timer, text with punctuation
        int gamemode = scanner.nextInt();
        scanner.nextLine();


        SwingUtilities.invokeLater(() -> {
            switch (gamemode){
                case 0 :
                    new Game(30, "dictionary.txt", 50).setVisible(true);
                    break;
                case 1 :
                    System.out.println("Please enter timer : ");
                    int timer = scanner.nextInt();
                    new Game(timer, "dictionary2.txt", 50).setVisible(true);
                    break;
                case 2 :
                    System.out.println("Please enter timer : ");
                    timer = scanner.nextInt();
                    new Game(timer, "quote.txt", 5).setVisible(true);
                default:
                    System.out.println();
            }


        });
    }

}
