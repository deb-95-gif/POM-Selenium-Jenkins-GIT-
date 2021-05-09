package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	public HomePageTest() {
		super(); // property will be intialised
	}
	
	// Test cases should be separated --independent of each other
	// before each test case launch the browser and login
	// after each tc - close the browser
	
	@BeforeMethod
	public void setUp() {
		intialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));// already intialized by super keyword
	}
	
	@Test(priority = 1)
	public void verifythehomePageTitleTest() {
		String homePagetitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePagetitle, "CRMPRO" ,"Home Page Title not matched");
	}
	
	@Test(priority = 2)
	public void verifyUserNameTest() {
		testUtil.switchToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority = 3)
	public void verifyContactsLink() {
		testUtil.switchToFrame();
		contactsPage = homePage.clickonContactsLink();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	
	}
	
	
	// for even 100 tc's browser will be launched 
	// execute the tc's and exit the browser
	// browser will be exhausted if we are not closing the browser

}


