package com.academy.techtenture.ecommerce;

import com.academy.techcenture.ecommerce.config.ConfigReader;
import com.academy.techcenture.ecommerce.config.Driver;
import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techcenture.ecommerce.pages.LoginPage;
import com.academy.techcenture.ecommerce.pages.UserAccountPage;
import com.academy.techcenture.ecommerce.pages.UserRegistrationPage;
import com.academy.techcenture.ecommerce.utils.ExcelReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.Map;

public class UserRegistrationTest {

    private WebDriver driver;
    private SoftAssert softAssert;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("URL"));
        softAssert = new SoftAssert();
    }


    @Test(priority = 0, dataProvider = "newUsersData")
    public void userRegistrationPositiveTest( Map<String,String> users ) throws IOException {


        HomePage homePage = new HomePage(driver,softAssert);
        LoginPage loginPage = new LoginPage(driver,softAssert);
        UserRegistrationPage registerPage = new UserRegistrationPage(driver,softAssert);
        UserAccountPage userAccountPage = new UserAccountPage(driver,softAssert);

        homePage.clickSingInLink();
        loginPage.enterNewEmailAddress(users);
        registerPage.registerUser(users);
        userAccountPage.verifyAccountOptions();
        userAccountPage.navigateHome();
        homePage.signOut();


        softAssert.assertAll();
    }

    @Test(dataProvider = "invalidCreateAccountData")
    public void userRegisterInvalidEmail(Map<String,String> users) throws InterruptedException {

        HomePage homePage = new HomePage(driver, softAssert);
        LoginPage loginPage = new LoginPage(driver, softAssert);

        homePage.clickSingInLink();
        loginPage.verifyInvalidEmailAddresses(users);

        softAssert.assertAll();
    }

    @Test(dataProvider = "registerErrorMessages")
    public void verifyErrorsOnRegisterPage(Map<String,String> users) throws IOException {
        HomePage homePage = new HomePage(driver, softAssert);
        LoginPage loginPage = new LoginPage(driver, softAssert);
        UserRegistrationPage registerPage = new UserRegistrationPage(driver, softAssert);

        homePage.clickSingInLink();
       loginPage.enterRandomEmail();

        registerPage.verifyErrorsOnUserRegisterPage(users);

        System.out.println("end of test");
        softAssert.assertAll();
    }


    @AfterMethod
    public void cleanUp(){
        if (driver != null){
            driver.quit();
        }
    }

    @DataProvider(name = "newUsersData")
    public Object[][] getNewUsersData(){
        ExcelReader excelReader = new ExcelReader("src/main/resources/testData/ecommerce.xlsx", "newUsers");
        return excelReader.getData();
    }

    @DataProvider(name = "invalidCreateAccountData")
    public Object[][] getInvalidCreateAccountData(){
        ExcelReader excelReader = new ExcelReader("src/main/resources/testData/ecommerce.xlsx", "negativeAccountCreate");
        return excelReader.getData();
    }

    @DataProvider(name = "registerErrorMessages")
    public Object[][] getErrorMessagesOnRegister(){
        ExcelReader excelReader = new ExcelReader("src/main/resources/testData/ecommerce.xlsx", "registerErrorMessages");
        return excelReader.getData();
    }
}







