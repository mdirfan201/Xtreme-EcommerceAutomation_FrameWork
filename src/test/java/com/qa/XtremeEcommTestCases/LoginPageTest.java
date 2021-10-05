package com.qa.XtremeEcommTestCases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

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
import com.qa.pages.LoginPage;
import com.qa.pages.RegisterPage;
import com.qa.util.TestUtil;


public class LoginPageTest extends TestBase{
	
	static LoginPage loginpage;
	static HomePage homepage;
	static ExtentReports extent;
	static ExtentSparkReporter spark;
	static ExtentTest test;
	String sheetName="Login";
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeSuite
	public void setupExtentReport() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now));
		extent= new ExtentReports();
		spark= new ExtentSparkReporter("C:\\Users\\mohammed\\eclipse-workspace\\Xtreme-EcommerceAutomation_FrameWork\\Extent-Report\\XtremeEcommerce-LoginPage.html");
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
		homepage.headerLoginBtn.click();
		loginpage= new LoginPage();
	}
	

	@Test(priority = 1)
	public void verifyHomePageLogoTest() {
		test=extent.createTest("TC_01 :HOME-PAGE  Verify Logo Title Test");
		boolean flag=loginpage.verifyLoginLogo();
		Assert.assertTrue(flag);
	}
	@Test(priority = 2)
	public void validateEmptyData() throws InterruptedException {
		test=extent.createTest("TC_02 :HOME-PAGE  validate Empty Data Test");
		loginpage.setEmptyRegister();
		test.info("The warning message-->" +loginpage.Warning.getText());
	}
	@Test(priority = 3)
	public void validateContinueViaGoogleLoginPage() throws InterruptedException {
		test=extent.createTest("TC_03 :HOME-PAGE  validate Continue Via Google Test");
		loginpage.validateContinueViaGoogle();
	}
	@DataProvider
	public Object[][] getData() {
		Object data[][]=TestUtil.getTestData(sheetName);
		return data;
	}
	@Test( priority = 4, dataProvider = "getData")
	public void validatesetRegisterData(String Email, String Password) {
		test=extent.createTest("TC_04 :HOME-PAGE  validate Register Data Test");
		try {
			loginpage.setRegister(Email, Password);
		} catch (InterruptedException e) {
			System.out.println("Data not found");
			e.printStackTrace();
		}
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
