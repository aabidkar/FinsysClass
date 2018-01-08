package ez.testcases.finsys;

import java.io.IOException;

import org.ejagruti.generic.TextOperations;
import org.testng.annotations.Test;

import com.Finsys.OperationsV1;

public class InvalidLogin_02 extends OperationsV1 {
	@Test
	public void InvalidLogin() throws InterruptedException, IOException {
		OperationsV1 op=new OperationsV1();
		op.LaunchApplication("ch", "http://localhost:90/finsys/login.html"); // for Office User.
		// op.LaunchApplication("ch", "http://localhost/finsys/login.html"); //for Home
		// User.
		op.TextBoxSetValue("//input[@placeholder='Username']", "dummyfm");
		op.TextBoxSetValue("//input[@placeholder='Password']", "passw0rdd");
		op.LinkClick("//span[.='Login']");
		String val = op.ObjectGetAttributeValue("//div[@id='error']", "innerText");
		if (val.equalsIgnoreCase("Please Enter Valid Username or Password!!!")) {
			System.out.println("User is log-in With Invalid Username & Invalid Password. [PASS]");
			TextOperations.AppendTextFile(LogFilePath,
					"User is log-in With Invalid Username & Invalid Password. [PASS]");
		} else {
			System.out.println("User is able to Log-in successfully. [FAIL]");
			TextOperations.AppendTextFile(LogFilePath, "User is able to Log-in successfully. [FAIL]");
		}
	}

}
