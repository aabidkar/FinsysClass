package ez.testcases.finsys;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Finsys.OperationsV1;

public class CreateCompany_03 extends OperationsV1 {
	@Test
	public void CreateCompany() throws InterruptedException, IOException {
		OperationsV1 op = new OperationsV1();
		//op.BeforeTest("Add Company", "Verify Add Company Functionality");
		op.LinkClick("//a[@title='Manage Company']");
		op.FrameSwitchByName("actionid");
		op.LinkClick("//a[1]/span[@class=\"l-btn-left l-btn-icon-left\"]");
		String company = "finsys";
		op.TextBoxSetValue("//input[@name=\"name\"]", company);
		//op.ButtonClick("//select[@id='companytype']");
		op.DropDownSelectByIndex("//select[@id='companytype']", 2);
		op.TextBoxSetValue("//input[@name=\"pan\"]", "9876543210");
		op.TextBoxSetValue("//input[@name=\"email\"]", "amitb@iprogrammer.com");
		op.TextBoxSetValue("//input[@name=\"tin\"]", "1234567890");
		op.DropDownSelectByOptionValue("//select[@id=\"countryid\"]", "IN");
		op.DropDownSelectByOptionValue("//select[@id=\"stateidlist\"]", "MAHARASHTRA");
		op.DropDownSelectByOptionValue("//select[@id=\"citylist\"]", "PUNE");
		op.LinkClick("//span[@class='l-btn-icon icon-save']");
		op.LinkClick("//span[@class='l-btn-icon pagination-load']");
		String val = op.ObjectGetAttributeValue("//tr[@id='datagrid-row-r1-2-0']/td[@field='name']", "innerText");
		String temp = val.replaceAll("\\‌​r|\\n", "");
		if (temp.equalsIgnoreCase(company)) {
			System.out.println(temp + " Company is added [PASS].");
		} else {
			System.out.println("Invalid Company  " + val + "[FAIL]");
		}
		//op.TestCaseEnd();
		//op.TestSuiteEnd();
	}

}
