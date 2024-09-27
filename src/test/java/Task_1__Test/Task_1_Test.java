package Task_1__Test;
import ReuseTests.ConfigLoader;
import ReuseTests.RetryAnalyzer;
import io.qameta.allure.Description;
import org.apache.logging.log4j.Logger;
import ReuseTests.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.example.Task_1_Page.Task_1;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Task_1_Test extends BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    Task_1 sc;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        ConfigLoader configLoader = new ConfigLoader();
        // Load URLs from config file
        String urlTask1 = configLoader.getProperty("urlTask1");
        OpenBrowser(urlTask1);
    }

    @Description("-\tOpen amazon link “https://www.amazon.com/” then type \" car accessories\"\n" +
            "-\tSelect the first Item\n" +
            "-\tAdd item to the cart\n" +
            "-\tGo to the cart and check that item is added successfully\n")
    @Test(priority = 1, dataProvider = "addItemData_Task1", dataProviderClass = BaseTest.class,retryAnalyzer = RetryAnalyzer.class)
    public void Add_Item_to_Cart(String title, String searchItem, String addItemElement, String assertItemAdded, String cartTitle) throws InterruptedException {
        sc=new Task_1(BaseTestDriver);
        wait = new WebDriverWait(BaseTestDriver, Duration.ofSeconds(30));
        logger.info("Navigating to Amazon homepage");
        if (sc.assert_Title(title)){
            logger.info("Title matched: " + title);
            sc.Search_item(searchItem);
            scrollPage(0,500);
            sc.Add_item_to_cart(addItemElement);
            sc.Assert_Item_Added(assertItemAdded);
            scrollPage(0,-1000);
            Thread.sleep(3000);
            String Description=sc.get_item_index();
            System.out.println(Description);
            scrollPage(0,-1000);
            sc.click_on_add();
            sc.Go_to_Cart();
            sc.Assert_Cart_Title(cartTitle);
            sc.Assertion_item_card(Description);
            logger.info("Item Description: " + Description);

        }else{
            logger.error("Incorrect page. Expected: " + title);
            System.out.println("You are not in the correct page");
        }
    }
    @Description("-\tOpen today's deals\n" +
            "-\tfrom the left side filters select \"Headphones\" and \"grocery\"\n" +
            "-\tfrom the discount part choose \"10% off or more\"\n" +
            "-\tgo to the fourth page then select any item and add it to the cart\n")
    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void Add_item_scenario_2() throws InterruptedException {
        ConfigLoader configLoader=new ConfigLoader();
        String title= configLoader.getProperty("Task1title");
        String cartTitle=configLoader.getProperty("cartTitle");
        sc=new Task_1(BaseTestDriver);
        if (sc.assert_Title(title)){
            sc.setDismiss();
            sc.Click_on_today_deals();
            scrollPage(0,1500);
            sc.Click_On_SeeMore();
            Thread.sleep(1000);
            sc.Click_on_grocery();
            BaseTestDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
            scrollPage(0,1500);
            BaseTestDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            sc.Click_on_discount();
            BaseTestDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            scrollPage(0,2500);
            // you must log in first and get a verification code from sms message
            Thread.sleep(3000);
            scrollPage(0,1000);
            if (!sc.verfiy_sign_in()) {
                sc.setMove_pages(4);
                sc.select_item_list();
                String Description=sc.get_item_listName();
                sc.Go_to_Cart();
                sc.Assert_Cart_Title(cartTitle);
                sc.Assertion_item_list_card(Description);
            } else {
                logger.warn("User is not signed in. Sign in required.");
            }
        } else {
            logger.error("Incorrect page. Expected: " + title);
        }
    }
}
