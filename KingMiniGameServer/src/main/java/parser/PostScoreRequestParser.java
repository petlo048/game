package parser;

import exception.BadRequestException;
import util.Parameters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PostScoreRequestParser {

    private static final String PATH_DIVIDER = "/";
    private static final String QUERY_DIVIDER = "=";

    public static int parseScoreFromBody(InputStream requestBody) throws IOException {
        InputStreamReader isr = new InputStreamReader(requestBody, Parameters.CHARSET);
        BufferedReader br = new BufferedReader(isr);
        return Integer.valueOf(br.readLine());
    }


    public static String parseSessionKey(String query) throws BadRequestException {
        try {
            String[] splittedQuery = query.split(QUERY_DIVIDER);
            return splittedQuery[1];
        } catch (Exception e) {
            throw new BadRequestException("Invalid query");
        }
    }

    public static int parseLevelId(String path) throws BadRequestException {
        try {
            String[] splittedPath = path.split(PATH_DIVIDER);
            return Integer.parseInt(splittedPath[1]);
        } catch (Exception e) {
            throw new BadRequestException("Invalid path");
        }
    }
}
