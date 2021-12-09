package Cases;

import org.testng.annotations.Test;

import BaseTest.BaseTest;
import Pages.LoginPage;

public class Module2 extends BaseTest {
	LoginPage page;

	@Test
	public void Module2_Test1() throws InterruptedException {
		page = new LoginPage(driver);
		page.test();
		System.out.println("Inside Module2_Test1 ");
		test.info("Inside Module2_Test1" );
	}

	@Test
	public void Module2_Test2() throws InterruptedException {
		System.out.println("Inside Module2_Test2 ");
		page = new LoginPage(driver);
		page.test();
		
		test.info("Inside Module2_Test2" );
	}
}
