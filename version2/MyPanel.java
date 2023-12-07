package game.version2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JFrame {
    private JButton startButton;
    private GameLogic gameLogic;


    public MyPanel(int gameDuration, String textfile, int numOfWord) {
        setTitle("Type-A-Thon");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startButton = new JButton("Start Typing");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLogic.startGame(gameDuration, textfile, numOfWord);
            }
        });

        setLayout(new BorderLayout());
        add(startButton, BorderLayout.NORTH);
        add(gameLogic.getLabel(), BorderLayout.CENTER);
        add(gameLogic.getTextField(), BorderLayout.SOUTH);

    }
}
