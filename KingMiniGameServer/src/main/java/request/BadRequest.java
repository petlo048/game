package request;

import com.sun.net.httpserver.HttpExchange;
import util.Parameters;

public class BadRequest extends Request {


    @Override
    public void execute(HttpExchange httpExchange) {
        response = Parameters.BAD_REQUEST_RESPONSE;
        statusCode = Parameters.BAD_REQUEST;
    }
}
