import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MyPanel extends JPanel {
    GenerateRandomText generateRandomText;

    public String userName;
    public int gameDuration;
    public String textFile;
    public int numOfWord;

    //swing components
    public Font font = new Font("Times", Font.PLAIN, 17);
    public JButton exitButton;
    public JButton randomText;
    public JButton sameText;
    public JTextField textField;
    public JPanel panel;
    public JLabel[] label;
    public JPanel buttonPanel;
    public JPanel timePanel;
    public JPanel textPanel;

    public Timer timer;

    public String[] currentSentenceArr;
    public String currentWord;
    public String[] randomWord;
    public int currentWordIndex = 0;
    public int currentLabelIndex = 0;


    public MyPanel(int gameDuration, String textFile, int numOfWord, String userName) {
        this.gameDuration = gameDuration;
        this.textFile = textFile;
        this.numOfWord = numOfWord;
        this.userName = userName;

        initialiseTextFile();

        initialiseUI();
        buttonAction();
        getRandomWord();
    }

    public void initialiseTextFile (){
        generateRandomText = new GenerateRandomText(textFile);
    }

    public void getRandomWord() {
        randomWord = generateRandomText.generateWord(numOfWord);
    }


    public void buttonAction() {
        setExitButton();
        setRandomTextButton();
        setSameTextButton();
    }

    public void setExitButton() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timer != null) {
                    timer.cancel();
                }
                new Menu(userName).setVisible(true);
                Component component = SwingUtilities.getRoot(MyPanel.this);
                if (component instanceof JFrame) {
                    ((JFrame) component).dispose();
                } else {
                    System.err.println("Unable to find JFrame to dispose.");
                }

            }
        });
    }

    public void setSameTextButton() {
        sameText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField.enable(true);
                resetPanel();
                currentWordIndex = 0;
                setRandomWord();
                sameText.setEnabled(false);
                randomText.setEnabled(false);
            }
        });
    }

    public void setRandomTextButton() {
        randomText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField.enable(true);
                resetPanel();
                getRandomWord();
                currentWordIndex = 0;
                setRandomWord();
                sameText.setEnabled(false);
                randomText.setEnabled(false);
            }
        });
    }

    public void setRandomWord() {
        label = new JLabel[numOfWord * 10];
        currentWord = randomWord[currentWordIndex];
        currentSentenceArr = currentWord.split("\\s+");


        int index = 0;
        for (int i = currentLabelIndex; i < currentWord.length() + currentLabelIndex; i++) {
            label[i] = new JLabel(String.valueOf(currentWord.charAt(index++)));
            label[i].setFont(font);
            textPanel.add(label[i]);
        }
        label[currentWord.length() + currentLabelIndex] = new JLabel(" ");
        textPanel.add(label[currentWord.length() + currentLabelIndex]);
        textPanel.revalidate();
    }

    public void resetPanel() {
        textPanel.removeAll();
        textPanel.revalidate();
        textPanel.repaint();
    }

    public void initialiseUI() {

        setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        //button setup

        randomText = new JButton("Random Text");
        randomText.setFont(font);
        sameText = new JButton("Same Text");
        sameText.setFont(font);
        exitButton = new JButton("Exit");
        exitButton.setFont(font);

        Dimension buttonSize = new Dimension(150, 40);
        randomText.setPreferredSize(buttonSize);
        sameText.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(new Dimension(70, 40));
        buttonPanel.add(Box.createRigidArea(new Dimension(160, 0))); // Add a rigid area of 100 pixels

        buttonPanel.add(randomText);
        buttonPanel.add(sameText);
        buttonPanel.add(Box.createRigidArea(new Dimension(100, 0))); // Add a rigid area of 100 pixels

        buttonPanel.add(exitButton);


        panel = new JPanel();
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 45));
        textField.setFont(new Font("Times", Font.PLAIN, 20));
        textField.setEnabled(false);
        timePanel = new JPanel();
        textPanel = new JPanel();

        //panel for time display
        timePanel.setBounds(10, 10, 100, 50);
        timePanel.setBackground(new Color(245, 240, 234));

        //panel for text display
        textPanel.setBackground(new Color(0xC1D3D0));
        textPanel.setBounds(70, 70, 750, 500);

        //adding components
        add(timePanel);
        add(textPanel);
        add(buttonPanel, BorderLayout.NORTH);
        add(panel);
        add(textField, BorderLayout.SOUTH);

        this.setFocusable(true);
    }


}