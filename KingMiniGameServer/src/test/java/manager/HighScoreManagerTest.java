package manager;

import model.Score;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HighScoreManagerTest {
    //Object in tests
    HighScoreManager highScoreManager;

    /**
     * Clear cache between tests
     */
    @After
    public void cleanUp() {
        highScoreManager.wipeOut();
    }

    @Test
    public void noScores_verifyEmptyListString() {
        // arrange
        highScoreManager = HighScoreManagerImpl.getInstance();

        // act
        String highScoreList = highScoreManager.getHighScoresAsCsvString(1);

        // assert
        Assert.assertEquals(0, highScoreList.length());
    }

    @Test
    public void addScoresToOneLevel_verifyListCsvString() {
        // arrange
        int level = 2;
        int nrOfScores = 4;
        highScoreManager = HighScoreManagerImpl.getInstance();
        ScoreTestWrapper scoreTestWrapper = createScoreWrapper(nrOfScores, level);

        // act
        scoreTestWrapper.scoreList.stream().forEach(score -> highScoreManager.add(score));
        String highScoreList = highScoreManager.getHighScoresAsCsvString(level);

        // assert
        Assert.assertTrue(scoreTestWrapper.expectedHighScoreString.equals(highScoreList));
    }


    @Test
    public void addScoresToManyLevels_verifyListCsvStringFromEachLevel() {
        // arrange
        highScoreManager = HighScoreManagerImpl.getInstance();

        int levels = 2;
        int baseNrOfScores = 4;
        List<ScoreTestWrapper> scoreTestWrappers = new ArrayList<>();
        for (int level = 0 ; level <= levels ; level++)  {
            scoreTestWrappers.add( createScoreWrapper(baseNrOfScores + level, level));
        }

        // act
        for (ScoreTestWrapper testWrapper : scoreTestWrappers) {
            testWrapper.scoreList.stream().forEach(score -> highScoreManager.add(score));
        }

        // assert
        for (int level = 0 ; level <= levels ; level++)  {
            String highScoreList = highScoreManager.getHighScoresAsCsvString(level);
            ScoreTestWrapper scoreTestWrapper = scoreTestWrappers.get(level);
            Assert.assertTrue(scoreTestWrapper.expectedHighScoreString.equals(highScoreList));
        }
    }

    @Test
    public void addDuplicates_verifyListCsvString() {
        // arrange
        int level = 2;
        highScoreManager = HighScoreManagerImpl.getInstance();
        Score score1 = new Score(1, 2, level);
        Score score2 = new Score(1, 3, level);
        Score score3 = new Score(1, 2, level);
        Score score4 = new Score(1, 2, level);

        String expectedString = "1-3";
        // act
        highScoreManager.add(score1);
        highScoreManager.add(score2);
        highScoreManager.add(score3);
        highScoreManager.add(score4);
        String highScoreList = highScoreManager.getHighScoresAsCsvString(level);

        // assert
        Assert.assertTrue(expectedString.equals(highScoreList));

    }
    /**
     * Creates a list of Scores that will be used in test. If all Scores in that list are added to
     * a HighScoreList the expected toCsvString() output from such a HighScoreList is returned in the HelperWrapper
     *
     * @param nrOfScores the number of scores that should be generated
     * @param level the level on the Score
     * @return HelperWrapper
     */
    private ScoreTestWrapper createScoreWrapper(int nrOfScores, int level) {
        ScoreTestWrapper wrapper = new ScoreTestWrapper();
        List<Score> scores = new ArrayList<>();
        String expectedString = "";

        for (int i = 0; i < nrOfScores; i++) {
            int userId = i + 100;
            int points = i + 1;
            scores.add(new Score(userId, points, level));
            expectedString = (String.format("%s-%s", userId, points).concat(expectedString));
            if (i < (nrOfScores - 1)) expectedString = (",").concat(expectedString);
        }

        wrapper.expectedHighScoreString = expectedString;
        wrapper.scoreList = scores;
        return wrapper;
    }

    /**
     * HelperClass for testdata generation and expected result
     */
    private class ScoreTestWrapper {
        public List<Score> scoreList;
        String expectedHighScoreString;
    }

}
