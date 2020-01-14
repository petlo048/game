package request;

import com.sun.net.httpserver.HttpExchange;
import exception.BadRequestException;
import manager.SessionManager;
import manager.SessionManagerImpl;
import parser.LoginRequestParser;
import util.Parameters;

public class LoginRequest extends Request {

    private final SessionManager sessionManager;

    public LoginRequest() {
        this.sessionManager = SessionManagerImpl.getInstance();
    }

    @Override
    public void execute(HttpExchange httpExchange) {
        try {
            int userId = LoginRequestParser.parseUserId(httpExchange.getRequestURI().getPath());
            response = sessionManager.login(userId);

        } catch (BadRequestException e) {
            this.statusCode = Parameters.BAD_REQUEST;
            this.response = e.getMessage();
        }
    }
}
