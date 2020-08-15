package settings.utils;

import java.util.Properties;

public class PropertyLoader {

    public static String loadProperty(String name) {
        Properties props = new Properties();
        try {
            String propFileStage = "/properties/app.properties";
            props.load(PropertyLoader.class.getResourceAsStream(propFileStage));
        } catch (Exception e) {
            throw new NullPointerException("Ошибка в методе loadProperty класса PropertyLoader");
        }

        String value = "";

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }

    public static String loadTestRunId() {
        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream("/properties/testrun.properties"));
        } catch (Exception e) {
            throw new NullPointerException("Ошибка в методе loadTestRunId класса PropertyLoader");
        }

        return props.getProperty("testRunId");
    }

}
