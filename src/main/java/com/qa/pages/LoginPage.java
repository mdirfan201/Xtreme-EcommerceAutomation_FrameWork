package com.qa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.JavaScriptUtil;

public class LoginPage extends TestBase{
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='item-header']")
	private WebElement LoginLogo;
	@FindBy(xpath="//input[@type='email']")
	public WebElement txtEmail;
	@FindBy(xpath="//input[@type='password']")
	public WebElement txtPassword;
	@FindBy(xpath="//div[@class='xc-button-text'][normalize-space()='LOGIN']")
	public WebElement clickLoginBtn;
	@FindBy(xpath="//a[normalize-space()='Forgot Password']")
	public WebElement clickForgotPassword;
	@FindBy(xpath="//a[normalize-space()='Contact customer support']")
	public WebElement ContactSupport;
	@FindBy(xpath="//div[@class='google-button-text']")
	public WebElement  ContinueviaGoogle;
	
	//------warning
	@FindBy(xpath="//div[@class='item-error hasError']")
	public WebElement Warning;
	
	
	
	
	public boolean verifyLoginLogo() {
		JavaScriptUtil.drawBorder(LoginLogo, driver);
		return LoginLogo.isDisplayed();
	}
	public void setEmptyRegister() throws InterruptedException{
		txtEmail.sendKeys("",Keys.TAB);
		txtPassword.sendKeys("",Keys.TAB);
		Thread.sleep(2000);
		clickLoginBtn.click();
		Thread.sleep(2000);
	}
	public void validateContinueViaGoogle() throws InterruptedException {
		ContinueviaGoogle.click();
		String parentWindowID=driver.getWindowHandle();
		System.out.println("Parent Window ID is====>" + parentWindowID);
		Set<String>windowsIDs= driver.getWindowHandles();
		System.out.println("Total number of Id of Multiple Windiows====>" + windowsIDs.size());
		List<String>windowIDsList= new ArrayList(windowsIDs);
		String ListparentID=windowIDsList.get(0);
		String ListchildID=windowIDsList.get(1);
		
		driver.switchTo().window(ListchildID);
		System.out.println("Child window title is ===>" + driver.getTitle());
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("mdirfan201@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Welcome@2021");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		Thread.sleep(3000);
	}
	public void setRegister(String email, String pwd) throws InterruptedException {
		txtEmail.sendKeys(email);
		txtPassword.sendKeys(pwd);
		Thread.sleep(2000);
		clickLoginBtn.click();
		Thread.sleep(2000);
	}
	public ForgotPasswordPage validateForgotPasswordLink() {
		clickForgotPassword.click();
		return new ForgotPasswordPage();
	}
}
