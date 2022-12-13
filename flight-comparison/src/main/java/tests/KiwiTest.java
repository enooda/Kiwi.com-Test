package tests;
import base.TestBase;
import org.junit.Test;
import pages.KiwiPage;

public class KiwiTest extends TestBase {

    @Test
    public void compareFlightsTest() {
        getDriver().get(BASE_URL);
        KiwiPage kiwiPage = new KiwiPage();

        kiwiPage.acceptCookies();
        kiwiPage.fillFromDestination();
        kiwiPage.fillFinalDestination();
        kiwiPage.fillDates();
        kiwiPage.clickSearchButton();
        kiwiPage.selectFlight();
        kiwiPage.bookingTab();
   }
}
