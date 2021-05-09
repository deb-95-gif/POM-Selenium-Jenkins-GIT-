package com.crm.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;


public class ContactsPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	
	public ContactsPageTest() {
		super();
		}

	@BeforeMethod
	public void setUp() {
		intialization();
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));// already intialized by super keyword
		testUtil.switchToFrame(); 
		contactsPage = homePage.clickonContactsLink();
	}
	
	@Test(priority = 1)
	public void verifyContactsPageLabel() {
		Assert.assertTrue(contactsPage.verifyContactsLabel() , "Contacts label is missing on the Page");
	}
	
	@Test(priority = 2)
	public void selectContactsTest() {
		contactsPage.selectContactsByName("Elaine Elaine");
	}
	
	@Test(priority = 3)
		public void selectMultipleContactsTest() {
			contactsPage.selectContactsByName("Elaine Elaine");
			contactsPage.selectContactsByName("aaa cc");
		
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
//	@DataProvider(name = "getCRMTestData")
//	public String[] readJson()throws IOException , ParseException{
//		JSONParser jsonParser = new JSONParser();
//		File reader = new File("C:\\Users\\Debanjan\\POM\\FreeCRMTest\\src\\test\\resources\\JSONData\\testdata.json");
//		Object obj = jsonParser.parse(reader);
//		JSONObject usercontactJsonobj =   (JSONObject) obj;
//		JSONArray usercontactArray = (JSONArray)usercontactJsonobj.get("getCRMTestData");
//		
//		String arr[] = new String[usercontactArray.size()];
//		
//		for(int i=0; i<usercontactArray.size();i++) {
//			JSONObject users = (JSONObject) usercontactArray.get(i);
//			String Title = (String) users.get("title");
//			String FirstName = (String) users.get("firstName");
//			String LastName = (String) users.get("lastName");
//			String Company = (String) users.get("company");
//			arr[i] = Title + "," + FirstName + "," + LastName + "," + Company ;
//		}
//		return arr;
//	}

	@Test(priority = 4 , dataProvider ="getCRMTestData")
	public void validateCreateNewContact(String title , String firstName , String lastName , String company) {
		homePage.clickOnNewContactLink();
		contactsPage.createNewContact(title, firstName, lastName , company);
	
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	
	}
	
}
