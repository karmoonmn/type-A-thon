package game.version2;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    private static String filePath = "player_profile.csv";

    public void saveProfile(Player player) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(player.toCsv());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Player> loadAllUserProfiles() {
        List<Player> playerProfiles = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                int averageWPM = Integer.parseInt(parts[1]);
                int averageAccuracy = Integer.parseInt(parts[2]);

                Player player = new Player(username, averageWPM, averageAccuracy);
                playerProfiles.add(player);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return playerProfiles;
    }
}

