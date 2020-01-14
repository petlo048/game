package manager;

import model.HighScore;
import model.Score;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HighScoreManagerImpl implements HighScoreManager {

    private static HighScoreManager instance;

    //Thread safety
    private final Map<Integer, HighScoreList> highScoreListPerLevel = new ConcurrentHashMap<>();

    //Singleton
    public static HighScoreManager getInstance() {
        if (instance == null) {
            instance = new HighScoreManagerImpl();
        }
        return instance;
    }

    @Override
    public void add(Score score) {

        int level = score.getLevel();

        if (!highScoreListPerLevel.containsKey(level)) {
            highScoreListPerLevel.put(level, new HighScoreList());
        }

        highScoreListPerLevel.get(level).addHighScore(new HighScore(score.getUserId(), score.getPoints()));
    }

    @Override
    public String getHighScoresAsCsvString(int level) {
        if (!highScoreListPerLevel.containsKey(level)) {
            return "";
        }
        return highScoreListPerLevel.get(level).toCsvString();
    }

    @Override
    public void wipeOut() {
        highScoreListPerLevel.clear();
    }
}
