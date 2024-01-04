import java.util.Timer;
import java.util.TimerTask;

public class TimerGameLogic extends GameLogic {

    public TimerGameLogic(int gameDuration, String textFile, int numOfWord) {
        super(gameDuration, textFile, numOfWord);
    }
    
    public TimerGameLogic(int gameDuration, String textFile, int numOfWord, boolean punctuation) {
        super(gameDuration, textFile, numOfWord);
    }
    
    @Override
    public void getRandomWord() {
        randomWord = GenerateRandomText.generateWord(numOfWord, true);
    }

    @Override
    public void startGame() {
        super.startGame();
        timerCountDown();
    }

    public void timerCountDown() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (second >= 1) {
                    second--;
                    timeGone++;
                    updateTimerDisplay();
                } else {
                    endGame();
                }
            }
        }, 0, 1000);
    }

    @Override
    public void displayScore() {
        score.accuracyWPM(correctCharactersTyped, totalCharactersTyped, totalWordsTyped, correctWordsTyped, timeGone);
    }
}
