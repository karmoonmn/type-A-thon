import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;


public class GameLogic extends MyPanel {
    public boolean gameStarted = false;

    Font timerFont = new Font("Times", Font.BOLD, 18);
    public Score score;
    private JLabel timerLabel;
    public int second = gameDuration;
    public int timeGone = 0;

    public int correctCharactersTyped = 0;
    public int currentCorrectChar = 0;
    public int correctWordsTyped = 0;
    public int totalCharactersTyped = 0;
    public int totalWordsTyped = 0;
    public boolean spacePressed = false;

    public int currentSentenceIndex = 0;
    public int sentenceIndex = 0;
    public int instantSentenceIndex = 0;
    public int sentenceWordCount = 0;

    public GameLogic(int gameDuration, String textFile, int numOfWord) {
        super(gameDuration, textFile, numOfWord);
        showTimer();
        score = new Score();
        textfieldListener();
    }


    public void spaceBar() {
        String typedWord = textField.getText().trim();
        String actualWord = currentSentenceArr[sentenceWordCount];
        if (typedWord.equals(actualWord)) {
            correctWordsTyped++;
        }
        if (!typedWord.equals(actualWord) || typedWord.length() < actualWord.length()) {
            correctCharactersTyped -= currentCorrectChar;
            totalCharactersTyped += (actualWord.length() - typedWord.length());
            currentLabelIndex += (actualWord.length() - typedWord.length());
        }

        currentCorrectChar = 0;
        textField.setText("");
//        ((DocumentFilter.FilterBypass) noDeleteAllFilter.getBypass()).remove(0, textField.getDocument().getLength());

        spacePressed = true;
        sentenceWordCount++;
        totalWordsTyped++;
        currentSentenceIndex++;
        instantSentenceIndex = 0;
        currentLabelIndex += 2;

        if (sentenceWordCount == currentSentenceArr.length) {
            currentLabelIndex = 0; // Reset currentLabelIndex for the new word
            if (currentWordIndex < numOfWord - 1) {
                currentWordIndex++;
                setRandomWord();
                instantSentenceIndex = 0;
                currentSentenceIndex = 0;
                sentenceWordCount = 0;
            } else {
                // Handle the end of the game or provide feedback
                //stopwatch mode : timer stopped
                System.out.println("Game Over!");
                endGame();
            }
        }
    }



    public void displayScore() {
        score.accuracyWPM(correctCharactersTyped, totalCharactersTyped, totalWordsTyped, correctWordsTyped, gameDuration);
    }

    public void textfieldListener() {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (!gameStarted && textField.getText().length() == 1) {
                    startGame();
                }
                totalCharactersTyped++;

                if (spacePressed) {
                    spacePressed = false; // Reset the flag
                    return; // Do nothing if space was pressed
                }
                checkCharacter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (spacePressed) {
                    spacePressed = false; // Reset the flag
                    return; // Do nothing if space was pressed
                }
                currentLabelIndex--;
                instantSentenceIndex--;
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getModifiers() & ActionEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_DELETE) {
                    // Prevent deleting all text at once
                    e.consume();
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spaceBar();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public void startGame() {
        gameStarted = true;
        //other codes
    }

    public void endGame() {
        SwingUtilities.invokeLater(() -> {
            //stop timer
            timer.cancel();
            JOptionPane.showMessageDialog(null, "Timer stopped");
            //calculate score& display score
            displayScore();

            //reset timer
            second = gameDuration;
            refreshShowTimer();

            //reset textfield
            textField.setText("");
            textField.enable(false);

            //reset the count for WPM, accuracy
            resetCount();
            sameText.setEnabled(true);
            randomText.setEnabled(true);
        });

    }


    public void checkCharacter() {
        char currentTypedChar = textField.getText().charAt(textField.getText().length() - 1);
        if (textField.getText().length() - 1 < currentSentenceArr[currentSentenceIndex].length() + 1) {
            try {
                char currentLabelChar = currentSentenceArr[currentSentenceIndex].charAt(instantSentenceIndex++);
                if (currentTypedChar == currentLabelChar) {
                    label[currentLabelIndex].setForeground(Color.BLUE);
                    correctCharactersTyped++;
                    currentCorrectChar++;
                } else if (currentTypedChar != currentLabelChar && currentTypedChar != ' ') {
                    handleWrongChar();
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Word completed!");
            }
        } else {
            // Handle the case where the user types more characters than the length of the current word
            //provide feedback or take appropriate action
            System.out.println("Word completed!");
        }

        currentLabelIndex++;
        sentenceIndex++;
    }

    public void handleWrongChar() {
        try {
            label[currentLabelIndex].setForeground(Color.RED);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }


    public void resetCount() {
        sentenceWordCount = 0;
        correctCharactersTyped = 0;
        currentCorrectChar = 0;
        correctWordsTyped = 0;
        totalCharactersTyped = 0;
        totalWordsTyped = 0;
        instantSentenceIndex = 0;
        currentSentenceIndex = 0;
        timeGone = 0;

        currentWordIndex = 0;
        currentLabelIndex = 0;
        spacePressed = false;
        gameStarted = false;
    }

    public void showTimer() {
        int hr = gameDuration / 3600;
        int min = (gameDuration % 3600) / 60;
        int sec = gameDuration % 60;

        String timeString = String.format("%02d:%02d:%02d", hr, min, sec);
        timerLabel = new JLabel(timeString, JLabel.CENTER);
        timerLabel.setFont(timerFont);

        timePanel.add(timerLabel, BorderLayout.CENTER);
    }

    public void refreshShowTimer() {
        int hr = gameDuration / 3600;
        int min = (gameDuration % 3600) / 60;
        int sec = gameDuration % 60;

        String timeString = String.format("%02d:%02d:%02d", hr, min, sec);
        timerLabel.setText(timeString);
    }

    public void updateTimerDisplay() {
        int hr = second / 3600;
        int min = (second % 3600) / 60;
        int sec = second % 60;

        String timeString = String.format("%02d:%02d:%02d", hr, min, sec);
        timerLabel.setText(timeString);

        timerLabel.setFont(timerFont);
    }
}
