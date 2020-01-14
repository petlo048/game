package parser;

import enums.RequestTypeEnum;

public class RequestParser {

    private static final String LOGIN_IDENTIFIER = "login";
    private static final String POSTSCORE_IDENTIFIER = "score?";
    private static final String GETHIGHSCORE_IDENTIFIER = "highscorelist";
    private static final String REQUEST_DIVIDER = "/";

    public static RequestTypeEnum parseRequestType(String path) {

        String[] splittedString = path.split(REQUEST_DIVIDER);

        if (splittedString.length < 3) {
            return RequestTypeEnum.UNKNOWN;
        }

        if (splittedString[2].matches(LOGIN_IDENTIFIER)) {
            return RequestTypeEnum.LOGIN;
        }

        if (splittedString[2].matches(POSTSCORE_IDENTIFIER + "(.*)")) {
            return RequestTypeEnum.POST_SCORE;
        }

        if (splittedString[2].matches(GETHIGHSCORE_IDENTIFIER)) {
            return RequestTypeEnum.GET_HIGHSCORE;
        }

        return RequestTypeEnum.UNKNOWN;
    }
}
