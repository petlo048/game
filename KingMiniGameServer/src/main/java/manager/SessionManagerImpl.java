package manager;

import exception.BadRequestException;
import model.Session;
import util.UniqueSessionKeyGenerator;
import util.UniqueSessionKeyGeneratorImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SessionManagerImpl implements SessionManager {

    private final String NO_SUCH_SESSIONKEY = "No such sessionKey";
    private final long SESSION_TIME_OUT_MS = 600_000;

    // Thread safety
    private static final Map<String, Session> sessionCache = new ConcurrentHashMap<>();

    private UniqueSessionKeyGenerator uniqueSessionKeyGenerator = UniqueSessionKeyGeneratorImpl.getInstance();

    private static SessionManager instance;

    //Singleton
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManagerImpl();
        }
        return instance;
    }

    @Override
    public String login(final int userid) {
        String sessionKey = uniqueSessionKeyGenerator.nextSessionKey();
        long sessionValidUntil = System.currentTimeMillis() + SESSION_TIME_OUT_MS;
        synchronized (instance) {
            sessionCache.put(sessionKey, new Session(userid, sessionValidUntil));
        }
        return sessionKey;
    }

    public boolean validSession(final String sessionKey, long time) {
        return sessionCache.containsKey(sessionKey) ? sessionCache.get(sessionKey).validSession(time) : false;
    }

    public void wipeOut() {
        sessionCache.clear();
        uniqueSessionKeyGenerator.reset();
    }

    public int getUserId(final String sessionKey) throws BadRequestException {
        if (!sessionCache.containsKey(sessionKey)) {
            throw new BadRequestException(NO_SUCH_SESSIONKEY);

        }
        return sessionCache.get(sessionKey).getUserId();
    }
}
