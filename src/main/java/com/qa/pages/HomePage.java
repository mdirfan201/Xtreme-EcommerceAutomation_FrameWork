package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.JavaScriptUtil;

public class HomePage extends TestBase{
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//img[@src='assets/logo.png']")
	public WebElement homepagelogo;
	
	//--Application Link
	@FindBy(xpath="//div[normalize-space()='Applications']")
	public WebElement application;
	
	@FindBy(xpath="//div[@class='xc-header-menu-dropdown-item'][normalize-space()='Data One']")
	public WebElement DataOne;
	@FindBy(xpath="//div[normalize-space()='Buy Box']")
	public WebElement BuyBox;
	@FindBy(xpath="//div[@class='xc-header-menu-dropdown-item'][normalize-space()='Repay++']")
	public WebElement Repay;
	@FindBy(xpath="//div[normalize-space()='Fivestar']")
	public WebElement Fivestar;
	@FindBy(xpath="//div[@class='xc-header-menu-dropdown-item'][normalize-space()='Order AI']")
	public WebElement OrderAI;
	//------------------------------------------------
	@FindBy(xpath="//div[@class='menu-item-action'][normalize-space()='Pricing']")
	public WebElement Pricing;
	@FindBy(xpath="//div[@class='menu-item-action'][normalize-space()='FAQs']")
	public WebElement FAQ;
	@FindBy(xpath="//div[@class='menu-item-action login']")
	public WebElement headerLoginBtn;
	@FindBy(xpath="//button[@class='xc-button']//div[@class='xc-button-text'][normalize-space()='REGISTER']")
	public WebElement headerRegisterBtn;
	
	//------------------FoolterLinks
	@FindBy(xpath="//div[normalize-space()='Data One - Sales Dashboard']")
	public WebElement FooterDataOne;
	@FindBy(xpath="//div[normalize-space()='Buy Box - Repricer Application']")
	public WebElement FooterBuyBox;
	@FindBy(xpath="//div[normalize-space()='Repay++ - Reimbursement Application']")
	public WebElement FooterRepay;
	@FindBy(xpath="//div[normalize-space()='Fivestar - Feedback Application']")
	public WebElement FooterFivestar;
	@FindBy(xpath="//div[normalize-space()='Order AI - Order Management Application']")
	public WebElement FooterOrderAI;
	@FindBy(xpath="//div[@class='xc-footer-menu-item active'][text()='Home']")
	public WebElement FooterHomeLink;
	@FindBy(xpath="//div[normalize-space()='Terms of Service']")
	public WebElement TermofService;
	@FindBy(xpath="//div[normalize-space()='Privacy Policy']")
	public WebElement PrivacyPolicy;
	@FindBy(xpath="//img[@src='assets/images/application/DataOne.png']")
	public WebElement DataOneLogo;
	@FindBy(xpath="//img[@src='assets/images/application/BuyBox.png']")
	public WebElement BuyOneLogo;
	@FindBy(xpath="//img[@src='assets/images/application/Repay.png']")
	public WebElement RepayLogo;
	@FindBy(xpath="//img[@src='assets/images/application/FiveStar.png']")
	public WebElement FiveStarLogo;
	@FindBy(xpath="//img[@src='assets/images/application/OrderAI.png']")
	public WebElement OrderAILogo;
	
	
	
	
	
	public  String validateTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyLogo() {
		JavaScriptUtil.drawBorder(homepagelogo, driver);
		return homepagelogo.isDisplayed();
	}
	public void validateApplication() throws InterruptedException {
		Actions action= new Actions(driver);
		action.moveToElement(application).build().perform();
		DataOne.click();
		Thread.sleep(2000);
		action.moveToElement(application).build().perform();
		BuyBox.click();
		Thread.sleep(2000);
		action.moveToElement(application).build().perform();
		Repay.click();
		Thread.sleep(2000);
		action.moveToElement(application).build().perform();
		Fivestar.click();
		Thread.sleep(2000);
		action.moveToElement(application).build().perform();
		OrderAI.click();
		
	}
	
	public LoginPage validateLoginBtn() {
		headerLoginBtn.click();
		return new LoginPage();
	}
	public RegisterPage validateRegisterBtn() {
		headerRegisterBtn.click();
		return new  RegisterPage();
	}
	
	public void validatePricingBtn() {
		Pricing.click();
	}
	public void validateFAQ() {
		FAQ.click();
	}
	
	public void scrolltoBottom() {
		WebElement element= driver.findElement(By.xpath("//div[@class='xc-footer-section-header'][normalize-space()='Features']"));
		JavascriptExecutor js= (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	public void FooterDataOneLink() throws InterruptedException {
		scrolltoBottom();
		FooterDataOne.click();
		JavaScriptUtil.drawBorder(DataOneLogo, driver);
		Thread.sleep(2000);
	}
	public void FooterBuyBoxLink() throws InterruptedException {
		scrolltoBottom();
		FooterBuyBox.click();
		JavaScriptUtil.drawBorder(BuyOneLogo, driver);
		Thread.sleep(2000);
	}
	public void FooterRepayLink() throws InterruptedException {
		scrolltoBottom();
		FooterRepay.click();
		JavaScriptUtil.drawBorder(RepayLogo, driver);
		Thread.sleep(2000);
	}
	public void FooterFivestareLink() throws InterruptedException {
		scrolltoBottom();
		FooterFivestar.click();
		JavaScriptUtil.drawBorder(FiveStarLogo, driver);
		Thread.sleep(2000);
	}
	public void FooterOrderAILink() throws InterruptedException {
		scrolltoBottom();
		FooterOrderAI.click();
		JavaScriptUtil.drawBorder(OrderAILogo, driver);
		Thread.sleep(2000);
	}
	
	public void FooterHomeBtn() throws InterruptedException {
		scrolltoBottom();
		FooterHomeLink.click();
		Thread.sleep(2000);
	}
	public void FooterTermOfService() throws InterruptedException {
		scrolltoBottom();
		TermofService.click();
		WebElement element= driver.findElement(By.xpath("//h1[normalize-space()='Terms of Use']"));
		JavaScriptUtil.drawBorder(element, driver);
		Thread.sleep(2000);
	}
	public void PrivacyPolicy() throws InterruptedException {
		scrolltoBottom();
		PrivacyPolicy.click();
		WebElement element= driver.findElement(By.xpath("//h1[normalize-space()='Privacy Policy']"));
		JavaScriptUtil.drawBorder(element, driver);
		Thread.sleep(2000);
	}
}
