package dd_util;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

import dd_core.test_core;

public class TestUtil extends test_core {
	public static String CaptureScreenshot;

	@DataProvider
	public static Object[][] GetData() {

		int rowcount = excel.getRowCount("login") - 1;
		int cellcount = excel.getCellCount("login");
		System.out.println("Row is " + rowcount + "cell is " + cellcount);
		Object[][] data = new Object[rowcount][cellcount];

		int k = 0;
		for (int i = 1; i <= rowcount; i++) {
			for (int j = 0; j < cellcount; j++) {

				data[k][j] = String.valueOf(excel.getCellData("login", i, j));

			}
			k++;
		}

		return data;
	}

	public static void CaptureScreenshot() throws IOException {

		Calendar cal = new GregorianCalendar();

		int month = cal.get(Calendar.MONTH);
		int year = (cal.get(Calendar.YEAR));
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);

		CaptureScreenshot = System.getProperty("user.dir") + "\\ScreenShot\\" + year + "_" + date + "_" + (month + 1)+ "_" + day + "_" + min + "_" + sec + ".jpeg";

		File f = (File)((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(f, new File(CaptureScreenshot));

	}
}
