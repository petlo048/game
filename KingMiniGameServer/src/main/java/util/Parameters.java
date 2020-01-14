package util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Parameters {

    // Config
    public static final String LOCALHOST = "localhost";
    public static final int DEFAULT_PORT = 8080;
    public static final int BACKLOG = 1;

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION = "application/json";
    public static final Charset CHARSET =  StandardCharsets.UTF_8;

    // Constants
    public static final String SESSION_KEY_PREFIX = "SES";
    public static final int MAX_NMBR_OF_HIGHSCORES = 15;

    // Responses
    public static final String INVALID_SESSION_RESPONSE = "Invalid session";
    public static final String BAD_REQUEST_RESPONSE = "Bad Request";

    // StatusCodes
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int STATUS_OK = 200;
    public static final int BAD_REQUEST = 400;
}
