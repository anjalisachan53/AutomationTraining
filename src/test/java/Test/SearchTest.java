package Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import PageObject.BaseClass;
import PageObject.SearchPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.captureScreenshot;



public class SearchTest extends BaseClass {

	SearchPage searchPage;
	private static final Logger logger = LogManager.getLogger(SearchTest.class);
	ExcelUtils excelUtils;
	private ExtentTest extentTest;

	@BeforeClass

	public void setup() {
		System.setProperty("log4j.configurationFile", "log4j2.properties");
	
		ExtentReportManager.getInstance(); // Ensure Extent Reports are initialized
	}

	@Test(priority = 19, enabled = true)

	public void searchPageTest() throws InterruptedException {
		ExtentReportManager.createTest("searchPageTest - " + "headphones");
		extentTest = ExtentReportManager.getTest();
		searchPage = new SearchPage(driver);
		logger.info("User launches Chrome browser");
		extentTest.info("User launches Chrome browser");
		logger.info("User enters the product to be searched and hits enter: " + "headphones");
		extentTest.info("User searches for " + "headphones");
		searchPage.searchProduct("headphones");
		Thread.sleep(2000);
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("headphones"), "URL does not contain the search query: " + "headphones");
		logger.info("Skipping test as execution required is set to no for page: " + "searchpage");
		System.out.println("Skipping test as execution required is set to no for page: " + "searchpage"); 
		extentTest.skip("Skipping test as execution required is set to no for page: " + "searchpage");


		// Capture screenshot after each test iteration

		String screenshotPath = captureScreenshot.captureScreenshot(driver, "searchPageTest_" + "headphones");
		extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after searching for: " + "headphones");
		extentTest.pass("Verified search for " + "headphones" + " applied successfully");
		driver.close();
		driver.quit();

	}

	@AfterClass

	public void tearDown() {
		ExtentReportManager.flush();

	}

}

