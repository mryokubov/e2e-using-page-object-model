package com.academy.techtenture.ecommerce.e2e;

import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techcenture.ecommerce.utils.ExcelReader;
import com.academy.techtenture.ecommerce.base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

//high level name for the functionality that you are testing
public class ProductVerificationTest extends BaseTest {


    @Test(dataProvider = "dresses")
    public void verifyProductTabsTest(Map<String,String> data) throws InterruptedException {

        HomePage homePage = new HomePage(driver, softAssert);

        homePage.verifyProductTabs(data);

        softAssert.assertAll();
    }


    @DataProvider (name = "dresses")
    public Object[][] getDresses(){
        return new ExcelReader("src/main/resources/testData/ecommerce.xlsx","dresses").getData();
    }




}
