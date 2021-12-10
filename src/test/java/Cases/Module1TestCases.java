package Cases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import BaseTest.BaseTest;
import Pages.LoginPage;

public class Module1TestCases extends BaseTest {

	LoginPage page;

	@Test
	public void Module1_Test1() throws InterruptedException {
		page = new LoginPage(driver);
		page.Login();
		for (int i = 0; i < 5; i++) {
			ExtentTest node = test.createNode("node " + i);
			System.out.println("Inside Module1_Test1+" + "\"node \"+i");
			node.info("Inside Module1_Test1+" + "\"node \"+i");
		}
	}

	@Test
	public void Module1_Test2() throws InterruptedException {
		System.out.println("Inside Module1_Test2 ");
		page = new LoginPage(driver);
		page.Login();

		for (int i = 0; i < 5; i++) {
			ExtentTest node = test.createNode("node " + i);
			System.out.println("Inside Module1_Test2+" + "\"node \"+i");
			node.info("Inside Module1_Test2+" + "\"node \"+i");
		}

	}
}
