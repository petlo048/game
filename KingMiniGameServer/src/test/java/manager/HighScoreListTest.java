package manager;

import model.HighScore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HighScoreListTest {

    private final int highScoreListMaxSize = 15;

    private class HelperWrapper {
        public List<HighScore> highScoreList;
        String expectedHighScoreString;
    }

    @Test
    public void noHighScore_verifyToCsvStringTest(){
        // arrange
        HighScoreList highScoreList = new HighScoreList();
        String expectedString = "";

        // act
        String result = highScoreList.toCsvString();

        // assert
        Assert.assertTrue(result.equals(expectedString));
    }

    @Test
    public void addOneNewScore_verifyToCsvStringTest(){
        // arrange
        HighScoreList highScoreList = new HighScoreList();
        int userId = 1;
        int points = 2;

        HighScore highScore = new HighScore(userId, points);
        String expectedString = String.format("%s-%s",userId, points);

        // act
        highScoreList.addHighScore(highScore);
        String result = highScoreList.toCsvString();

        // assert
        Assert.assertTrue(result.equals(expectedString));
    }


    @Test
    public void addManyNewScore_verifyToCsvStringTest(){
        // arrange
        HighScoreList highScoreList = new HighScoreList();
        HelperWrapper helperWrapper = createHighScoreList(15);
        List<HighScore> listOfHighScores = helperWrapper.highScoreList;
        String expectedString = helperWrapper.expectedHighScoreString;

        // act
        listOfHighScores.stream().forEach(highScore -> highScoreList.addHighScore(highScore));
        String result = highScoreList.toCsvString();

        // assert
        Assert.assertTrue(result.equals(expectedString));
    }

    @Test
    public void fillHighScoreList_AddNewHighScore_verifyToCsvStringTest(){
        // arrange
        HighScoreList highScoreList = new HighScoreList();
        HelperWrapper helperWrapper = createHighScoreListForMaxTest(2);
        List<HighScore> listOfHighScores = helperWrapper.highScoreList;
        String expectedString = helperWrapper.expectedHighScoreString;

        // act
        listOfHighScores.stream().forEach(highScore -> highScoreList.addHighScore(highScore));
        String result = highScoreList.toCsvString();

        // assert
        Assert.assertTrue(result.equals(expectedString));
    }

    private HelperWrapper createHighScoreListForMaxTest(int nrOfScores) {
        HelperWrapper wrapper = new HelperWrapper();
        List <HighScore> highScores = new ArrayList<>();
        String expectedString = "";
        int totalnrOfScore = highScoreListMaxSize + nrOfScores;

        for (int i = 0 ; i < totalnrOfScore; i++){
            int userId = i + 100;
            int points = i + 1;
            highScores.add(new HighScore(userId, points));

            if (i >= nrOfScores) {
                expectedString = (String.format("%s-%s", userId, points).concat(expectedString));
                if (i < (totalnrOfScore - 1)) expectedString = (",").concat(expectedString);
            }
        }

        wrapper.expectedHighScoreString = expectedString;
        wrapper.highScoreList = highScores;
        return wrapper;
    }


    private HelperWrapper createHighScoreList(int nrOfScores) {
        HelperWrapper wrapper = new HelperWrapper();
        List <HighScore> highScores = new ArrayList<>();
        String expectedString = "";

        for (int i = 0 ; i < nrOfScores; i++){
            int userId = i + 100;
            int points = i + 1;
            highScores.add(new HighScore(userId, points));
            expectedString = (String.format("%s-%s", userId, points).concat(expectedString));
            if (i < (nrOfScores - 1)) expectedString = (",").concat(expectedString);
        }

        wrapper.expectedHighScoreString = expectedString;
        wrapper.highScoreList = highScores;
        return wrapper;
    }

}
