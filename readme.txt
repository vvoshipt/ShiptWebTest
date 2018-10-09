HOW TO RUN:
    Project created on Windows
    Install java jdk;
        link - https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
    Install IntelliJ
        link - https://www.jetbrains.com/idea/download/#section=windows
    Install Gradle
        link - https://gradle.org/install/

    Open the Project in IntelliJ and turn on the option for Auto Download Dependencies and wait for gradle dependencies to install.
    Right click '\src\test\java\LoginTest.java' in project window to run the test. A browser should pop up and perform the test.

    Results can be found in console logs and in Extent Reports as an HTML link 'src\test\resources\WebTestReport.html'.
    Extent reports only holds one run for now but can be switched to append instead of replacing the file with a true/false tag in 'src\test\java\TestNG\TestRunnerBase.java';

Project Details:
    -TestNg is used to drive the test as a Data Driven Test where it will Create TestCase Objects and pass those as arguments for the tests to Run.
    -Assertions are Performed through the TestCase Object using TestNg SoftAsserts and reported as a combination of TestNG's reporting and Extent Reports'.
    -Background Work for the Test including test setup and test case setup can be found in '\src\test\java\TestNG\TestRunnerBase.java'.
    -Shouldn't need to worry about binaries for Selenium because the WebDriverManager Dependency should be able to download the correct binaries on mac or windows during the test run.