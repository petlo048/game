package parser;

import exception.BadRequestException;

public class LoginRequestParser {

    private static final String PATH_DIVIDER = "/";

    public static int parseUserId(String path) throws BadRequestException {
        try {
            String[] splittedPath = path.split(PATH_DIVIDER);
            return Integer.parseInt(splittedPath[1]);
        } catch (Exception e) {
            throw new BadRequestException("Invalid path");
        }
    }
}
