package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Base.BasePage;
import Util.ElementUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil el;

	//For Passin Driver Reference
	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
		el = new ElementUtil(driver);
	}
		
	private By txtEmail=By.xpath("//input[@type='email']");
	private By btn_Next=By.xpath("//input[@value='Next']");

	public void Login()  {
		el.waitForElementToBeVisible2(txtEmail, 30);
		el.doSendKeys(txtEmail, "sumit raut");
		el.doClick(btn_Next);
	}
}
