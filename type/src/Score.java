import javax.swing.*;

public class Score extends JPanel{


    public void calAccuracyWPM(int totalCharTyped, int wrongType, int gameDuration){
        int correctType = totalCharTyped - wrongType;
        double accuracy = (double) correctType / totalCharTyped * 100.0;
        double min = gameDuration / 60.0;
        double WPM =  (correctType / 5.0) / min;
        JOptionPane.showMessageDialog(this,
                "Accuracy : " + (int)accuracy + "\nWPM : " + (int)WPM);

        System.out.println("Accuracy : " + (int)accuracy + " WPM : " + (int)WPM);
    }

//
}