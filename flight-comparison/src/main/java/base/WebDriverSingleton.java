package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {

    private static WebDriver driver;

    private static void initialize() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static WebDriver getWebdriverInstance() {
        if (driver == null) {
            initialize();
        }
        return driver;
    }
}
