package com.academy.techcenture.ecommerce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class HomePage {

    private WebDriver driver;
    private SoftAssert softAssert;

    public HomePage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }

    //web elements
    @FindBy(xpath = "//a[@class='login']")
    private WebElement signInLink;

    @FindBy(linkText = "Contact us")
    private WebElement contactUsLink;

    @FindBy(className = "logout")
    private WebElement signOutLink;

    //user actions with assertions and validations
    public void clickSingInLink(){
        softAssert.assertTrue(signInLink.isDisplayed(), "Sign in link was not displayed");
        signInLink.click();
        System.out.println("clicking sign in link");
    }

    public void clickContactUsLink(){
        softAssert.assertTrue(contactUsLink.isDisplayed(), "Contact us link is not displayed");
        contactUsLink.click();
        System.out.println("clicking contact us link");
    }

    public void signOut(){

        if (signOutLink.isDisplayed()){
            signOutLink.click();
            System.out.println("Clicking sign out");
        }
    }


}
















