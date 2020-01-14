package parser;

import enums.RequestTypeEnum;
import org.junit.Assert;
import org.junit.Test;


public class RequestParserTest {

    @Test
    public void parseBadPath_returnUnkown(){
        String badPath = "/";
        RequestTypeEnum request = RequestParser.parseRequestType(badPath);
        Assert.assertEquals(RequestTypeEnum.UNKNOWN, request);
    }

    @Test
    public void parsepath_returnLoginType(){
        String path = "/12/login";
        RequestTypeEnum request = RequestParser.parseRequestType(path);
        Assert.assertEquals(RequestTypeEnum.LOGIN, request);
    }


    @Test
    public void parsePostHighScorePath_returnPostScoreType(){
        String path = "/2/score?sessionkey=UICSNDK";
        RequestTypeEnum request = RequestParser.parseRequestType(path);
        Assert.assertEquals(RequestTypeEnum.POST_SCORE, request);
    }

    @Test
    public void parseGetHighScore_returnGetHighScoreType(){
        String path = "/2/highscorelist";
        RequestTypeEnum request = RequestParser.parseRequestType(path);
        Assert.assertEquals(RequestTypeEnum.GET_HIGHSCORE, request);
    }
}
