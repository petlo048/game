package parser;

import exception.BadRequestException;
import org.junit.Assert;
import org.junit.Test;


public class LoginRequestParserTest {


    @Test(expected = BadRequestException.class)
    public void parseBadPath_expectBadRequestException() throws BadRequestException {
        String badPath = "/";
        LoginRequestParser.parseUserId(badPath);
    }

    @Test
    public void parseLoginPath_verifyUserId() throws BadRequestException {
        int exptectedUserId = 12;
        String loginPath = "/" + exptectedUserId + "/login";
        int userId = LoginRequestParser.parseUserId(loginPath);
        Assert.assertEquals(exptectedUserId, userId);
    }
}
