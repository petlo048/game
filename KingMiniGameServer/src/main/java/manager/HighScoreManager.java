package manager;

import model.Score;

public interface HighScoreManager {
    void add(Score score);

    String getHighScoresAsCsvString(int level);

    void wipeOut();
}
