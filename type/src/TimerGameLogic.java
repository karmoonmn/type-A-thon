import java.util.Timer;
import java.util.TimerTask;

public class TimerGameLogic extends GameLogic {
     boolean punctuation;

    public TimerGameLogic(int gameDuration, String textFile, int numOfWord) {
        super(gameDuration, textFile, numOfWord);
    }
    
    public TimerGameLogic(int gameDuration, String textFile, int numOfWord, boolean punctuation) {
        super(gameDuration, textFile, numOfWord);
        this.punctuation = punctuation;
    }
    
    @Override
    public void getRandomWord() {
        if (punctuation) {
            randomWord = generateRandomText.generateWord(numOfWord, true);
        } else {
            randomWord = generateRandomText.generateWord(numOfWord);
        }
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
