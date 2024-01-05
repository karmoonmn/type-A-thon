import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PlayersProfile extends JPanel {
    private static ArrayList<String> emails;
    private JPanel profileDetailsPanel;
    private Image profileBackground ;
    private final String relativePath = "picture.png";

    public PlayersProfile(){

        fetchEmails(); // Fetch usernames from the database
        setLayout(new BorderLayout());
        createProfileButtons(); // Create buttons for each username
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(relativePath);

            if (inputStream == null) {
                System.err.println("Can't find reference: " + relativePath);
                System.exit(0);
            }

            BufferedImage image = ImageIO.read(inputStream);
            profileBackground = image;

        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
            System.exit(0);
        }


        // Profile details panel at the center
        profileDetailsPanel = new JPanel(new GridLayout(0, 1, 0, 0));
        profileDetailsPanel.setBorder(new EmptyBorder(50, 200, 100, 200));
        profileDetailsPanel.setOpaque(false); // Making the profile details panel transparent
        JScrollPane scrollPane = new JScrollPane(profileDetailsPanel);
        scrollPane.getViewport().setOpaque(false); // Make the viewport transparent
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        // Title label at the top
        JLabel titleLabel = new JLabel("PROFILES", SwingConstants.CENTER);
        titleLabel.setFont(new Font("MONOSPACED BOLD", Font.BOLD, 30));
        add(titleLabel, BorderLayout.NORTH);

}
    // Fetch usernames from the database
     static void fetchEmails() {
        // Fetch usernames from the database and populate the usernames ArrayList
        emails = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT players_email FROM player_profiles"; // Assuming your table is named player_profiles
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String email = resultSet.getString("players_email");
                emails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions properly in your application
        }
    }

    private void createProfileButtons() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonsPanel.setOpaque(false); // Set the buttons panel to be non-opaque
        buttonsPanel.setBorder(null); // Remove any existing border
        
        Dimension buttonSize = new Dimension(150, 30);
        int verticalSpacing = 10; // Adjust the vertical spacing between buttons

        for (String email : emails) {
            JButton profileButton = new JButton(email);
            profileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            profileButton.setAlignmentY(Component.CENTER_ALIGNMENT);
            profileButton.setPreferredSize(buttonSize); // Set the fixed button size
            profileButton.setMaximumSize(buttonSize); // Ensure fixed size
            profileButton.setMinimumSize(buttonSize); // Ensure fixed size
            profileButton.addActionListener(new ProfileButtonListener(email));
            buttonsPanel.add(profileButton);
            profileButton.addActionListener(new ProfileButtonListener(email));
            buttonsPanel.add(Box.createVerticalStrut(verticalSpacing));
            buttonsPanel.add(profileButton);
        }
    
        int preferredHeight = emails.size() * 100;
        // Set the preferred size of buttonsPanel to trigger scrolling
        buttonsPanel.setPreferredSize(new Dimension(200, preferredHeight));
        buttonsPanel.setOpaque(false); // Set the buttons panel to be non-opaque
        buttonsPanel.setBorder(null);
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setOpaque(false);
        separator.setBorder(null);
        separator.setPreferredSize(new Dimension(10, 500));
        JPanel buttonsAndSeparatorPanel = new JPanel(new BorderLayout());
        buttonsAndSeparatorPanel.setBorder(null);
        buttonsAndSeparatorPanel.setOpaque(false); // Make the panel transparent
        buttonsAndSeparatorPanel.add(buttonsPanel, BorderLayout.CENTER);
        buttonsAndSeparatorPanel.add(separator, BorderLayout.EAST);
        JScrollPane scrollPane = new JScrollPane(buttonsAndSeparatorPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false); // Make the viewport transparent;
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.WEST);
    }

    // ActionListener for profile buttons
    private class ProfileButtonListener implements ActionListener {
        private String email;

        public ProfileButtonListener(String email) {
            this.email = email;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            displayPlayerProfile(email);
        }
    }

    private void displayPlayerProfile(String email) {
        profileDetailsPanel.removeAll();
    
        try (Connection connection = DatabaseConnection.getConnection()) {
            // Check if records exist for the selected email
            String countQuery = "SELECT COUNT(*) AS record_count " +
                    "FROM results " +
                    "INNER JOIN player_profiles ON results.players_ID = player_profiles.players_ID " +
                    "WHERE player_profiles.players_email = ?";
            try (PreparedStatement countStatement = connection.prepareStatement(countQuery)) {
                countStatement.setString(1, email);
                try (ResultSet countResult = countStatement.executeQuery()) {
                    countResult.next();
                    int recordCount = countResult.getInt("record_count");
    
                    if (recordCount > 0) {
                        // Records exist, fetch average values for last 10 games and all-time
                        String last10AvgQuery = "SELECT AVG(accuracy) AS last_10_avg_accuracy, AVG(wpm) AS last_10_avg_wpm " +
                                "FROM (SELECT accuracy, wpm " +
                                "FROM results " +
                                "INNER JOIN player_profiles ON results.players_ID = player_profiles.players_ID " +
                                "WHERE player_profiles.players_email = ?" +
                                "ORDER BY timestamp DESC " +
                                "LIMIT 10) AS last_10_games ";
                        try (PreparedStatement last10AvgStatement = connection.prepareStatement(last10AvgQuery)) {
                            last10AvgStatement.setString(1, email);
                            try (ResultSet last10AvgResult = last10AvgStatement.executeQuery()) {
                                double last10AvgAccuracy = 0.0;
                                double last10AvgWPM = 0.0;
    
                                if (last10AvgResult.next()) {
                                    last10AvgAccuracy = last10AvgResult.getDouble("last_10_avg_accuracy");
                                    last10AvgWPM = last10AvgResult.getDouble("last_10_avg_wpm");
                                }
    
                                String allTimeAvgQuery = "SELECT AVG(accuracy) AS all_time_avg_accuracy, AVG(wpm) AS all_time_avg_wpm " +
                                        "FROM results " +
                                        "INNER JOIN player_profiles ON results.players_ID = player_profiles.players_ID " +
                                        "WHERE player_profiles.players_email = ?";
                                try (PreparedStatement allTimeAvgStatement = connection.prepareStatement(allTimeAvgQuery)) {
                                    allTimeAvgStatement.setString(1, email);
                                    try (ResultSet allTimeAvgResult = allTimeAvgStatement.executeQuery()) {
                                        double allTimeAvgAccuracy = 0.0;
                                        double allTimeAvgWPM = 0.0;
    
                                        if (allTimeAvgResult.next()) {
                                            allTimeAvgAccuracy = allTimeAvgResult.getDouble("all_time_avg_accuracy");
                                            allTimeAvgWPM = allTimeAvgResult.getDouble("all_time_avg_wpm");
                                        }
                                        

                                        // Display fetched data in the UI (labels, etc.)
                                        JLabel usernameLabel = new JLabel("Username: " + email);
                                        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
                                        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                        profileDetailsPanel.add(usernameLabel);
    
                                        JPanel leftPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                                        JLabel last10GamesLabel = new JLabel("Last 10 Games", SwingConstants.CENTER);
                                        last10GamesLabel.setFont(new Font("Arial", Font.BOLD, 16));
                                        last10GamesLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                        leftPanel.add(last10GamesLabel);
    
                                        JLabel last10AccuracyLabel = new JLabel("Avg Accuracy: " + last10AvgAccuracy);
                                        JLabel last10WPMLabel = new JLabel("Avg WPM: " + last10AvgWPM);
                                        last10AccuracyLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                        last10WPMLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
                                        leftPanel.add(last10AccuracyLabel);
                                        leftPanel.add(last10WPMLabel);
    
                                        JPanel rightPanel = new JPanel(new GridLayout(0, 1, 0, 10));
                                        JLabel allTimeLabel = new JLabel("All Time", SwingConstants.CENTER);
                                        allTimeLabel.setFont(new Font("Arial", Font.BOLD, 16));
                                        allTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                        rightPanel.add(allTimeLabel);
    
                                        JLabel allTimeAccuracyLabel = new JLabel("Avg Accuracy: " + allTimeAvgAccuracy);
                                        JLabel allTimeWPMLabel = new JLabel("Avg WPM: " + allTimeAvgWPM);
                                        allTimeAccuracyLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                        allTimeWPMLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
                                        rightPanel.add(allTimeAccuracyLabel);
                                        rightPanel.add(allTimeWPMLabel);
    
                                        JPanel parentPanel = new JPanel(new GridLayout(1, 2, 10, 0));
                                        parentPanel.setOpaque(false);
                                        parentPanel.setBorder(null);
                                        leftPanel.setOpaque(false);
                                        leftPanel.setBorder(null);
                                        rightPanel.setOpaque(false);
                                        rightPanel.setBorder(null);
                                        parentPanel.add(leftPanel);
                                        parentPanel.add(rightPanel);
                                        
                                        // Add the parent panel to the profileDetailsPanel
                                        profileDetailsPanel.add(parentPanel);
                                        

                                    }
                                }
                            }
                        }
                    } else {
                        // No records found for the player
                        JLabel noDataLabel = new JLabel("No data available for this player.");
                        noDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        profileDetailsPanel.add(noDataLabel);
                    }
                }
            }
    
            revalidate(); // Refresh the panel to display new components
            repaint();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle potential exceptions properly in your application
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (profileBackground != null) {
            g.drawImage(profileBackground, 0, 0, this);
        }
    }
    
}