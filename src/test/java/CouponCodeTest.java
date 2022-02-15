import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CouponCodeTest {

    WebDriver driver;

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
    }
    @Test
    public void couponCodeTest(){
//        WebDriver driver = new ChromeDriver(); - уходит в @BeforeEach и перед @BeforeEach
        driver.get("https://shop.acodemy.lv/");
//        driver.quit(); - уходит в @AfterEach
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }


}
