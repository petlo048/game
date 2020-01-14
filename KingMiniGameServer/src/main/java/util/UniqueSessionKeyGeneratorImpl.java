package util;

import java.util.concurrent.atomic.AtomicLong;

public class UniqueSessionKeyGeneratorImpl implements UniqueSessionKeyGenerator {

    private static UniqueSessionKeyGeneratorImpl instance;

    //Thread safety
    private static volatile AtomicLong sequence = new AtomicLong(0);

    //Singleton
    public static UniqueSessionKeyGeneratorImpl getInstance() {
        if (instance == null) {
            instance = new UniqueSessionKeyGeneratorImpl();
        }
        return instance;
    }

    @Override
    public String nextSessionKey() {
        return String.format(Parameters.SESSION_KEY_PREFIX + "%s", sequence.getAndIncrement());
    }


    @Override
    public void reset() {
        sequence = new AtomicLong(0);
    }
}
