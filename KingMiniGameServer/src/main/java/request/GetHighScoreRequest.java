package request;

import com.sun.net.httpserver.HttpExchange;
import exception.BadRequestException;
import manager.HighScoreManager;
import manager.HighScoreManagerImpl;
import parser.GetHighScoreRequestParser;
import util.Parameters;

public class GetHighScoreRequest extends Request {

    private final HighScoreManager highScoreManager;

    public GetHighScoreRequest() {
        this.highScoreManager = HighScoreManagerImpl.getInstance();

    }

    @Override
    public void execute(HttpExchange httpExchange) {
        try {
            int levelId = GetHighScoreRequestParser.parseLevelId(httpExchange.getRequestURI().getPath());
            response = highScoreManager.getHighScoresAsCsvString(levelId);

        } catch (BadRequestException e) {
            this.statusCode = Parameters.BAD_REQUEST;
            this.response = e.getMessage();
        }
    }
}
