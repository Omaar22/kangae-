package swe;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login implements Login_Interface {
	String UserName;
	String Password;
	Scanner x = new Scanner(System.in);

	public void Check(String userName, String password)
			throws FileNotFoundException {
		Account A = new Account(userName, password);
		Account B = A.Search(userName, password);
		if (B.Role.equals("Teacher")) {
			Teacher T1 = new Teacher();
			T1.setName(B.getName());
			T1.setEmail(B.getEmail());
			T1.setUserName(B.getUserName());
			T1.setPassword(B.getPassword());
			T1.setRole(B.getRole());
			T1.setStudyCateg(B.getStudyCateg());
			T1.Show();
		} else if (B.Role.equals("Student")) {
			Student S1 = new Student();
			S1.setName(B.getName());
			S1.setEmail(B.getEmail());
			S1.setUserName(B.getUserName());
			S1.setPassword(B.getPassword());
			S1.setRole(B.getRole());
			S1.setStudyCateg(B.getStudyCateg());
			S1.Show();
		} else {
			System.out.println("Account not FOUND!!!!!!");
		}

	}

	public boolean Check_test(String userName, String password)
			throws FileNotFoundException {
		Account A = new Account(userName, password);
		Account B = A.Search(userName, password);
		if (B.Role.equals("Teacher")) {
			Teacher T1 = new Teacher();
			T1.setName(B.getName());
			T1.setEmail(B.getEmail());
			T1.setUserName(B.getUserName());
			T1.setPassword(B.getPassword());
			T1.setRole(B.getRole());
			T1.setStudyCateg(B.getStudyCateg());
			// T1.Show();
			return true;
		} else if (B.Role.equals("Student")) {
			Student S1 = new Student();
			S1.setName(B.getName());
			S1.setEmail(B.getEmail());
			S1.setUserName(B.getUserName());
			S1.setPassword(B.getPassword());
			S1.setRole(B.getRole());
			S1.setStudyCateg(B.getStudyCateg());
			// S1.Show();
			return true;
		} else {
			System.out.println("Account not FOUND!!!!!!");
			return false;

		}

	}

	public void Show() throws FileNotFoundException {

		System.out.print("UserName ->");
		String a = x.next();
		// System.out.println(a);
		System.out.print("Password ->");
		String b = x.next();
		// System.out.println(b);
		Check(a, b);
	}
}
