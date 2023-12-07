package game.version1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RandomQuotesPrinter {

    public static void main(String[] args) {
        List<String> quotes = loadQuotes("quote.txt");

        if (!quotes.isEmpty()) {
            shuffleAndPrintQuotes(quotes);

            // Schedule a task to print the entire line after 10 seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    printEntireLine(quotes);
                    timer.cancel(); // Cancel the timer after printing the entire line
                }
            }, 10000); // 10000 milliseconds = 10 seconds
        } else {
            System.out.println("No quotes found.");
        }
    }

    private static List<String> loadQuotes(String filePath) {
        List<String> quotes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("quote.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                quotes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return quotes;
    }

    private static void shuffleAndPrintQuotes(List<String> quotes) {
        Collections.shuffle(quotes);
        for (String quote : quotes) {
            // Extract and print only the part before the dash
            String[] parts = quote.split(" - ");
            if (parts.length > 0) {
                System.out.println(parts[0]);
                System.out.println("-----");
            }
        }
    }

    private static void printEntireLine(List<String> quotes) {
        System.out.println("Printing entire line after 10 seconds:");
        for (String quote : quotes) {
            System.out.println(quote);
            System.out.println("-----");
        }
    }
}
