package util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class UniqueSessionKeyGeneratorTest {

    //Object in test
    UniqueSessionKeyGenerator uniqueSessionKeyGenerator;

    @After
    public void cleanUp() {
        uniqueSessionKeyGenerator.reset();
    }


    @Test
    public void uniqueSessionKeyCalls_verifyId() {
        // arrange
        uniqueSessionKeyGenerator = UniqueSessionKeyGeneratorImpl.getInstance();
        int nrOfCalls = 4;
        String expectedId = Parameters.SESSION_KEY_PREFIX + nrOfCalls;

        // act
        for (int i = 0; i < nrOfCalls; i++) {
            uniqueSessionKeyGenerator.nextSessionKey();
        }

        // assert
        Assert.assertTrue(uniqueSessionKeyGenerator.nextSessionKey().equals(expectedId));
    }

    @Test
    public void twoInstancesUniqueSessionKeyCalls_verifyId() {
        // arrange
        uniqueSessionKeyGenerator = UniqueSessionKeyGeneratorImpl.getInstance();
        int nrOfCalls = 4;
        UniqueSessionKeyGenerator uniqueSessionKeyGenerator2 = UniqueSessionKeyGeneratorImpl.getInstance();
        String expectedId = Parameters.SESSION_KEY_PREFIX + (nrOfCalls * 2);

        // act
        for (int i = 0; i < nrOfCalls; i++) {
            uniqueSessionKeyGenerator.nextSessionKey();
        }

        for (int i = 0; i < nrOfCalls; i++) {
            uniqueSessionKeyGenerator2.nextSessionKey();
        }

        // assert
        Assert.assertTrue(uniqueSessionKeyGenerator.nextSessionKey().equals(expectedId));
    }
}
