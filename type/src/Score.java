import javax.swing.*;

public class Score extends JPanel{

    public void accuracyWPM (int correctCharactersTyped, int totalCharactersTyped,
                             int totalWordsTyped, int correctWordsTyped, int gameDuration){

        double accuracy = (double) correctCharactersTyped / (totalCharactersTyped - totalWordsTyped) * 100.0;
        if (accuracy < 0) {
            accuracy = 0;
        }

//        System.out.println("Correct Words Typed: " + correctWordsTyped);
//        System.out.println("Total Words Typed: " + totalWordsTyped);
//        System.out.println("Total Characters Typed: " + totalCharactersTyped);
//        System.out.println("correctCharactersTyped: " + correctCharactersTyped);
        System.out.println("Accuracy: " + accuracy + "%");

        double minutes = (double) gameDuration / 60.0;
        double wpm = ((double) correctCharactersTyped / 5.0) / minutes;

        System.out.println("Words Per Minute (WPM): " + wpm);
        String message = String.format("WPM        : %.2f\nAccuracy : %.2f%%", wpm, accuracy);
        JOptionPane.showMessageDialog(null, message);    }
}