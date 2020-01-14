package manager;

import exception.BadRequestException;

public interface SessionManager {

    String login(final int userid);
    boolean validSession(final String sessionKey, long time);
    void wipeOut();
    int getUserId(final String sessionKey) throws BadRequestException;
}
