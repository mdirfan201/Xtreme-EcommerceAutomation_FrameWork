package com.qa.XtremeEcommTestCases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.CustomeListeners.CustomeListeners;
import com.qa.base.TestBase;
import com.qa.pages.HomePage;

@Listeners(CustomeListeners.class)
public class HomePageTest extends TestBase{
	static HomePage homepage;
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeSuite
	public void setupExtentReport() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("D:\\IRFAN---\\java program\\Xtreme-EcommerceAutomation_FrameWork\\Extent-Report\\XtremeEcommerce-HomePage.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Virventure-Website Automation");
		spark.config().setReportName("Mohammed Irfan Ansari");
		extent.attachReporter(spark);

		//Setting Environment
		extent.setSystemInfo("Author","Mohammed Irfan");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("System","Windows10");
		extent.setSystemInfo("Applicatoin","Eclipse");
		extent.setSystemInfo("Tools","Selenium With Java");
	}
	@AfterSuite
	public void tearExtentReport() {
		extent.flush();
	}
	
	@BeforeMethod
	public void setup() {
		initialization();
		homepage = new HomePage();
	}
	@Test(priority = 1)
	public void validateHomePageTitleTest() {
		test=extent.createTest("TC_01 :HOME-PAGE  Validate Title Test");
		String actualTitle= homepage.validateTitle();
		System.out.println("The actual Title is--->"+ actualTitle);
		String expectedTitle="11-Xtreme Ecommerce";
		Assert.assertEquals(actualTitle, expectedTitle, "Title not matched");
	}

	@Test(priority = 2)
	public void verifyHomePageLogoTest() {
		test=extent.createTest("TC_02 :HOME-PAGE  Verify Logo Title Test");
		boolean flag= homepage.verifyLogo();
		Assert.assertTrue(flag);
	}
	@Test(priority = 3)
	public void validateHomePageApplicationLinkTest() throws InterruptedException {
		test=extent.createTest("TC_03 :HOME-PAGE  Application Link Test");
		homepage.validateApplication();
		Thread.sleep(2000);
	}
	@Test(priority = 4)
	public void validateHomePageLoginBtnTest() throws InterruptedException {
		test=extent.createTest("TC_04 :HOME-PAGE  LoginBtn Link Test");
		homepage.validateLoginBtn();
		Thread.sleep(2000);
	}
	
	@Test(priority = 5)
	public void validateHomePageRegisterBtnTest() throws InterruptedException {
		test=extent.createTest("TC_05 :HOME-PAGE  RegisterBtn Link Test");
		homepage.validateRegisterBtn();
		Thread.sleep(2000);
	}
	@Test(priority = 6)
	public void validateHomePagePricingLinkTest() {
		test=extent.createTest("TC_06 :HOME-PAGE Pricing Link Test");
		homepage.validatePricingBtn();
	}
	@Test(priority = 7)
	public void validateHomePageFAQTest() {
		test=extent.createTest("TC_07 :HOME-PAGE FAQ Link Test");
		homepage.validateFAQ();
	}
	
	@Test(priority = 8)
	public void validateHomePageFooterLinkTest() throws InterruptedException {
		test=extent.createTest("TC_08 :HOME-PAGE  FooterLink Test");
		homepage.FooterDataOneLink();
		test.info("Data-One SS--", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.FooterBuyBoxLink();
		test.info("FooterBuyBox SS--", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.FooterRepayLink();
		test.info("FooterRepay SS--", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.FooterFivestareLink();
		test.info("FooterFiveStar SS--", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.FooterOrderAILink();
		test.info("FooterOrderAll SS--", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.FooterTermOfService();
		test.info("FooterTOS--", MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		homepage.PrivacyPolicy();
	}
	
	
	
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test cases MethodName Failed ==>" + result.getName());
			test.log(Status.FAIL, "Test cases MethodName Failed and throws Exception==>" + result.getThrowable());
			test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		}else if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP, "Test cases MethodName Skiped ==>" + result.getName());
			test.log(Status.SKIP, "Test cases MethodName Skiped and throws Exception ==>" + result.getThrowable());	
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test cases MethodName Pass ==>" + result.getName());
			test.log(Status.PASS, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64ScreenShot()).build());
		}

		driver.close();

	}

	public String getBase64ScreenShot() {
		return((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}
}
