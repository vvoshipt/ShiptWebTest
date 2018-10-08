package TestNG;
import Case.TestCase;
import com.relevantcodes.extentreports.ExtentReports;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

public class TestRunnerBase {
    public ExtentReports reports;

    @DataProvider(name = "GenerateTestCase")
    public Object[][] generateTest(){
        String url = "https://www.shipt.com/";
        TestCase testCase = new TestCase(url);
        int total_number_of_testCases = 1;

        //Create object to store all the test cases. In this example there is only One but the list can be any size.
        Object[][] data = new Object[total_number_of_testCases][1];
        data[0][0] = testCase;
        return data;
    }

    @BeforeSuite
    public void beforeSuite(){
        reports = new ExtentReports(getExtentReportsFilePath(), true);
    }

    @BeforeClass
    public void beforeTest(){

    }

    private String getExtentReportsFilePath(){
        return System.getProperty("user.dir") + "/src/test/resources/WebTestReport.html";
    }

    @AfterSuite
    public void afterSuite(){
        reports.flush();
    }

}
