package swe;

import org.testng.Assert;
import org.testng.annotations.Test;

public class gameTest {

	game g = new game();
	
	//check that there exits game to play
	//function should have returned zero if there no games are in database

	@Test
	public void Show() {
		Boolean isFound = false;
		if (g.DB.G.size() >= 1)
			isFound = true;
		Assert.assertEquals(isFound, g.Show());
	}
}
