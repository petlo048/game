package model;

import com.sun.net.httpserver.HttpExchange;
import exception.BadRequestException;
import parser.PostScoreRequestParser;

import java.io.IOException;

public class PostScoreRequestData {

    private final String sessionKey;
    private final int level;
    private final int score;

    public PostScoreRequestData(String sessionKey, int score, int level) {
        this.sessionKey = sessionKey;
        this.score = score;
        this.level = level;
    }

    public static PostScoreRequestData createFromHttpExchange(HttpExchange httpExchange) throws IOException, BadRequestException {
        String sessionKey = PostScoreRequestParser.parseSessionKey(httpExchange.getRequestURI().getQuery());
        int levelId = PostScoreRequestParser.parseLevelId(httpExchange.getRequestURI().getPath());
        int score = PostScoreRequestParser.parseScoreFromBody(httpExchange.getRequestBody());
        return new PostScoreRequestData(sessionKey, score, levelId);
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }
}
