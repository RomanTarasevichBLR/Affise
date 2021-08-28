package utility;

import aquality.selenium.browser.AqualityServices;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;

public class ScreenshotProvider {

    public static String takeScreenShot(String screenShotName, String testName) {
        try {
            File file = new File("screenshots-results");
            if (!file.exists()) {
                System.out.println("File created " + file);
                file.mkdir();
            }

            File screenshotFile = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("screenshots-results" + File.separator + testName, screenShotName);
            FileUtils.copyFile(screenshotFile, targetFile);

            return screenShotName;
        } catch (Exception e) {
            System.out.println("An exception occured while taking screenshot " + e.getCause());
            return null;
        }
    }
}
