package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import utility.ScreenshotProvider;

public final class TestListener implements ITestListener {

    @Override
    public void onFinish(ITestContext context) {
        String screenShotName = context.getName().trim() + ".png";
        ScreenshotProvider.takeScreenShot(screenShotName, context.getName().trim());
    }
}
