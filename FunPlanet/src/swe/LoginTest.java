package swe;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
	Login user = new Login();

	@DataProvider(name = "test_for_login")
	public static Object[][] test_data() {
		return new Object[][] { { true, "khadega", "1" },
				{ true, "omar", "2" }, { false, "menna", "3" } };

	}

	//checks that the logged in user has registered before
	@Test(dataProvider = "test_for_login")
	public void Check_test(boolean result, String userName, String password)
			throws FileNotFoundException {
		Assert.assertEquals(result, user.Check_test(userName, password));

	}
}