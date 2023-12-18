import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {
    public int gameDuration;
    public String textFile;
    public int numOfWord;
    public JButton startButton;
    public JButton sameText;
    public JTextField textField;
    public JPanel panel;
    public JLabel[] label;
    public String labelText;
    public JPanel buttonPanel;

    public GameLogic gameLogic;

    public MyPanel(int gameDuration, String textFile, int numOfWord) {
        this.gameDuration = gameDuration;
        this.textFile = textFile;
        this.numOfWord = numOfWord;
        initialiseUI();

    }
    public void setSameText() {
        sameText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.enable(true);
            }
        });
    }

    public void setStartButton() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.enable(true);
                System.out.println("Button Activated");
                resetPanel();
                setRandomWord();

            }
        });
    }

    public void setRandomWord() {
        GenerateRandomText generateRandomText = new GenerateRandomText(textFile);
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

    public void initialiseUI(){

        setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        startButton = new JButton("Random Text");
        sameText = new JButton("Same Text");
        Dimension buttonSize = new Dimension(150, 30); // Adjust the size as needed
        startButton.setPreferredSize(buttonSize);
        sameText.setPreferredSize(buttonSize);

        buttonPanel.add(startButton);
        buttonPanel.add(sameText);

        add(buttonPanel, BorderLayout.NORTH);


        panel = new JPanel();
        textField = new JTextField();
//        panel.setLayout(new FlowLayout());
//        panel.setFocusable(true);
//
//
        add(panel, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        this.setFocusable(true);
    }


}
