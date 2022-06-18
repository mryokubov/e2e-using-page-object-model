package com.academy.techtenture.ecommerce.e2e;

import com.academy.techcenture.ecommerce.pages.HomePage;
import com.academy.techtenture.ecommerce.base.BaseTest;
import org.testng.annotations.Test;

public class SocialNetworksTest extends BaseTest {



    @Test
    public void verifyAutomationPracticeSocialNetworksTest() throws InterruptedException {

        HomePage homePage = new HomePage(driver, softAssert);
        homePage.verifySocialNetworks();
        softAssert.assertAll();

    }





}
