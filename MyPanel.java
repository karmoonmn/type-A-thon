import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {
    public int gameDuration;
    public String textFile;
    public int numOfWord;
    public JButton randomText;
    public JButton sameText;
    public JTextField textField;
    public JPanel panel;
    public JLabel[] label;
    public String labelText;
    public JPanel buttonPanel;
    public JPanel timePanel;
    public JPanel textPanel;


    public MyPanel(int gameDuration, String textFile, int numOfWord) {
        this.gameDuration = gameDuration;
        this.textFile = textFile;
        this.numOfWord = numOfWord;
        initialiseUI();
    }
    public void setSameTextButton() {
        sameText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.enable(true);
            }
        });
    }

    public void setRandomTextButton() {
        randomText.addActionListener(new ActionListener() {
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
            textPanel.add(label[i]);
        }
        textPanel.revalidate();

    }

    public void resetPanel(){
        if (label != null){
            textPanel.removeAll();
        }
        textPanel.revalidate();
    }

    public void initialiseUI(){

        setLayout(new BorderLayout());
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //button setup
        randomText = new JButton("Random Text");
        sameText = new JButton("Same Text");
        Dimension buttonSize = new Dimension(150, 30);
        randomText.setPreferredSize(buttonSize);
        sameText.setPreferredSize(buttonSize);
        buttonPanel.add(randomText);
        buttonPanel.add(sameText);


        panel = new JPanel();
        textField = new JTextField();
        timePanel = new JPanel();
        textPanel = new JPanel();

        //panel for time display
        timePanel.setBounds(10, 10 , 70, 30);
        timePanel.setBackground(new Color(250,235,215));

        //panel for text display
        textPanel.setBackground(Color.CYAN);
        textPanel.setBounds(50, 70, 500, 250);

        //adding components
        add(timePanel);
        add(textPanel);
        add(buttonPanel, BorderLayout.NORTH);
        add(panel);
        add(textField, BorderLayout.SOUTH);
        this.setFocusable(true);
    }


}
