package parser;

import exception.BadRequestException;
import org.junit.Assert;
import org.junit.Test;


public class GetHighScoreRequestParserTest {

    @Test(expected = BadRequestException.class)
    public void parseBadPath_expectBadRequestException() throws BadRequestException {
        String badPath = "/";
        GetHighScoreRequestParser.parseLevelId(badPath);
    }

    @Test
    public void parsePostHighScorePath_verifyUserId() throws BadRequestException {
        int exptectedLeveId = 2;
        String loginPath = "/" + exptectedLeveId + "/highscorelist";
        int levelId = GetHighScoreRequestParser.parseLevelId(loginPath);
        Assert.assertEquals(exptectedLeveId, levelId);
    }

}
