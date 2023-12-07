package game.version2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {
    private JButton startButton;
    private GameLogic gameLogic;
    public JLabel label;
    private JTextField textField;

    public MyPanel(){}

    public MyPanel(int gameDuration, String textfile, int numOfWord) {
//        setTitle("Type-A-Thon");
//        setSize(600, 400);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel();
        textField = new JTextField();
        startButton = new JButton("Start Typing");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLogic = new GameLogic(gameDuration, textfile, numOfWord);
                gameLogic.startGame(label, textField);
            }
        });


        setLayout(new BorderLayout());
        add(startButton, BorderLayout.NORTH);
        add(label, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);

    }

    public JLabel getLabel() {
        return label;
    }
}
