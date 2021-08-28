package utility;

import configuration.Environment;

public class DataReader {

    private static final String FILE_RESOURCE_PATH = "testdata/%s/";

    private static String getResourcePath() {
        return String.format(FILE_RESOURCE_PATH, Environment.getEnvironmentName());
    }

    public static String getPathForTestData(String fileName) {
       return ResourceUtil.getResourcePath(getResourcePath() + fileName).replaceFirst("/", "");

    }
}
