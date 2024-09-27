package org.example.Task_2_Page;

import org.checkerframework.checker.units.qual.C;
import org.checkerframework.checker.units.qual.K;
import org.example.ReusePages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.text.DateFormatSymbols;
import java.time.Duration;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Task_2 extends BasePage {

    public Task_2(WebDriver mydriver) {
        super(mydriver);
    }
    HashMap<String,Integer[]> SeatsRow=new HashMap<>();

    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(BasePageDriver)
            .withTimeout(Duration.ofSeconds(30))        // Maximum wait time
            .pollingEvery(Duration.ofSeconds(5))        // Polling interval
            .ignoring(NoSuchElementException.class);
    private By title=By.xpath("//img[@alt=\"Karnataka State Road Transport Corporation - KSRTC\"]");
    private By fromCity=By.id("fromCity");
    private By toCity=By.id("toCity");
    private By departDate=By.id("departDate");
    private By submitSearch=By.id("submitSearch");
    private By click_on_toCity=By.xpath("//*[@id=\"toCity_chosen\"]/a");
    private By click_on_fromList=By.xpath("//*[@id=\"fromCity_chosen\"]/a");
    private By enter_dep_city=By.xpath("//*[@id=\"fromCity_chosen\"]/div/div[1]/input[@placeholder=\"Search Your City Name\"]");
    private By enter_des_city=By.xpath("//*[@id=\"toCity_chosen\"]/div/div[1]/input");
//------------------------------------------------------------------------------------------------------------------------------//
    private By select_seat=By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/div[2]/div[1]/div/div/div[5]/div[7]/div");
    private By provideseatbtn=By.xpath("//div[@class='btnPassDetails']");
    private By Assert_details=By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div[1]/div");
    private By Mobile=By.xpath("//input[@name='mobileNo']");
    private By Email=By.xpath("//input[@name='email']");
    private By proceed=By.xpath("///div[contains(text(), 'PROCEED TO passenger detail as') and contains(text(), 'guest') and contains(text(), 'user')]");
    private By Name=By.xpath("//input[@name='paxName[0]']");
    private By Gender=By.xpath("//input[@name='paxGender[0]']");
    private By GenType=By.id("searchInput");
    private By Age=By.xpath("//input[@name='paxAge[0]']");
    private By checkout=By.xpath("//div[text()='Proceed to Checkout']");
    private By paxConcessionType=By.xpath("//input[@name'paxConcessionType[0]']");
    private By paxIDCardType=By.xpath("//input[@name='paxIDCardType[0]']");
    private By paxIDCardNo=By.xpath("//input[@name='paxIDCardNo[0]']");
    //-------------------------------------------------First test case--------------------------------------------------------------//
    public void enterdate(String day,String month) throws InterruptedException {
        BasePageDriver.findElement(departDate).click();
        JavascriptExecutor js = (JavascriptExecutor) BasePageDriver;
        js.executeScript("window.scrollBy(500,250)", "");
        int Month=Integer.parseInt(month)-1;
        Thread.sleep(3000);
        BasePageDriver.findElement(By.xpath("//td[@data-month='"+Month+"']/a[text()='"+day+"']")).click();
        System.out.println("the date is entered : Month : "+month+" Day : "+day);
    }
    public void click_submit() throws InterruptedException {
        Thread.sleep(2000);
        BasePageDriver.findElement(submitSearch).click();
    }
    public boolean Assert_title(String Title) throws InterruptedException {
        Thread.sleep(3000);
        String name=BasePageDriver.findElement(title).getAttribute("alt");
        System.out.println(name);
        if (name!=null){
            if (name.contains(Title)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    public void click_from(String City) throws InterruptedException {
        BasePageDriver.findElement(click_on_fromList).click();
        BasePageDriver.findElement(enter_dep_city).sendKeys(City, Keys.ENTER);
        BasePageDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        System.out.println("Selected country: " + City);
    }
    public void click_to(String City) throws InterruptedException {
        BasePageDriver.findElement(click_on_toCity).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(enter_des_city));
        BasePageDriver.findElement(enter_des_city).sendKeys(City, Keys.ENTER);
        System.out.println("Selected country: " + City);
    }
    //-------------------------------------------------------------------------------------------------------------------//
    //------------------------------------Second testcase-------------------------------------------------------------------------------//
    public void Assert_Details_tip(String from,String to,String month,String day){
        // Convert the first letter to uppercase and the rest to lowercase
        String FromCity = from.substring(0, 1).toUpperCase() +
                from.substring(1).toLowerCase();
        String ToCity = to.substring(0, 1).toUpperCase() +
                to.substring(1).toLowerCase();
        String actual=BasePageDriver.findElement(Assert_details).getText();
        String monthAbbreviation = new DateFormatSymbols().getShortMonths()[Integer.parseInt(month) - 1];
        String expected="Onward Trip: "+FromCity+" - "+ToCity+", "+day+" "+monthAbbreviation+"";
        System.out.println(expected);
        Assert.assertEquals(actual,expected);
        System.out.println("The Assertion is correct");
    }
    public void Click_select_seat(){
        BasePageDriver.findElement(select_seat).click();
    }
    public void Choose_Seat(int CardNum, String SeatRow, int SeatNum, String seatavailable, int Postion) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(select_seat));


        // Define seat rows with their availability
        SeatsRow.put("1", new Integer[]{4, 5, 12, 13, 20, 21, 28, 29, 36, 37, 44, 45});
        SeatsRow.put("2", new Integer[]{3, 6, 11, 14, 19, 22, 27, 30, 35, 38, 43, 46});
        SeatsRow.put("3", new Integer[]{47});
        SeatsRow.put("4", new Integer[]{2, 7, 10, 15, 18, 23, 26, 31, 34, 39, 42});
        SeatsRow.put("5", new Integer[]{1, 8, 9, 16, 17, 24, 25, 32, 33, 40, 41});
        WebElement click_board= BasePageDriver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[3]/div[2]/div[" + CardNum + "]/div/div/div[5]/div[7]/div"));
        WebElement seatElement = BasePageDriver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[3]/div[2]/div["+CardNum+"]/div[2]/div[2]/div[1]/div/div[" + SeatRow + "]/div[" + Postion + "]"));
        if (SeatsRow.containsKey(SeatRow)) {
            try {
                Integer[] specificSeats = SeatsRow.get(SeatRow);
                for (Integer element : specificSeats) {
                    if (element == SeatNum) {
                        // Ensure the seat is available
                        if (seatElement != null) {
                            String check = seatElement.getAttribute("style");
                            System.out.println(seatElement.getText());
                            if (!check.contains(seatavailable)) {
                                if (checkOpenBoard(CardNum)) {
                                    wait.until(ExpectedConditions.elementToBeClickable(click_board));
                                    click_board.click();
                                    // Re-locate the seat element before clicking
                                    seatElement.click();
                                } else {
                                    System.out.println("the board is closed");
                                    click_board.click();
                                    seatElement.click();
                                }
                            } else {
                                System.out.println("The seat is unavailable.");
                            }
                        }

                    } else {
                        System.out.println("The seat is not in the row.");
                    }
                }
            }catch (StaleElementReferenceException e) {
                    System.out.println("re-locating the seat element.");
                    click_board.click();
                WebElement seat = BasePageDriver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[3]/div[2]/div["+CardNum+"]/div[2]/div[2]/div[1]/div/div[" + SeatRow + "]/div[" + Postion + "]"));
                Thread.sleep(1500);
                    seat.click();
            }
            }else {
            System.out.println("The row does not exist.");
        }
    }
    public boolean checkOpenBoard(int CardNum) throws InterruptedException {
        try {
            // Attempt to find the board element
            WebElement board = BasePageDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/div[2]/div["+CardNum+"]/div[1]/div/div[5]/div[7]/div")); // Update with the correct XPath

            // Return true if the board is displayed, false otherwise
            return board.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("The board element was not found. It may not exist.");
            return false; // Return false if the board cannot be found
        }
    }
    public void select_point1(int CardNum) throws InterruptedException {

        BasePageDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/div[2]/div["+CardNum+"]/div[2]/div[2]/div[2]/div[1]/div[1]/div[3]/div")).click();
        Thread.sleep(1000);
        BasePageDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/div[2]/div["+CardNum+"]/div[2]/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]")).click();
        Thread.sleep(1000);
        BasePageDriver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[3]/div[2]/div["+CardNum+"]/div[2]/div[2]/div[2]/div[3]/div")).click();
        Thread.sleep(500);
        BasePageDriver.findElement(provideseatbtn).click();
    }
    public void checkoutseat(String mobile,String email,String name,String gender,String age) throws InterruptedException {
        BasePageDriver.findElement(Mobile).sendKeys(mobile, Keys.ENTER);
        Thread.sleep(300);
        BasePageDriver.findElement(Email).sendKeys(email,Keys.ENTER);
        Thread.sleep(300);
        BasePageDriver.findElement(provideseatbtn).click();
        Thread.sleep(10000);
        BasePageDriver.findElement(Name).sendKeys(name,Keys.ENTER);
        Thread.sleep(300);
        BasePageDriver.findElement(Gender).click();
        Thread.sleep(300);
        BasePageDriver.findElement(GenType).sendKeys(gender,Keys.ENTER);
        Thread.sleep(300);
        BasePageDriver.findElement(Age).sendKeys(age,Keys.ENTER);
        Thread.sleep(300);
        BasePageDriver.findElement(paxConcessionType).click();
        Thread.sleep(300);
        BasePageDriver.findElement(GenType).sendKeys("Gen",Keys.ENTER);
        Thread.sleep(300);
        BasePageDriver.findElement(paxIDCardType).click();
        BasePageDriver.findElement(GenType).sendKeys("PAN",Keys.ENTER);
        Thread.sleep(300);
        BasePageDriver.findElement(paxIDCardNo).sendKeys("123",Keys.ENTER);
        BasePageDriver.findElement(checkout).click();
    }

}
