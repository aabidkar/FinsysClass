package ez.testcases.finsys;

import java.io.IOException;
import org.testng.annotations.Test;
import com.Finsys.OperationsV1;

public class BaseFeatures extends OperationsV1  {
	@Test
	public void BeforeFeat() throws InterruptedException, IOException {
		OperationsV1 op = new OperationsV1();
		op = new OperationsV1(path + "\\result", true); // call to report
		// op = new OperationsV1(true, path+"\\log"); // call to log

	}
}
