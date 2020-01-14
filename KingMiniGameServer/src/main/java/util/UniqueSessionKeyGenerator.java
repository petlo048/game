package util;

public interface UniqueSessionKeyGenerator {
    String nextSessionKey();

    void reset();
}
