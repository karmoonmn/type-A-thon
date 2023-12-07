package game.version1;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TypeDemo1 extends JFrame {
    private JFrame frame;
    private JLabel label;
    private JTextField textField;
    private JButton startButton;

    private Timer timer;
    private boolean gameRunning = false;
    private int correctCnt;
    private int totalCnt;
    private int correctCharCnt = 0;

    //variables for switching gamemode
    private int gameDuration;
    private int numOfWord;
    private String textfile;

    public TypeDemo1(int gameDuration, String textfile, int numOfWord) {
        this.gameDuration = gameDuration;
        this.textfile = textfile;
        this.numOfWord = numOfWord;
        initialiseUI();
    }

    public TypeDemo1() { //default mode
        textfile = "dictionary.txt";
        gameDuration = 30;
        numOfWord = 120;
        initialiseUI();
    }

    public void initialiseUI() {
        setTitle("Type-A-Thon");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel();
        textField = new JTextField();
        startButton = new JButton("Start Typing");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        add(startButton, BorderLayout.NORTH);
        setLocationRelativeTo(null);

    }

    public void startGame() {
        gameRunning = true;
        correctCnt = 0;
        totalCnt = 0;
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
                    }, gameDuration * 1000);
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
        GenerateRandomText generateRandomText = new GenerateRandomText(textfile);
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
        checkWord(typedWord);

        //score
        //how to count mistakes ???
        double minute = (double) gameDuration / 60;
        double wpm = (correctCharCnt / 5.0) / minute;
        JOptionPane.showMessageDialog(this, "WPM = " + ((int)wpm));
        System.out.println((int) wpm);
        correctCharCnt = 0;
        JOptionPane.showMessageDialog(this,
                "Press the button to get a new random text or you may just start");
    }

    public void checkWord(String typedWord) {
        String[] wordTyped = typedWord.replaceAll("\\<.*?>", "").split("\\s+");
        String[] wordDisplayed = label.getText().replaceAll("\\<.*?>", "").split("\\s+");
        totalCnt = wordTyped.length;
        for (int i = 0; i < totalCnt; i++) {
            if (wordTyped[i].equals(wordDisplayed[i])) {
                correctCnt++;
                correctCharCnt += wordTyped[i].length();
            }
        }

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
                    new TypeDemo1().setVisible(true);
                    break;
                case 1 :
                    // System.out.println("Please enter timer : ");
                    // int timer = scanner.nextInt();
                    new TypeDemo1(30, "dictionary2.txt", 100).setVisible(true);
                    break;
                case 2 :
                    // System.out.println("Please enter timer : ");
                    // timer = scanner.nextInt();
                    new TypeDemo1(30, "quote.txt", 5).setVisible(true);
                default:
                    System.out.println();
            }


        });
    }


}
