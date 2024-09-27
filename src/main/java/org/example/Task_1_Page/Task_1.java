package org.example.Task_1_Page;

import io.qameta.allure.Step;
import org.example.ReusePages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class Task_1 extends BasePage {

    public Task_1(WebDriver mydriver) {
        super(mydriver);

    }
    SoftAssert softAssert=new SoftAssert();
    WebDriverWait wait = new WebDriverWait(BasePageDriver, Duration.ofSeconds(60));
    private By HomePage=By.id("nav-logo-sprites");
    private By SearchBar=By.id("twotabsearchtextbox");
    private By TodayDeal=By.xpath("//a[@data-csa-c-content-id=\"nav_cs_gb\"]");
    private By Cart=By.id("nav-cart");
    private By checkout=By.xpath("//input[@value='Proceed to checkout']");
    private By Item_Added_Message=By.xpath("//strong[text()='Item Added']");
    private By item_select=By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/span[1]/div[1]/div[5]/div/div/span/div/div/div[3]/div[1]/h2/a/span");
    private By item_Cart=By.xpath("/html/body/div[1]/div[1]/div[3]/div[4]/div/div[2]/div[1]/div/form/div[2]/div[3]/div[4]/div/div[2]/ul/li/span/a/span[1]/h4/span/span[2]");
    private By seemore=By.xpath("//a[text()='See more']");
    private By grocery_icon=By.xpath("//*[@id=\"DealsGridScrollAnchor\"]/div[2]/div[1]/div/span[14]/div/label");
    private By discount  =By.xpath("//*[@id=\"DealsGridScrollAnchor\"]/div[2]/div[6]/div[@role=\"radiogroup\"]/span[2]/div[@data-csa-c-element-id=\"filter-percentOff-10% off or more\"]/label");
    private By move_pages=By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div/div/div/div[2]/div/div[2]/div/div[3]/a");
    private By page_num=By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div/div/div/div[2]/div/div[1]/div/div/div[2]/span/span[1]/span[1]");
    private By Item_list=By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[1]/div/div/div/div[2]/div/div[2]/div/div[2]/div/ol/li[4]/span/div/a");
    private By Add_Cart=By.id("add-to-cart-button");
    private By itemlist_title=By.xpath("//span[@id=\"productTitle\"]");
    private By Itemlist_cart=By.xpath("/html/body/div[1]/div[1]/div[3]/div[4]/div/div[2]/div[1]/div/form/div[2]/div[3]/div[4]/div/div[2]/ul/li/span/a/span[1]/h4/span/span[2]");
    private By Dismiss=By.xpath("//input[@data-action-type=\"DISMISS\"]");
    private By verify=By.xpath("//a[@href=\"https://www.amazon.com/ap/signin?openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&openid.return_to=https%3A%2F%2Fwww.amazon.com%2Fref%3Drhf_sign_in&openid.assoc_handle=usflex&openid.pape.max_auth_age=0\"]/span[text()='Sign in']");
    //--------------------------------------Functions to Make an action-----------------------------------------------//
    @Step("Search for item or category")
    public void Search_item(String item){
        wait.until(ExpectedConditions.visibilityOfElementLocated(SearchBar));
        BasePageDriver.findElement(SearchBar).clear();
        BasePageDriver.findElement(SearchBar).sendKeys(item, Keys.ENTER);
        System.out.println("Open "+item+" throw searchbar");
    }

    @Step("Go to the cart")
    public void Go_to_Cart(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(Item_Added_Message));
        Actions actions = new Actions(BasePageDriver);
        actions.moveToElement(BasePageDriver.findElement(Cart)).perform();
        BasePageDriver.findElement(Cart).click();
    }
    @Step("Assert if we are in Cart Page or not")
    public void Assert_Cart_Title(String title){
        String actual_title=BasePageDriver.getTitle();
        softAssert.assertTrue(actual_title.contains(title),"Expected title: '" + title + "', but got: '" + actual_title + "'");
        System.out.println("Expected title: '" + title + "', and but got: '" + actual_title + "'");
        softAssert.assertAll();
    }
    @Step("Assert for adding Item")
    public void Assert_Item_Added(String item_added){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Item_Added_Message));
        Actions actions = new Actions(BasePageDriver);
        actions.moveToElement(BasePageDriver.findElement(Item_Added_Message)).perform();
        String Message =BasePageDriver.findElement(Item_Added_Message).getText();
        softAssert.assertEquals(Message,item_added,"Expected title: '" + item_added + "', but got: '" + Message + "'");
        softAssert.assertAll();
    }
    @Step("Add item with id to the cart")
    public void Add_item_to_cart(String id ){
        BasePageDriver.findElement(By.id(id)).click();
        System.out.println("Click on item with id : "+id);
    }
    @Step("Click on checkout button")
    public void Click_on_checkout(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkout));
        BasePageDriver.findElement(checkout).click();
        System.out.println("Click on checkout");
    }
    @Step("Get item index from the list")
    public String get_item_index(){

        return BasePageDriver.findElement(item_select).getText();
    }
    @Step("Assert if item in the cart matches expected")
    public boolean Assertion_item_card(String expected) {
        String actual=BasePageDriver.findElement(item_Cart).getText();
        // i make this because the second description ends with ...
        String[] expected_Words = expected.trim().split("\\s+");
        String[] actual_Words = actual.trim().split("\\s+");

        // Extract the first two words and compare
        String expectedFirstTwo = expected_Words[0] + " " + expected_Words[1];
        String actualFirstTwo = actual_Words[0] + " " + actual_Words[1];
        System.out.println("item :"+actual + " is added ");
        // Return true if the first two words match, ignoring case
        return expectedFirstTwo.equalsIgnoreCase(actualFirstTwo);
    }
    @Step("Click on today's deals")
    public void Click_on_today_deals(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(TodayDeal));
        BasePageDriver.findElement(TodayDeal).click();
    }
    @Step("Click on 'See More'")
    public void Click_On_SeeMore(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(seemore));
        wait.until(ExpectedConditions.elementToBeClickable(seemore));
        Actions actions = new Actions(BasePageDriver);
        actions.moveToElement(BasePageDriver.findElement(seemore)).perform();
        BasePageDriver.findElement(seemore).click();
    }
    @Step("Click on the grocery icon and scroll through the items")
    public void Click_on_grocery() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(grocery_icon));
        Actions actions = new Actions(BasePageDriver);
        actions.moveToElement(BasePageDriver.findElement(grocery_icon)).perform();
        BasePageDriver.findElement(grocery_icon).click();
        WebElement slider=BasePageDriver.findElement(By.xpath("//div[@data-tabulation-id=\"desktop-refinements\"]"));
        // Continue scrolling until all elements are loaded
        JavascriptExecutor js = (JavascriptExecutor) BasePageDriver;
        long lastHeight = (long) js.executeScript("return arguments[0].scrollHeight;", slider);

        while (true) {
            js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", slider);
            Thread.sleep(2000); // Adjust sleep time if needed
            long newHeight = (long) js.executeScript("return arguments[0].scrollHeight;", slider);
            if (newHeight == lastHeight) {
                break; // Exit loop when no more content is loaded
            }
            lastHeight = newHeight;
        }

    }
    @Step("Click on discount section")
    public void Click_on_discount(){
        BasePageDriver.findElement(discount).click();
    }
    @Step("Navigate to the home page")
    public void Click_HOME(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage));
        Actions actions = new Actions(BasePageDriver);
        actions.moveToElement(BasePageDriver.findElement(HomePage)).perform();
        BasePageDriver.findElement(HomePage).click();
    }
    @Step("Move through pages until page number is reached")
    public void setMove_pages(int num){
        wait.until(ExpectedConditions.visibilityOfElementLocated(move_pages));
        Actions actions = new Actions(BasePageDriver);
        actions.moveToElement(BasePageDriver.findElement(move_pages)).perform();
        int pages= Integer.parseInt(BasePageDriver.findElement(page_num).getText());
        for (int i=1;i<=pages;i++){
            if (pages!=num) {
                BasePageDriver.findElement(move_pages).click();
            }else {
                break;
            }
        }
    }
    @Step("Select an item from the list")
    public void select_item_list(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Item_list));
        Actions actions = new Actions(BasePageDriver);
        actions.moveToElement(BasePageDriver.findElement(Item_list)).perform();
        BasePageDriver.findElement(Item_list).click();
    }
    @Step("Click on 'Add to Cart'")
    public void click_on_add(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Add_Cart));
        Actions actions = new Actions(BasePageDriver);
        actions.moveToElement(BasePageDriver.findElement(Add_Cart)).perform();
        BasePageDriver.findElement(Add_Cart).click();
    }
    @Step("Get the name of the selected item from the list")
    public String get_item_listName(){
        return BasePageDriver.findElement(itemlist_title).getText();
    }
    @Step("Assert if item in the list matches expected")
    public boolean Assertion_item_list_card(String expected) {
        String actual=BasePageDriver.findElement(Itemlist_cart).getText();
        // i make this because the second description ends with ...
        String[] expected_Words = expected.trim().split("\\s+");
        String[] actual_Words = actual.trim().split("\\s+");

        // Extract the first two words and compare
        String expectedFirstTwo = expected_Words[0] + " " + expected_Words[1];
        String actualFirstTwo = actual_Words[0] + " " + actual_Words[1];
        System.out.println("item :"+actual + " is added ");
        // Return true if the first two words match, ignoring case
        return expectedFirstTwo.equalsIgnoreCase(actualFirstTwo);
    }
    @Step("Dismiss the popup or message")
    public void setDismiss(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(Dismiss));
        Actions actions = new Actions(BasePageDriver);
        actions.moveToElement(BasePageDriver.findElement(Dismiss)).perform();
        BasePageDriver.findElement(Dismiss).click();
    }
    @Step("Verify if the 'Sign In' button is displayed")
    public boolean verfiy_sign_in(){
        String Verfiy=BasePageDriver.findElement(verify).getText();
        if (!Verfiy.isEmpty()){
            if (Verfiy.contains("Sign in")){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    @Step("Verify if the page number is displayed")
    public boolean verfiy_page_number(){
        String pages= BasePageDriver.findElement(page_num).getText();
        if (pages!=null){
            return true;
        }else {
            return false;
        }
    }
}
