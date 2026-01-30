package appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.URL;

public class BrowserStackBaseTest {

    public AndroidDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {

        String username = System.getenv("BROWSERSTACK_USERNAME");
        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");

        System.out.println("BS USER = " + username);
        System.out.println("BS KEY  = " + (accessKey != null ? "FOUND" : "NULL"));

        if (username == null || accessKey == null) {
            throw new RuntimeException("BrowserStack credentials not found! Set env variables correctly.");
        }

        String bsUrl = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

        UiAutomator2Options options = new UiAutomator2Options();

        // ✅ BrowserStack capabilities
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Google Pixel 7");   // any valid device name
        options.setPlatformVersion("13.0");        // android version
        options.setApp("bs://f9dcde9250d28eb32f61ef43813398a37a000bd8");

        // ✅ optional project details in BrowserStack dashboard
        options.setCapability("bstack:options", java.util.Map.of(
                "projectName", "Appium Framework",
                "buildName", "Smoke Run",
                "sessionName", "VerifyToastMessage"
        ));

        driver = new AndroidDriver(new URL(bsUrl), options);

        System.out.println("✅ BrowserStack driver started: " + driver);
    }
    public String getScreenshotBase64() {
        if (driver == null) return null;
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    @BeforeMethod(alwaysRun = true)
    public void restartApp() {
        if (driver != null) {
            driver.terminateApp("com.androidsample.generalstore");
            driver.activateApp("com.androidsample.generalstore");
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
