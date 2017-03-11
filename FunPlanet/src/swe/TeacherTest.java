package swe;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TeacherTest {
	Teacher teacher = new Teacher();

	@DataProvider(name = "test_for_addgame")
	public static Object[][] test_data() {
		return new Object[][] { { true, "MCQ", "math" },
				{ true, "Check", "science" }, { false, "true/false", "math" } };

	}

	//checks if the game is added to the list
	@Test(dataProvider = "test_for_addgame")
	public void AddGame_test(boolean result, String Type, String Categ) {
		Assert.assertEquals(result, teacher.AddGame_test(Type, Categ));

	}

}
