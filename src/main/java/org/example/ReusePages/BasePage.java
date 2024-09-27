package org.example.ReusePages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected static WebDriver BasePageDriver;


    public BasePage(WebDriver mydriver){
        BasePageDriver=mydriver;
    }
    private By Title=By.id("nav-logo-sprites");

    public Boolean assert_Title(String expected_Title) {
        String actual_Title = BasePageDriver.findElement(Title).getAttribute("aria-label");
        if (actual_Title.equals(expected_Title)) {

            System.out.println("You are in " + expected_Title);
            return true;
        } else {
            // Provide a meaningful message for the assertion error
            throw new AssertionError("Expected title: '" + expected_Title + "', but got: '" + actual_Title + "'");
        }
    }


}
