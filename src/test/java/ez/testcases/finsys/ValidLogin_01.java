package ez.testcases.finsys;

import java.io.IOException;

import com.Finsys.OperationsV1;

public class ValidLogin_01 extends OperationsV1 {

	public void ValidLogin() throws InterruptedException, IOException {
		// op.LaunchApplication("ch", "http://localhost:90/finsys/login.html"); // for
		// Office User.
		op.LaunchApplication("ch", "http://localhost/finsys/login.html"); // for Home User.
		op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfm");
		op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rd");
		op.LinkClick("//span[.='Login']");
		String val = op.ObjectGetAttributeValue("//a[.='LOGOUT']", "innerText");
		if (val.equalsIgnoreCase("LOGOUT")) {
			System.out.println("User is Log-in Successfully. [PASS]");
		} else {
			System.out.println("User Log-in is Failed. [FAIL]");
		}

	}
}