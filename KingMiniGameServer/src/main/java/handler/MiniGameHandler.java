package handler;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import factory.RequestFactory;
import request.Request;
import util.Parameters;

import java.io.IOException;

public class MiniGameHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        try {
            validate(httpExchange);
            Request request = RequestFactory.createRequestFromPath(httpExchange.getRequestURI().getPath());
            request.execute(httpExchange);
            final byte[] responseBody = request.getResponseAsBytes();
            final int statusCode = request.getStatusCode();
            setResponse(httpExchange, responseBody, statusCode);

        } catch (Exception e) {
            final String responseBody = "fatal exception";
            setResponse(httpExchange, responseBody.getBytes(Parameters.CHARSET), Parameters.INTERNAL_SERVER_ERROR);

        } finally {
            httpExchange.close();
        }
    }

    private void validate(HttpExchange httpExchange) {
        /*
            Intentionally no logic here - I just want to show that I think it is good idea to
            perform some validation before processing the request.
         */
    }

    private void setResponse(HttpExchange httpExchange, byte[] rawResponseBody, int statusCode) throws IOException {
        final Headers headers = httpExchange.getResponseHeaders();
        headers.set(Parameters.HEADER_CONTENT_TYPE, String.format("%s; charset=%s", Parameters.APPLICATION, Parameters.CHARSET));
        httpExchange.sendResponseHeaders(statusCode, rawResponseBody.length);
        httpExchange.getResponseBody().write(rawResponseBody);
    }
}
