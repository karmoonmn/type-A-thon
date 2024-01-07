import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class MistypedWordCSV {
    String userName;
    String fileName;
    String mistypedWord;

    public MistypedWordCSV(String userName, String mistypedWord) throws IOException {
        this.userName = userName;
        this.fileName = userName + ".csv";
        this.mistypedWord = mistypedWord;
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        removeSpecialChar();
        updateCSV();
    }

    public MistypedWordCSV(String userName) throws IOException {
        this.userName = userName;
        this.fileName = userName + ".csv";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

    }

    public static void main(String[] args) throws IOException {
        String [] arr = new MistypedWordCSV("moon").getRandomWordsArray(10);
        System.out.println(Arrays.toString(arr));
    }

    public void removeSpecialChar() {
        String specialChars = ",.!?:\"';";
        int lastIndex = mistypedWord.length() - 1;

        // Iterate from the end and remove trailing special characters
        while (lastIndex >= 0 && specialChars.indexOf(mistypedWord.charAt(lastIndex)) != -1) {
            lastIndex--;
        }

        mistypedWord = mistypedWord.substring(0, lastIndex + 1);

    }

    public String[] getAllWordsArray() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Collect all words from the CSV file
            List<String> allWordsList = reader.lines()
                    .map(line -> line.split(",")[0]) // Extract the word part
                    .collect(Collectors.toList());

            return allWordsList.toArray(new String[0]);
        }
    }

    public String[] getRandomWordsArray(int numOfWords) throws IOException {
        String [] word = getAllWordsArray();
        if (word.length == 0) {
            System.out.println("Cannot open correction facility");
            return null;
        }
        String[] newArray = new String[numOfWords];
        Random random = new Random();

        for (int i = 0; i < numOfWords; i++) {
            // Randomly select an index from the source array
            try {
                int randomIndex = random.nextInt(word.length);
                newArray[i] = word[randomIndex];
            } catch (IllegalStateException e){
                System.out.println("Cannot open correction facility");
            }
        }

        return newArray;
    }

    public String[] getTop10WordsArray() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Collect word frequencies into a Map
            Map<String, Integer> wordFrequencies = reader.lines()
                    .map(line -> line.split(","))
                    .collect(Collectors.toMap(parts -> parts[0], parts -> Integer.parseInt(parts[1])));

            // Sort the words based on frequencies in descending order
            List<String> topWordsList = wordFrequencies.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(10)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            // Convert the list to an array
            return topWordsList.toArray(new String[0]);
        }
    }

    public void updateCSV() throws IOException {
        try {
            // Read existing CSV file into a StringBuilder
            StringBuilder csvContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    csvContent.append(line).append(System.lineSeparator());
                }
            }

            // Update or append the mistyped word and its frequency
            String updatedCsvContent = updateCsvContent(csvContent.toString());

            // Write the updated content back to the CSV file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(updatedCsvContent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String updateCsvContent(String csvContent) {
        String[] lines = csvContent.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(",");
            if (parts.length >= 2 && parts[0].equals(mistypedWord)) {
                // Word found, update frequency
                int frequency = Integer.parseInt(parts[1]);
                frequency++;
                lines[i] = mistypedWord + "," + frequency;
                return String.join(System.lineSeparator(), lines);
            }
        }
        return csvContent + mistypedWord + ",1" + System.lineSeparator();

    }


}
