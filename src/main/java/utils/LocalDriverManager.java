package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;
import java.util.Locale;

public class LocalDriverManager {

    private static WebDriver driver;

    public static WebDriver getInstance() {
        if (driver == null) {
            driver = configureDriver();
        }

        return driver;
    }

    public static WebDriver localDriver() {

        String browser = PropertiesReader.getProperties().getProperty("browser").toLowerCase();

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "msedge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                throw new UnsupportedOperationException("Following browser is not supported: " + browser);
        }
    }

    @SneakyThrows

    public static RemoteWebDriver remoteWebDriver() {
        String browser = PropertiesReader.getProperties().getProperty("browser").toLowerCase();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        return new RemoteWebDriver(URI.create("http://164.92.199.142:4444/wd/hub/").toURL(), capabilities);
    }

    public static WebDriver configureDriver() {
        String machine = PropertiesReader.getProperties().getProperty("machine").toLowerCase();
        switch (machine) {
            case "local":
                return localDriver();
            case "remote":
                return remoteWebDriver();
            default:
                throw new RuntimeException("Unsupported machine: " + machine);
        }
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}

