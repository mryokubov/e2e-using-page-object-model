package com.academy.techcenture.ecommerce.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class HomePage {

    protected WebDriver driver;
    protected SoftAssert softAssert;

    public HomePage(WebDriver driver, SoftAssert softAssert){
        this.driver = driver;
        this.softAssert = softAssert;
        PageFactory.initElements(driver, this);
    }

    //web elements
    @FindBy(xpath = "//a[@class='login']")
    protected WebElement signInLink;

    @FindBy(linkText = "Contact us")
    protected WebElement contactUsLink;

    @FindBy(className = "logout")
    protected WebElement signOutLink;

    @FindBy(id = "search_query_top")
    protected WebElement searchInputBox;

    @FindBy(xpath = "//button[contains(@class,'button-search')]")
    protected WebElement searchBtn;




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
















