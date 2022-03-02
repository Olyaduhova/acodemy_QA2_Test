import constans.Messages;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.CartPage;
import page_object.HomePage;
import utils.LocalDriverManager;
import utils.PropertiesReader;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static constans.Messages.COUPON_IS_APPLIED;
import static constans.Messages.COUPON_IS_REMOVED;
//import static constans.Messages.COUPON_IS_REMOVED;

@Slf4j

public class CouponCodeTest {

    private final WebDriver driver = LocalDriverManager.getInstance();
    HomePage homePage = new HomePage();
    CartPage cart = new CartPage();

    @BeforeEach
    public void init() {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        log.info("Step 1 : User opens the Online Shop page.");
        driver.get(PropertiesReader.getProperties().getProperty("home.page"));
    }

    @Test
    public void couponCodeTest() {
        log.info("Step 2: User selects any product on the main page by clicking on Add to cart button.");
        homePage.productSelection();
        log.info("Step 3: User clicks on the View cart button under selected product.");
        homePage.cartCheck();
        log.info("Step 4 : User puts coupon code to the Coupon code field.");
        cart.addCouponCode("easy_discount");
        log.info("Step 5 : User clicks on the Apply coupon button.");
        cart.applyCouponButton();
        log.info("Step 6: Success message check.");
        cart.checkSuccessMessage(COUPON_IS_APPLIED);
        log.info("Step 6 : User clicks on the Remove button near the Coupon code and discount string in Cart totals");
        cart.removeCouponWithJs();
        log.info("Step 7: Check message is there coupon code removed.");
        cart.checkSuccessMessage(COUPON_IS_REMOVED);
    }

    @AfterEach
    public void tearDown() {
        LocalDriverManager.closeDriver();
    }


}
