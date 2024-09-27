package Task_2_Test;

import ReuseTests.BaseTest;
import ReuseTests.ConfigLoader;
import ReuseTests.RetryAnalyzer;
import io.qameta.allure.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Task_2_Page.Task_2;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Task_2_Test extends BaseTest {
    private static final Logger log = LogManager.getLogger(Task_2_Test.class);
    Task_2 sc;

    @BeforeClass
    public void setUp() {
        ConfigLoader configLoader = new ConfigLoader();
        // Load URLs from config file
        String urlTask1 = configLoader.getProperty("urlTask2");
        log.info("Opening browser with URL: " + urlTask1);
        OpenBrowser(urlTask1);
    }

    @Description("-\tOpen this website: “https://ksrtc.in/oprs-web/guest/home.do?h=1”\n" +
            "-\tChoose the following ((from \"CHIKKAMAGALURU\" to \"BENGALURU”)) from the popular routes\n" +
            "-\tChoose the arrival date only \n" +
            "-\tClick “Search for bus”\n")
    @Test(priority = 1, dataProvider = "addItemData_Task2", dataProviderClass = BaseTest.class)
    public void Choose_routers(String title, String fromCity, String toCity, String arrivalday, String arrivalMonth) throws InterruptedException {
        sc = new Task_2(BaseTestDriver);
        log.info("Test started: Choose_routers");
        log.info("Expected title: " + title);

        if (sc.Assert_title(title)) {
            log.info("Correct page loaded. Selecting routes...");
            sc.click_from(fromCity);
            log.info("From city selected: " + fromCity);
            sc.click_to(toCity);
            log.info("To city selected: " + toCity);
            sc.enterdate(arrivalday, arrivalMonth);
            log.info("Arrival date selected: " + arrivalday + " " + arrivalMonth);
            sc.click_submit();
            log.info("Clicked 'Search for bus'.");
        } else {
            log.error("You are not in the correct main page.");
        }
    }

    @Description("-\tSelect a seat\n" +
            "-\tChoose the boarding point and dropping point\n" +
            "-\tFill the “Customer” and “Passenger” details (Note: you can use this phone number: 6789125987)\n" +
            "-\tClick on “make payment” and fill all the fields without submitting the payment\n")
    @Test(priority = 2, dataProvider = "addItemData_Task2", dataProviderClass = BaseTest.class)
    public void Choose_seat(String title, String fromCity, String toCity, String arrivalday, String arrivalMonth) throws InterruptedException {
        sc = new Task_2(BaseTestDriver);
        ConfigLoader configLoader = new ConfigLoader();

        String seatavailable = configLoader.getProperty("seat");
        int CardNum = Integer.parseInt(configLoader.getProperty("Card"));
        String SeatRow = configLoader.getProperty("SeatRow");
        int SeatNum = Integer.parseInt(configLoader.getProperty("SeatNum"));
        int Postion = Integer.parseInt(configLoader.getProperty("SeatPostion"));

        log.info("Test started: Choose_seat");
        log.info("Expected title: " + title);

        if (sc.Assert_title(title)) {
            Thread.sleep(5000);
            log.info("Correct page loaded. Verifying route details...");
            sc.Assert_Details_tip(fromCity, toCity, arrivalMonth, arrivalday);
            log.info("Route details verified.");

            sc.Click_select_seat();
            log.info("Selecting a seat...");
            scrollPage(100, -200);
            Thread.sleep(5000);
            sc.Choose_Seat(CardNum, SeatRow, SeatNum, seatavailable, Postion);
            log.info("Seat selected: Row " + SeatRow + ", Seat " + SeatNum);

            Thread.sleep(300);
            sc.select_point1(CardNum);
            log.info("Selected boarding point.");

            Thread.sleep(8000);
            scrollPage(0, 150);
            log.info("Filling customer details...");
            sc.checkoutseat(configLoader.getProperty("Mobile"), configLoader.getProperty("email"), configLoader.getProperty("name"), configLoader.getProperty("gender"), configLoader.getProperty("age"));
            log.info("Customer details filled.");
        } else {
            log.error("You are not in the correct main page.");
        }
    }
}
