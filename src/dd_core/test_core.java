package dd_core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import dd_util.ReadData;
import dd_util.TestUtil;

public class test_core {

	public static WebDriver driver = null;
	public static Properties config = null;
	public static Properties object = null;
	public static FileInputStream fis;
	public static ReadData excel = null;

	@BeforeSuite
	public void init() throws IOException {
		System.out.println("Test Suite");
		if (driver == null) {

			config = new Properties();
			object = new Properties();

			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\dd_properties\\config.properties");
			config.load(fis);

			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\dd_properties\\object.properties");
			object.load(fis);

			excel = new ReadData(System.getProperty("user.dir") + "\\src\\dd_properties\\login.xlsx");

			if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
			} else if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				driver = new FirefoxDriver();
			}

			driver.get(config.getProperty("url"));
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		}

	}

	@AfterSuite
	public void stop() {
		System.out.println("stoping server");
	}

	public static WebElement findelement(String key) throws IOException {
		try {
			return driver.findElement(By.xpath(object.getProperty(key)));
		} catch (Throwable t) {
			TestUtil.CaptureScreenshot();
			return null;

		}

	}
}
