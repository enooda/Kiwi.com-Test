package pages;

import base.WebDriverSingleton;
import models.Guest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class KiwiPage {
    private static WebDriver driver;
    String fromCity;
    String toCity;
    public WebElement calendar;
    public WebElement searchButton;

    public KiwiPage() {
        driver = WebDriverSingleton.getWebdriverInstance();
        fromCity = "Paris";
        toCity = "Zurich";
        calendar = driver.findElement(By.xpath("//input[@name='search-outboundDate']"));
        searchButton = driver.findElement(By.xpath("//a[contains(@data-test,'Landing')]"));
    }

    public void acceptCookies() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Accept']"))).click();
    }

    public void fillFromDestination() {
        driver.findElement(By.xpath("//div[@data-test='PlacePickerInputPlace-close']")).click();
        WebElement fromDestination = driver.findElement(By.xpath("//div[@data-test='PlacePickerInput-origin']/input[@data-test='SearchField-input']"));
        fromDestination.sendKeys(fromCity);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='CDG Charles de Gaulle Airport']"))).click();
    }

    public void fillFinalDestination() {
        WebElement toDestination = driver.findElement(By.xpath("//div[@data-test='PlacePickerInput-destination']/input[@data-test='SearchField-input']"));
        toDestination.sendKeys(toCity);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Zürich, Switzerland']"))).click();
     }

     public void fillDates() {
     calendar.click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Calendarstyled__CalendarWrapper-sc-612c3d-3 czJssG']")));

        //set departure date: current date + 7 days
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     Calendar depDate = Calendar.getInstance();
        depDate.setTime(new Date());
        depDate.add(Calendar.DATE, 7);
    String departureDate = dateFormat.format(depDate.getTime());

    driver.findElement(By.xpath("//div[@data-value='" + departureDate + "']")).click();

    //set return date: current date + 21 days
    Calendar retDate = Calendar.getInstance();
        retDate.setTime(new Date());
        retDate.add(Calendar.DATE, 21);
    String returnDate = dateFormat.format(retDate.getTime());

        driver.findElement(By.xpath("//div[@data-value='" + returnDate + "']")).click();
        driver.findElement(By.xpath("//div[text()='Set dates']")).click();
     }

    public void clickSearchButton() {
        driver.findElement(By.xpath("//a[@data-test='LandingSearchButton']")).click();
    }

    public void bookingTab() {
        //switch window to Booking.com
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        //accept cookies
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("onetrust-accept-btn-handler"))).click();

        //sort hotels by Top reviewed
        driver.findElement(By.xpath("//button[@data-testid='sorters-dropdown-trigger']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-id='class']"))).click();

        //select hotel
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='e57ffa4eb5'])[5]")));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("(//span[@class='e57ffa4eb5'])[5]")));

        //switch to hotel booking tab
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(2));

        //select room
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("(//select[@class='hprt-nos-select js-hprt-nos-select'])[1]")));
        Select room = new Select(driver.findElement(By.xpath("(//select[@class='hprt-nos-select js-hprt-nos-select'])[1]")));
        room.selectByValue("1");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[contains(@class, 'js-reservation-button')]")));
        driver.findElement(By.xpath("//button[contains(@class, 'js-reservation-button')]")).click();

        fillGuest();

        //click final button
        driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
    }

    private void fillGuest() {
        Guest greatGatsby = new Guest("Great", "Gatsby", "scott@fitzgerald.com","So we beat on, boats against the current, borne back ceaselessly into the past.");
        driver.findElement(By.id("firstname")).sendKeys(greatGatsby.getName());
        driver.findElement(By.id("lastname")).sendKeys(greatGatsby.getSurname());
        driver.findElement(By.id("email")).sendKeys(greatGatsby.getEmail());
        driver.findElement(By.id("email_confirm")).sendKeys(greatGatsby.getEmail());
        driver.findElement(By.id("remarks")).sendKeys(greatGatsby.getMessage());
    }

    public void selectFlight() {
        //switch to kiwi.com
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        //add 1 checked baggage
        driver.findElement(By.xpath("(//button[@aria-label='increment'])[2]")).click();

        //select 3rd flight ticket
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@data-test='BookingButton'])[3]"))).click();

        //continue as a guest
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-test='MagicLogin-GuestTextLink']"))).click();
    }
}