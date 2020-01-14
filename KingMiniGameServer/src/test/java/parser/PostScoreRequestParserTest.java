package parser;

import exception.BadRequestException;
import org.junit.Assert;
import org.junit.Test;


public class PostScoreRequestParserTest {


    @Test(expected = BadRequestException.class)
    public void parseBadPath_expectBadRequestException() throws BadRequestException {
        String badPath = "/";
        PostScoreRequestParser.parseLevelId(badPath);
    }

    @Test
    public void parsePostHighScorePath_verifyLevelId() throws BadRequestException {
        int exptectedLeveId = 2 ;
        String loginPath = "/" + exptectedLeveId + "/score?sessionkey=UICSNDK";
        int levelId = PostScoreRequestParser.parseLevelId(loginPath);
        Assert.assertEquals(exptectedLeveId, levelId);
    }

}
