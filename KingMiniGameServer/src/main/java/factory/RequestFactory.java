package factory;

import parser.RequestParser;
import request.*;

public class RequestFactory {
    public static Request createRequestFromPath(String path) {
            switch (RequestParser.parseRequestType(path)) {
                case LOGIN:
                    return new LoginRequest();
                case POST_SCORE:
                    return new PostScoreRequest();
                case GET_HIGHSCORE:
                    return new GetHighScoreRequest();
                default:
                    return new BadRequest();
            }
    }
}
