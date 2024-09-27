package ReuseTests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;


public class BaseTest {
    public static WebDriver BaseTestDriver;
    public static final Logger log = LogManager.getLogger(BaseTest.class);
    @Description("Enter Amazon Website")
    public void OpenBrowser(String Url){
        WebDriverManager.chromedriver().setup();
        log.info("The browser is opened");
//        BaseTestDriver=new ChromeDriver();
//         Set ChromeOptions for headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        BaseTestDriver= new ChromeDriver(options);
//      The automated tests run in a browser resolution of "1024 x 768" px
        BaseTestDriver.manage().window().setSize(new Dimension(1024,768));
        log.debug("Connect to URL for Task1");
        BaseTestDriver.get(Url);
    }
    @AfterClass
    public void CloseBrowser(){
        if (BaseTestDriver != null) {
            log.info("The browser is closed");
            BaseTestDriver.close();
        }
    }

    @AfterMethod(description = "Take pic for failed testcases")
    public void failedTestCaseScreen(ITestResult TestCaseResult) throws IOException {
        if (TestCaseResult.getStatus()==ITestResult.FAILURE){
            //Take screenshot and add it to a folder and name it with the testcase name
            File SCREENSHOT =Screenshots.takeshots(BaseTestDriver,"C:\\_Automation\\TestScript\\screenshoots\\"+TestCaseResult.getName()+".png");
            System.out.println("ITestResult.Failure is"+ITestResult.FAILURE);
            System.out.println("TestCaseResult.getStatus()"+TestCaseResult.getStatus());
            //Add the failed test case screenshot in the testing report
            Allure.addAttachment(" Page Screenshot", FileUtils.openInputStream(SCREENSHOT));
        }
    }
    public void scrollPage(int p,int pixels){
        JavascriptExecutor js = (JavascriptExecutor) BaseTestDriver;
        js.executeScript("window.scrollBy("+p+","+pixels+")", "");
    }
    @DataProvider(name = "addItemData_Task1")
    public Object[][] getAddItemData() throws FileNotFoundException {
        // Load JSON data from file
        JsonObject jsonObject = JsonParser.parseReader(new FileReader("./src/main/resources/Task_1_TestData.json")).getAsJsonObject();
        JsonArray testCases = jsonObject.getAsJsonArray("Scenario");

        // Convert JSON data to Object array
        Object[][] data = new Object[testCases.size()][5];
        for (int i = 0; i < testCases.size(); i++) {
            JsonObject testCase = testCases.get(i).getAsJsonObject();
            data[i][0] = testCase.get("title").getAsString();
            data[i][1] = testCase.get("searchItem").getAsString();
            data[i][2] = testCase.get("addItemElement").getAsString();
            data[i][3] = testCase.get("assertItemAdded").getAsString();
            data[i][4] = testCase.get("cartTitle").getAsString();
        }

        return data;
    }
    @DataProvider(name = "addItemData_Task2")
    public Object[][] getAddItemData_Task2() throws FileNotFoundException {
        // Load JSON data from file
        JsonObject jsonObject = JsonParser.parseReader(new FileReader("./src/main/resources/Task_2_TestData.json")).getAsJsonObject();
        JsonArray testCases = jsonObject.getAsJsonArray("Task2");

        // Convert JSON data to Object array
        Object[][] data = new Object[testCases.size()][5];
        for (int i = 0; i < testCases.size(); i++) {
            JsonObject testCase = testCases.get(i).getAsJsonObject();
            data[i][0] = testCase.get("title").getAsString();
            data[i][1] = testCase.get("fromCity").getAsString();
            data[i][2] = testCase.get("toCity").getAsString();
            data[i][3] = testCase.get("arrivalday").getAsString();
            data[i][4] = testCase.get("arrivalMonth").getAsString();
        }

        return data;
    }
}
