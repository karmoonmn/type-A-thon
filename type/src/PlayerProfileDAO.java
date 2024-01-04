import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerProfileDAO {
    private Connection connection;

    public PlayerProfileDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle this exception properly in your application
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle this exception properly in your application
        }
    }

    public boolean registerPlayer(String fullname, String email, String password) {
        String query = "INSERT INTO Player_profiles (players_fullname, players_email, players_password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle this exception properly in your application
            return false;
        }
    }

    public boolean authenticatePlayer(String email, String password) {
        String query = "SELECT * FROM Player_profiles WHERE players_email = ? AND players_password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next(); // Returns true if a row is found with the provided credentials
        } catch (SQLException e) {
            e.printStackTrace(); // Handle this exception properly in your application
            return false;
        }
    }

}