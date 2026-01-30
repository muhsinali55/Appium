package appium;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class eCommerce extends BaseTest {

    @Test(priority = 1, groups = {"smoke1"})
    public void VerifyToastMessage() {
        //android.widget.Toast - special widget - if we have 2 if one -
        //  mandatory attribute - name value - message
        System.out.println("Driver value inside test: " + driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")
        ));
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        Assert.assertEquals(toastMessage, "Please enter your name1");
    }

    @Test(priority = 2, groups = {"smoke1"}, dataProvider = "userData", dataProviderClass = TestData.class)
    public void FillForm(String name, String gender, String country) {
        System.out.println(name + " " + gender + " " + country);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")
        ));
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")).sendKeys(name);
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='" + gender + "']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + country + "\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + country + "']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }


//    @Test(priority = 3,groups = {"regression"}, dataProvider = "userData", dataProviderClass = TestData.class)
//    public void VerifyAddToCart(String name, String gender, String country)
//            throws InterruptedException {
//
//        System.out.println(name + " " + gender + " " + country);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")
//        ));
//        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")).sendKeys(name);
//        driver.hideKeyboard();
//        driver.findElement(By.xpath("//android.widget.RadioButton[@text='"+gender+"']")).click();
//        driver.findElement(By.id("android:id/text1")).click();
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+country+"\"))"));
//        driver.findElement(By.xpath("//android.widget.TextView[@text='"+country+"']")).click();
//        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
//        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))"));
//
//        int prodCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
//
//        for (int i =0;i<prodCount;i++){
//            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
//            if(productName.equalsIgnoreCase("Jordan 6 Rings")){
//                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
//            }
//        }
//        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
//        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text","Cart"));
//        String cartProductName = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
//        Assert.assertEquals(cartProductName,"Jordan 6 Rings");
//
//    }

    @Test(priority = 3, groups = {"smoke"}, dataProvider = "AmountData", dataProviderClass = TestData.class)
    public void VerifyTotalAmountInCart(String name, String gender, String country) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")
        ));
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.androidsample.generalstore:id/nameField\"]")).sendKeys(name);
        driver.hideKeyboard();
        driver.findElement(By.xpath("//android.widget.RadioButton[@text='" + gender + "']")).click();
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + country + "\"))"));
        driver.findElement(By.xpath("//android.widget.TextView[@text='" + country + "']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        Thread.sleep(4000);
        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(0).click();
//         driver.findElement(By.xpath("(//android.widget.TextView[@text='ADD TO CART'])[1]")).click();
        // text changes to added to cart
//            driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(1).click();
//            - this will have only one item - array out of index
//        driver.findElements(By.xpath("//android.widget.TextView[@text='ADD TO CART']")).get(1).click();


        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(8000);
        Set<String> contexts = driver.getContextHandles();
        System.out.println("Available Contexts: " + contexts);

        // ✅ Step 3: Switch to WEBVIEW
        for (String contextName : contexts) {
            if (contextName.contains("WEBVIEW")) {
                driver.context(contextName);
                System.out.println("Switched to: " + contextName);
                break;
            }
        }
        // ✅ Step 4: Perform Web actions inside WebView
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[type='search']")
        ));
        searchBox.sendKeys("Appium Hybrid Testing");
        Thread.sleep(5000);


//        //wait for page to fully loaded - use selenium explicit wait
//        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
//        driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).click();
//        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
//        int product_count =productPrices.size();
//        double total_amount = 0;
//        for(int i = 0; i < product_count ; i++) {
//            String amountInString = productPrices.get(i).getText();
////            amountInString.substring(1);
//            Double price = Double.parseDouble(amountInString.substring(1));
//            total_amount = total_amount + price;
//        }
//        System.out.println(total_amount);
//
//        String displaySum = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
//        Double formattedAmount_display_sum = getFormattedAmount(displaySum);
//        Assert.assertEquals(formattedAmount_display_sum, total_amount);
//    }
//
//    public  double getFormattedAmount(String amount){
//        Double total_amount = Double.parseDouble(amount.substring(1));
//        return total_amount;
//    }
//
//    // stale element exception add sleep
//
//    // LongPress

}}
