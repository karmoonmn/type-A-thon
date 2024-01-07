import java.io.IOException;

public class SuddenDeathGameLogic extends TimerGameLogic {


    public SuddenDeathGameLogic(int gameDuration, String textFile, int numOfWord, String userName) {
        super(gameDuration, textFile, numOfWord, userName);
    }

    @Override
    public void handleWrongChar() {
        super.handleWrongChar();
        try {
            new MistypedWordCSV(userName, actualWord);
        } catch (IOException e) {
            e.printStackTrace();
        }
        endGame();


    }

    @Override
    public void handleWrongWord() {
        try {
            new MistypedWordCSV(userName, actualWord);
        } catch (IOException e) {
            e.printStackTrace();

        }
        endGame();
    }

    @Override
    public void displayScore() {
        score.SuddenDeathAccuracyWPM(correctCharactersTyped, totalCharactersTyped, totalWordsTyped, correctWordsTyped, timeGone);
    }

}
