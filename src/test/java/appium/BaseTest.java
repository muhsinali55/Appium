package appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.URL;

public class BaseTest {

    public AndroidDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup() throws Exception {
        System.out.println("✅ BEFORE CLASS RUNNING");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Xiaomi POCO M2 Pro");
        options.setApp("D:\\Appium\\src\\main\\resources\\General-Store.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);

        System.out.println("✅ DRIVER CREATED: " + driver);
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
    public void beforeEachTest() {
        System.out.println("✅ BEFORE METHOD RUNNING");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        System.out.println("✅ AFTER CLASS RUNNING");
        if (driver != null) driver.quit();
    }
}
