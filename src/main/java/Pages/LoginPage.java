package Pages;

import org.openqa.selenium.WebDriver;

import Base.BasePage;
import Util.ElementUtil;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementUtil el;

	public LoginPage(WebDriver driver) {
		super();
		this.driver = driver;
		el = new ElementUtil(driver);
	}

	public void test() throws InterruptedException {
		getDriver().get("http://teams.microsoft.com");
	}

}
