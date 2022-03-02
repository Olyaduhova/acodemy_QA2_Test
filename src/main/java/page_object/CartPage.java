package page_object;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocalDriverManager;
import utils.PropertiesReader;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CartPage {

    private final WebDriver driver = LocalDriverManager.getInstance();
    public final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(PropertiesReader.getProperties().getProperty("implicit.wait"))));


    private final By couponCodeField = By.id("coupon_code");
    private final By couponCodeButton = By.xpath("//button[text()=\"Apply coupon\"]");
    private final By successMessage = By.className("woocommerce-message");
    private final By removeButton = By.className("woocommerce-remove-coupon");
//    private final By checkRemoveSuccessMessage = By.className("woocommerce-message");


    public void addCouponCode(String discountCode) {
        driver.findElement(couponCodeField).sendKeys(discountCode);
    }

    public void applyCouponButton() {
        driver.findElement(couponCodeButton);
        driver.findElement(couponCodeButton).click();
    }

    public void checkSuccessMessage(String message) {
        wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
        wait.until(ExpectedConditions.textToBe(successMessage, message));
        assertThat(driver.findElement(successMessage).getText(), equalTo(message));
    }

    public void removeCouponWithJs() {
        wait.until(ExpectedConditions.presenceOfElementLocated(removeButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(removeButton));
    }

//    public void checkRemoveSuccessMessage (String message){
//        wait.until(ExpectedConditions.presenceOfElementLocated(successMessage));
//        assertThat(driver.findElement(By.linkText("Coupon has been removed.");
//    }

}
