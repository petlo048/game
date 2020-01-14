package factory;

import org.junit.Assert;
import org.junit.Test;
import request.*;

public class RequestFactoryTest {

    @Test
    public void badPath_createBadRequest() {
        String badPath = "/";
        Request request = RequestFactory.createRequestFromPath(badPath);
        Assert.assertTrue(request instanceof BadRequest);
    }

    @Test
    public void loginPath_createLoginRequest() {
        String path = "/12/login";
        Request request = RequestFactory.createRequestFromPath(path);
        Assert.assertTrue(request instanceof LoginRequest);
    }

    @Test
    public void postHighScorePath_returnPostScoreRequest() {
        String path = "/2/score?sessionkey=UICSNDK";
        Request request = RequestFactory.createRequestFromPath(path);
        Assert.assertTrue(request instanceof PostScoreRequest);
    }

    @Test
    public void getHighScore_returnGetHighScoreRequest() {
        String path = "/2/highscorelist";
        Request request = RequestFactory.createRequestFromPath(path);
        Assert.assertTrue(request instanceof GetHighScoreRequest);
    }


}
