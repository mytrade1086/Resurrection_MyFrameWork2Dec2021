package Cases;

import org.testng.annotations.Test;

import BaseTest.BaseTest;
import Pages.LoginPage;

public class Module1 extends BaseTest {

	LoginPage page;

	@Test
	public void Module1_Test1() throws InterruptedException {
		page = new LoginPage(driver);
		page.test();
		System.out.println("Inside Module1_Test1 ");
		test.info("Inside Module1_Test1" );
	}

	@Test
	public void Module1_Test2() throws InterruptedException {
		System.out.println("Inside Module1_Test2 ");
		page = new LoginPage(driver);
		page.test();
		System.out.println("Inside Module1_Test1 ");
		test.info("Inside Module1_Test2" );

	}
}
