package appium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;

public class BaseTest_old {


    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public BaseTest_old() {
        System.out.println("BaseTest constructor called");
    }

    @BeforeClass
    public void configureAppium() throws MalformedURLException {
        System.out.println("=== BEFORE CLASS STARTED ===");


        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\16220\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                // ✅ Enable Gestures plugin here
                .withArgument(() -> "--use-plugins", "gestures");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        System.out.println("✅ Appium started!");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("Appium started with gestures plugin enabled!");
//        service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\16220\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//                .withIPAddress("127.0.0.1").usingPort(4723).build();
//
//        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("samsung SM-E066B");
//        options.setDeviceName("samsung SM-M066B");
//        options.setDeviceName("Xiaomi 2201116SI");
        options.setDeviceName("Xiaomi POCO M2 Pro");

//        options.setApp("D:\\Appium\\src\\main\\resources\\ApiDemos-debug.apk");
//        options.setApp("D:\\Appium\\src\\main\\resources\\ApiDemos-debug.apk");
        options.setApp("D:\\Appium\\src\\main\\resources\\General-Store.apk");
        options.setCapability("appium:autoGrantPermissions", true);
        options.setCapability("appium:disableHiddenApiPolicy", true);
        options.setCapability("appium:ignoreHiddenApiPolicyError", true);
        options.setCapability("appium:newCommandTimeout", 60);
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
            System.out.println("=== DRIVER CREATED? " + (driver != null) + " ===");
        }
        catch (Exception e)
        {
            System.out.println("❌ Driver creation failed: " + e.getMessage());

        }
    }

    public void longPressAction(RemoteWebElement element){
        driver.executeScript("gesture: longPress", Map.of("elementId",element.getId(), "pressure",0.5,"duration", 2000));
    }
    // ✅ Screenshot method for Extent report
    public String getScreenshotPath(String testCaseName) throws IOException {

        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Save inside reports/screenshots folder
        String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
        new File(screenshotDir).mkdirs();

        String screenshotName = testCaseName + ".png";
        String destinationPath = screenshotDir + screenshotName;

        Files.copy(source.toPath(), new File(destinationPath).toPath());

        // ✅ return relative path so html can display image
        return "screenshots/" + screenshotName;
    }
    public String getScreenshotBase64() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }


    @BeforeMethod
    public void initDriver() throws MalformedURLException {

        if (driver == null) {
            System.out.println("✅ Starting Driver in @BeforeMethod...");

            UiAutomator2Options options = new UiAutomator2Options();
            options.setDeviceName("Xiaomi POCO M2 Pro");
            options.setApp("D:\\Appium\\src\\main\\resources\\General-Store.apk");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        }
    }

//    public void restartApp() {
//        if (driver != null) {
//            driver.terminateApp("com.androidsample.generalstore");
//            driver.activateApp("com.androidsample.generalstore");
//        }
//    }

    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}

