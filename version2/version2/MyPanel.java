package game.version2;

import game.version1.GenerateRandomText2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel{
    private int gameDuration;
    private String textfile;
    private int numOfWord;
    private JButton startButton;
    private GameLogic gameLogic;
    private JTextField textField;
    private JPanel panel;
    private JLabel [] label;
    private String labelText;


    public MyPanel(){}
    public MyPanel(int gameDuration, String textfile, int numOfWord) {
        this.gameDuration = gameDuration;
        this.textfile = textfile;
        this.numOfWord = numOfWord;

        startButton = new JButton("Start Typing");

        setLayout(new BorderLayout());
        panel = new JPanel();
        textField = new JTextField();
        panel.setLayout(new FlowLayout());
        panel.setFocusable(true);


        this.add(panel, BorderLayout.CENTER);
        this.add(startButton, BorderLayout.NORTH);
        this.add(textField, BorderLayout.SOUTH);
        this.setFocusable(true);

        setStartButton();
    }

    public void setStartButton (){
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("button activated");
                resetPanel();
                setRandomWord();
                gameLogic = new GameLogic(gameDuration, textfile, numOfWord, textField, label, labelText);

//                gameLogic.startGame(textField, label, labelText);

            }
        });
    }

    public void setRandomWord() {
        GenerateRandomText generateRandomText = new GenerateRandomText(textfile);
        String[] word = generateRandomText.generateWord(numOfWord);
        labelText = String.join(" ", word);
        label = new JLabel[labelText.length()];

        for (int i = 0; i < labelText.length(); i++) {
            label[i] = new JLabel(String.valueOf(labelText.charAt(i)));
            label[i].setFont(new Font("Times", Font.BOLD, 16));
            panel.add(label[i]);
        }
        panel.revalidate();

    }

    public void resetPanel(){
        if (label != null){
            panel.removeAll();
        }
        panel.revalidate();
    }

}
