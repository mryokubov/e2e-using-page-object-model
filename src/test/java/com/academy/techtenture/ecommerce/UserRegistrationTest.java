package com.academy.techtenture.ecommerce;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.config.Driver;
import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techcenture.ecommerce.pages.LoginPage;
import com.academy.techcenture.ecommerce.pages.UserAccountPage;
import com.academy.techcenture.ecommerce.pages.UserRegisterPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class UserRegistrationTest {

    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();
    }

    @Test(priority = 0)
    public void userRegistrationPositiveTest() throws IOException {
        HomePage homePage = new HomePage(driver,softAssert);
        LoginPage loginPage = new LoginPage(driver,softAssert);
        UserRegisterPage registerPage = new UserRegisterPage(driver,softAssert);
        UserAccountPage userAccountPage = new UserAccountPage(driver,softAssert);

        homePage.clickSingInLink();
        loginPage.enterNewEmailAddress();
        registerPage.registerUser();
        userAccountPage.verifyAccountOptions();
        userAccountPage.navigateHome();
        homePage.signOut();
        softAssert.assertAll();
    }

    @Test
    public void userRegisterInvalidEmail() throws InterruptedException {
        //todo create an account with invalid email address such as kevin.lee@gmail and kevin.leegmail.com and kevin.lee

        HomePage homePage = new HomePage(driver, softAssert);
        LoginPage loginPage = new LoginPage(driver, softAssert);

        homePage.clickSingInLink();
        loginPage.verifyInvalidEmailAddresses();

        softAssert.assertAll();
    }

    @Test
    public void verifyErrorsOnRegisterPage() throws IOException {
        HomePage homePage = new HomePage(driver, softAssert);
        LoginPage loginPage = new LoginPage(driver, softAssert);
        UserRegisterPage registerPage = new UserRegisterPage(driver, softAssert);

        homePage.clickSingInLink();
        loginPage.enterNewEmailAddress();

        registerPage.verifyErrorsOnUserRegisterPage();


        System.out.println("end of test");
        softAssert.assertAll();

    }


    @AfterMethod
    public void cleanUp(){
        if (driver != null){
            driver.quit();
        }
    }

}







