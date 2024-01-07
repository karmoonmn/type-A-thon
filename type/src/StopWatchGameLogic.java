import java.util.Timer;
import java.util.TimerTask;

public class StopWatchGameLogic extends GameLogic {

    public StopWatchGameLogic(int gameDuration, String textFile, int numOfWord, String userName) {
        super(gameDuration, textFile, numOfWord, userName);
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