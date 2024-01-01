public class SuddenDeathGameLogic extends TimerGameLogic {


    public SuddenDeathGameLogic(int gameDuration, String textFile, int numOfWord) {
        super(gameDuration, textFile, numOfWord);
    }

    @Override
    public void handleWrongChar() {
        endGame();
    }
}
