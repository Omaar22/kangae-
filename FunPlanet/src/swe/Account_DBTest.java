package swe;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Account_DBTest {

	Account_DB db = new Account_DB();

	//checks if there are duplicates in account database
	@Test
	public void readfile() throws FileNotFoundException {
		db.readfile();
		Boolean duplicates = false;
		Boolean result = false;
		Map <Account, Boolean> visted = new HashMap<>();
		for (int i = 0; i < db.a.size(); i++){
			if (visted.containsKey(db.a.get(i))){
				duplicates = true;
			}
			visted.put(db.a.get(i), true);
		}
		Assert.assertEquals(result, duplicates);
	}
}
