package com.academy.techcenture.ecommerce.pages;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.utils.CommonUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class UserRegisterPage {

    private WebDriver driver;
    private CommonUtils commonUtils;
    private WebDriverWait wait;
    private SoftAssert softAssert;
    
    public UserRegisterPage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.commonUtils = new CommonUtils();
        this.softAssert = softAssert;
        this.wait = new WebDriverWait( driver, Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("webDriverWait")) ));
        PageFactory.initElements(driver, this);
    }


    //create web elements for user registration page
    @FindBy(id = "customer_firstname")
    private WebElement firstNameInput;


    @FindBy(id = "submitAccount")
    private WebElement registerBtn;


    //actions and verifications
    public void registerUser(){
        fillOutPersonalDetailsSection();
        fillOutAddressSection();
    }


    private void fillOutPersonalDetailsSection(){
        //Todo: create web elements on a class level for registration form
        // complete the personal info and address sections
        String randomEmail = ConfigReader.getProperty("randomEmail");
        int atSign = randomEmail.indexOf("@");
        String[] fullName = randomEmail.substring(0, atSign).split("\\.");
        String firstName = fullName[1].substring(0,1).toUpperCase() + fullName[1].substring(1);
        String lastName = fullName[0].substring(0,1).toUpperCase() + fullName[0].substring(1);
        String password = commonUtils.randomPassword();

    }

    private void fillOutAddressSection(){
        //todo fill out address seciont inputs
    }


    String[] expectedErrorMessages = {
           "You must register at least one phone number.",
            "lastname is required.",
           "firstname is required.",
           "passwd is required.",
          "address1 is required.",
            "city is required.",
           "The Zip/Postal code you've entered is invalid. It must follow this format: 00000",
           "This country requires you to choose a State."
    };


    //very simple
    public void verifyErrorsOnUserRegisterPage(){

        //scroll down so register btn is visible using 2 approaches:

        //1. Actions class in Selenium Wedbriver
//        Actions actions = new Actions(driver);
//        actions.moveToElement(registerBtn).build().perform();


        //2. Javascript executor
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", registerBtn);
        registerBtn.click();
        softAssert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).isDisplayed());

        softAssert.assertEquals(driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]/p")).getText(), "There are 8 errors");

        List<WebElement> errors = driver.findElements(By.xpath("//div[contains(@class,'alert-danger')]//li"));
        for (int i = 0; i < errors.size(); i++) {
            softAssert.assertEquals(errors.get(i).getText(), expectedErrorMessages[i], "Error msg did not match: " + expectedErrorMessages[i]);
        }

    }

}








