package com.academy.techtenture;

import com.academy.techcenture.pages.HomePage;
import com.academy.techcenture.pages.LoginPage;
import com.academy.techcenture.pages.UserAccountPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class UserLoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        //browser configuration
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        this.driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        driver.get("http://automationpractice.com/index.php");
    }


    @Test(priority = 0)
    public void userLoginPositive() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        UserAccountPage userAccountPage = new UserAccountPage(driver);
        homePage.clickSingInLink();
        loginPage.login();
        userAccountPage.verifyAccountOptions();
        userAccountPage.navigateHome();
        homePage.signOut();

    }

    @Test(priority = 1)
    public void userLoginNegative() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickSingInLink();
        loginPage.verifyLoginErrors();

    }

    @AfterMethod
    public void cleanUp(){
        if (driver != null){
            driver.quit();
        }
    }
}
