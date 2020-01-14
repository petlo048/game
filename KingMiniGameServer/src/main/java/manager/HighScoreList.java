package manager;

import model.HighScore;
import util.Parameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreList {

    private final String CSV_DELIMITER = ",";

    // Thread safe
    private List<HighScore> highScores = Collections.synchronizedList(new ArrayList<>());

    public HighScoreList() {
    }

    /**
     * If the userId has no present highScore in the highScore list - add the new highScore
     * to the list
     *
     * If the userId in the newScore already has a highScore in the highScore list, then if the new
     * highScore is better then the present one - replace with the new (better) highScore
     *
     * Finally sort the list and only keep the top values (how many is defined in Parameters.MAX_NMBR_OF_HIGHSCORES)
     *
     * @param newHighScore
     */
    public void addHighScore(HighScore newHighScore) {
        HighScore presentHighScoreForUser = getPresentHighScoreForUser(newHighScore.getUserId());

        if (presentHighScoreForUser != null) {
            if (newHighScore.getPoints() > presentHighScoreForUser.getPoints()) {
                highScores.remove(presentHighScoreForUser);
                highScores.add(newHighScore);
            }
        }
        else{
            highScores.add(newHighScore);
        }

        Collections.sort(highScores);
        highScores = highScores.subList(0, Math.min(highScores.size(), Parameters.MAX_NMBR_OF_HIGHSCORES));
    }

    private HighScore getPresentHighScoreForUser(int userId) {

        for (HighScore highScore : highScores) {
            if (userId== highScore.getUserId()) {
                    return highScore;
            }
        }
        return null;
    }

    public String toCsvString() {
        List<String> highScoresAsStrings = new ArrayList<>();
        highScores.stream().forEach(score -> highScoresAsStrings.add(score.toString()));
        return String.join(CSV_DELIMITER, highScoresAsStrings);
    }

}
