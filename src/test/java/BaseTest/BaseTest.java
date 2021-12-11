package BaseTest;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import Base.BasePage;

public class BaseTest {
	static Date date = new Date();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH-mm");
	static String strDate = dateFormat.format(date);
	public ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest test;
	protected BasePage base;
	protected Properties prop;
	protected WebDriver driver;
	
	
	
//	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
//
//	public static synchronized WebDriver getDriver() {
//		return tlDriver.get();
//	}

	@BeforeSuite
	public void setExtent() {
		spark = new ExtentSparkReporter("./" + "\\target\\ExtentReport.html");
		spark.loadXMLConfig("./src\\main\\java\\resources\\extent.xml");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

	@AfterSuite
	public void flushExtent() {
		if (extent != null) {
			extent.flush();
		}
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		base = new BasePage();
		prop = base.init_prop();
		driver = base.init_driver(prop);
		System.out.println("executing before method");
		test = extent.createTest(m.getName());
		test.assignCategory(m.getName().split("_")[0]);
	}

	@AfterMethod
	public void afterMEthod() {
		System.out.println("executing after method");
		driver.quit();
	}
	
	

//
//	public void onTestStart(ITestResult result) {
//		test = extent.createTest(result.getName());
//		test.log(Status.INFO, result.getName() + " has started");
//	}
//	
//	public void onFinish(ITestContext context) {
//		flushExtent();
//
//	}

}
