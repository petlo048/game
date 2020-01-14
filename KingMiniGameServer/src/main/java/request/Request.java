package request;

import com.sun.net.httpserver.HttpExchange;
import util.Parameters;

public abstract class Request {
     String response ;
     int statusCode ;

    protected Request() {
        this.response = "";
        this.statusCode = Parameters.STATUS_OK;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public byte[] getResponseAsBytes() {
        return response.getBytes(Parameters.CHARSET);
    }

    public String getResponse(){
        return response;
    }

    public abstract void execute(HttpExchange httpExchange);
}
