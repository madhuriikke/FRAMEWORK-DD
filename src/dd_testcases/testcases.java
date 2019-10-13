package dd_testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dd_core.test_core;
import dd_util.TestUtil;

public class testcases extends test_core {

	@Test(dataProvider = "GetData")
	public void dologin(String username, String password) throws IOException {

		System.out.println("Data is " + username + "-" + password);

		findelement("username").sendKeys(username);
		findelement("password").sendKeys(password);
		findelement("login").click();

	}

	@DataProvider
	public Object[][] GetData() {
		return TestUtil.GetData();
	}
}