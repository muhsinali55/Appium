package appium;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "userData")
    public Object[][] getUserData() {
        return new Object[][]{
                {"Muhsin", "Male", "Australia"},
                {"Ashly", "Female", "Argentina"},
        };
    }
    @DataProvider(name = "cartData")
    public Object[][] getCartData() {
        return new Object[][]{
                {"Muhsin", "Male", "India"},
                {"Ashly", "Female", "Brazil"},
        };
    }
    @DataProvider(name = "AmountData")
    public Object[][] VerifyTotalAmountInCart() {
        return new Object[][]{
                {"Muhsin", "Male", "Argentina"},
        };
    }
}
