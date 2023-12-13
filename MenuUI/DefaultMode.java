package MenuUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class DefaultMode {
    public static void start(String mode) {
        def obj = new def(mode);
        obj.generaterandom("C:\\Users\\User\\Documents\\My VS Code\\Learning\\Typegui\\Swing\\randomtext");
    }
}

class def extends JFrame implements ActionListener {

    private JLabel Defaulttxt;
    private JLabel modetxt;
    private JLabel Randomholder1;
    private JLabel Randomholder2;
    private JLabel Randomholder3;
    private JLabel Randomholder4;
    private JTextField textField;

    public def(String mode) {

        setLayout(null); // Set layout to null for absolute positioning

        // print something
        Defaulttxt = new JLabel("DEFAULT");
        modetxt = new JLabel("MODE");
        Randomholder1 = new JLabel();
        Randomholder2 = new JLabel(); // Initialize Randomholder2
        Randomholder3 = new JLabel(); // Initialize Randomholder2
        Randomholder4 = new JLabel(); // Initialize Randomholder2
        textField = new JTextField("Type here");

        Defaulttxt.setBounds(540, 5, 500, 150); // x, y, width, height
        modetxt.setBounds(545, 40, 500, 150);
        Randomholder1.setBounds(150, 100, 1000, 150);
        Randomholder2.setBounds(150, 150, 1000, 150);
        Randomholder3.setBounds(150, 200, 1000, 150);
        Randomholder4.setBounds(150, 250, 1000, 150);
        textField.setBounds(150, 400, 200, 30);

        // Set font and color for welcome label
        Font randomhold = new Font("SansSerif", Font.PLAIN, 30);
        Randomholder1.setFont(randomhold);
        Randomholder1.setForeground(Color.GRAY);

        add(Randomholder1);

        // Set transparent background for the text field
        textField.setOpaque(false);
        add(textField);


        // Set font and color for welcome label
        Font randomhold2 = new Font("SansSerif", Font.PLAIN, 30);
        Randomholder2.setFont(randomhold2);
        Randomholder2.setForeground(Color.GRAY);

        add(Randomholder2);

        Font randomhold3 = new Font("SansSerif", Font.PLAIN, 30);
        Randomholder3.setFont(randomhold3);
        Randomholder3.setForeground(Color.GRAY);

        add(Randomholder3);

        Font randomhold4 = new Font("SansSerif", Font.PLAIN, 30);
        Randomholder4.setFont(randomhold4);
        Randomholder4.setForeground(Color.GRAY);

        add(Randomholder4);

        // Set font and color for welcome label
        Font welcomeFont = new Font("Arial", Font.BOLD, 30);
        Defaulttxt.setFont(welcomeFont);
        Defaulttxt.setForeground(Color.BLUE);

        add(Defaulttxt);

        // Set font and color for menu label
        Font menuFont = new Font("SansSerif", Font.ITALIC, 40);
        modetxt.setFont(menuFont);
        modetxt.setForeground(Color.RED);

        add(modetxt);

        setLocation(150, 90);
        setSize(1250, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void generaterandom(String filePath){
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] lines = content.split("\n");

            Random random = new Random();
            int randomIndex = random.nextInt(lines.length);

            String randomText = lines[randomIndex].trim();
            Randomholder1.setText(randomText);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] lines = content.split("\n");

            Random random = new Random();
            int randomIndex = random.nextInt(lines.length);

            String randomText = lines[randomIndex].trim();
            Randomholder2.setText(randomText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] lines = content.split("\n");

            Random random = new Random();
            int randomIndex = random.nextInt(lines.length);

            String randomText = lines[randomIndex].trim();
            Randomholder3.setText(randomText);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] lines = content.split("\n");

            Random random = new Random();
            int randomIndex = random.nextInt(lines.length);

            String randomText = lines[randomIndex].trim();
            Randomholder4.setText(randomText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
