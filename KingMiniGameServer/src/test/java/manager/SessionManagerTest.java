package manager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import util.Parameters;

public class SessionManagerTest {

    //Object in tests
    SessionManager sessionManager;
    @After
    public void cleanUp(){
        sessionManager.wipeOut();
    }

    @Test
    public void login_verifyId() {
        // arrange
        sessionManager = SessionManagerImpl.getInstance();
        String expectedSessionKey = Parameters.SESSION_KEY_PREFIX + 0;

        // act
        String sessionKey = sessionManager.login(1);

        // assert
        Assert.assertTrue(sessionKey.equals(expectedSessionKey));
    }

    @Test
    public void MultipleLogin_TwoInstances_verifyId() {
        // arrange
        sessionManager = SessionManagerImpl.getInstance();
        SessionManager sessionManager2 = SessionManagerImpl.getInstance();
        int nrOfCallsFirstInstance = 4;
        int nrOfCalls2ndInstance = 4;
        int totalNrOfCalls = (nrOfCallsFirstInstance + nrOfCalls2ndInstance);
        String expectedSessionKey = Parameters.SESSION_KEY_PREFIX + totalNrOfCalls;

        // act

        for (int i = 0; i <= nrOfCallsFirstInstance; i++) {
            sessionManager.login(i);
        }

        for (int i = nrOfCalls2ndInstance + 1; i < totalNrOfCalls; i++) {
            sessionManager2.login(i);
        }
        String sessionKey = sessionManager.login(1);

        // assert
        Assert.assertTrue(sessionKey.equals(expectedSessionKey));
    }

    @Test
    public void login_verifyValidSessionId() {
        // arrange
        sessionManager = SessionManagerImpl.getInstance();
        long now = System.currentTimeMillis();

        // act
        String sessionKey = sessionManager.login(1);

        // assert
        Assert.assertTrue(sessionManager.validSession(sessionKey, now));
    }

    @Test
    public void nologin_verifyInValidSessionId() {
        // arrange
        sessionManager = SessionManagerImpl.getInstance();
        long now = System.currentTimeMillis();

        // act
        String sessionKey = Parameters.SESSION_KEY_PREFIX + 1;

        // assert
        Assert.assertFalse(sessionManager.validSession(sessionKey, now));
    }
}
