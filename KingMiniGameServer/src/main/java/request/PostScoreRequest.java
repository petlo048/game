package request;

import com.sun.net.httpserver.HttpExchange;
import exception.BadRequestException;
import manager.HighScoreManager;
import manager.HighScoreManagerImpl;
import manager.SessionManager;
import manager.SessionManagerImpl;
import model.PostScoreRequestData;
import model.Score;
import util.Parameters;

public class PostScoreRequest extends Request {

    private final SessionManager sessionManager;
    private final HighScoreManager highScoreManager;

    public PostScoreRequest() {
        this.sessionManager = SessionManagerImpl.getInstance();
        this.highScoreManager = HighScoreManagerImpl.getInstance();
    }

    @Override
    public void execute(HttpExchange httpExchange) {
        try {
            PostScoreRequestData requestData = PostScoreRequestData.createFromHttpExchange(httpExchange);

            if (sessionManager.validSession(requestData.getSessionKey(), System.currentTimeMillis())) {
                Score score = new Score(
                                sessionManager.getUserId(requestData.getSessionKey()),
                                requestData.getScore(),
                                requestData.getLevel());

                highScoreManager.add(score);
            } else {
                this.statusCode = Parameters.BAD_REQUEST;
                this.response = Parameters.INVALID_SESSION_RESPONSE;
            }

        } catch (BadRequestException e) {
            this.statusCode = Parameters.BAD_REQUEST;
            this.response = e.getMessage();

        } catch (Exception e) {
            this.statusCode = Parameters.INTERNAL_SERVER_ERROR;
            this.response = e.getMessage();
        }
    }
}
