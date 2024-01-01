import java.util.Timer;
import java.util.TimerTask;

public class StopWatchGameLogic extends GameLogic {

    public StopWatchGameLogic(int gameDuration, String textFile, int numOfWord) {
        super(gameDuration, textFile, numOfWord);
    }


    @Override
    public void startGame() {
        super.startGame();
        startStopWatch();
    }

    public void startStopWatch() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                second++;
                updateTimerDisplay();
            }
        }, 0, 1000);

    }

    @Override
    public void displayScore() {
        score.accuracyWPM(correctCharactersTyped, totalCharactersTyped, totalWordsTyped, correctWordsTyped, second);
    }
}