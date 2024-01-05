import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


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
        JOptionPane.showMessageDialog(null, message);    
        saveResultsToDatabase(accuracy, wpm);
    }
       
    

private void saveResultsToDatabase(double accuracy, double WPM) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            int playerID = getPlayerIdFromEmail(Profile.email);                            // Fetch playerId using emai
            String insertQuery = "INSERT INTO results (players_ID, accuracy, wpm) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, playerID); // Replace userId with the actual user's ID
                preparedStatement.setDouble(2, accuracy);
                preparedStatement.setDouble(3, WPM);
                preparedStatement.executeUpdate();
            }
            System.out.println("Results saved to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Accuracy : " + (int) accuracy + " WPM : " + (int) WPM);
    }

    public int getPlayerIdFromEmail(String email) {
        int playerId = -1; // Default value or error handling if player is not found
        try (Connection connection = DatabaseConnection.getConnection()) {
            String selectQuery = "SELECT players_id FROM player_profiles WHERE players_email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        playerId = resultSet.getInt("players_id");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        }
        return playerId;
    }
}