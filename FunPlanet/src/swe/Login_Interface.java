package swe;

import java.io.FileNotFoundException;

public interface Login_Interface {
	public void Check(String userName, String password)
			throws FileNotFoundException;
}
