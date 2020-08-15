package settings.testrail;

import java.util.HashMap;
import java.util.Map;

public class DataProviderHelper {
    private static final Map<String, Integer> testCounter = new HashMap<>();

    public static int getAnnotationIndex(String testName) {
        if (!testCounter.containsKey(testName)) {
            testCounter.put(testName, 0);
            return testCounter.get(testName);
        }

        Integer incrementedCount = testCounter.get(testName);
        incrementedCount++;
        testCounter.put(testName, incrementedCount);
        return testCounter.get(testName);
    }

}
