package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LocalDriverManager;
import utils.PropertiesReader;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver = LocalDriverManager.getInstance();
    public final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(PropertiesReader.getProperties().getProperty("explicit.wait"))));

    public By addToCartButton = By.xpath("//a[@href=\"?add-to-cart=18\"]");
    public By viewCartButton = By.xpath("//a[contains(@class, 'added_to_cart')]");

    public void productSelection() {
        driver.findElement(addToCartButton);
        driver.findElement(addToCartButton).click();
    }

    public void cartCheck() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        driver.findElement(viewCartButton);
        driver.findElement(viewCartButton).click();
    }

}
