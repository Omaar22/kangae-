package swe;

import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountTest {
	Account user = new Account();

	//checks if the search results are correct
	
	@DataProvider (name="searchDataset")
	public static Object[][] test_data() {
		return new Object[][] { { "maryam", "maryam" },
				{ "khadega", "1" }, { "ali", "ali" }};

	}

	@Test (dataProvider = "searchDataset")
	public void Search( String username, String password) throws FileNotFoundException {
		Account_DB Database = new Account_DB();
		Database.readfile();
		Assert.assertEquals(Search1(username, password), user.Search(username, password));

	}

	public Account Search1(String username, String password)
			throws FileNotFoundException {
		Account A = new Account();
		Account_DB aa = new Account_DB();
		A = null;
		for (int i = 0; i < Account_DB.a.size(); i++) {
			if (Account_DB.a.get(i).UserName.equals(username)
					&& Account_DB.a.get(i).Password.equals(password)) {
				A = Account_DB.a.get(i);
				break;
			}
		}
		return A;
	}

}
