package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.qa.util.WebEventListener;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties prop;
	static EventFiringWebDriver e_driver;
	
	public TestBase() {
		try {
			FileInputStream ip= new FileInputStream("D:\\IRFAN---\\java program\\Xtreme-EcommerceAutomation_FrameWork\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop =new Properties();
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initialization() {
		String browserName="chrome";
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}else {

			System.out.println("browser key not available in properties file");
		}
		
		e_driver= new EventFiringWebDriver(driver);
		WebEventListener eventListner=new WebEventListener(); 
		e_driver.register(eventListner);
		driver= e_driver;
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

	public  static void getScreenshot(String testMethodNmae) throws IOException {
		TakesScreenshot ts= (TakesScreenshot) driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File("D:\\IRFAN---\\java program\\Xtreme-EcommerceAutomation_FrameWork\\ScreenShot\\"+testMethodNmae+"_"+ ".png"));

		} catch (Exception e) {
			System.out.println("Unable to take screensot");
			e.printStackTrace();
		}

	}
}
