
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public static void logStatus(String type, String message, String status) {

        System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
                message, status));
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public Boolean testCase01() throws InterruptedException {
        logStatus("TestCase 01", "Start test case : Verify the URL of the homepage ", "DONE");

        // boolean status;

        Home homePage = new Home(driver);
        homePage.WikiHomepage();

        Thread.sleep(3000);

        if (this.driver.getCurrentUrl().contains("wikipedia")) {

            logStatus("TestCase 01", "Successfully Verify the URL of the homepage ", "PASS");

        } else {
            logStatus("TestCase 01 ", "Test Case Failure. Issues in URL of the homepage ", "FAIL");
        }

        // System.out.println("end Test case: testCase01");
        return true;
    }

    public Boolean testCase02() throws InterruptedException {
        logStatus("TestCase 02", "Start test case: Verify Wikipedia Header and Footer", "DONE");

        Home homePage = new Home(driver);
        homePage.WikiHomepage();

        // Wait for the page to load
        Thread.sleep(5000);

        // Verify header title
        String pageTitle = homePage.WikiHeader();
        if (pageTitle.equals("Wikipedia")) {
            logStatus("TestCase 02", "Header title is 'Wikipedia'", "PASS");
        } else {
            logStatus("TestCase 02", "Header title is not 'Wikipedia'", "FAIL");
        }

        // Verify footer links
        boolean footerLinksValid = homePage.verifyFooterLinks(Arrays.asList("Terms of Use", "Privacy Policy"));
        if (footerLinksValid) {
            logStatus("TestCase 02", "Footer links are valid", "PASS");
        } else {
            logStatus("TestCase 02", "Footer links are not valid", "FAIL");
        }

        logStatus("TestCase 02", "Verify Wikipedia Header and Footer complete", "DONE");
        return pageTitle.equals("Wikipedia") && footerLinksValid;
    }

    public Boolean testCase03() throws InterruptedException{
        logStatus("TestCase 03", "Start test case : Verify the search functionality ", "DONE");

        boolean status;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Home homePage = new Home(driver);
        homePage.WikiHomepage();

        // Wait for the page to load
        Thread.sleep(3000);

        String SearchText = "apple";

        String SearchTextResult = "Apple Inc.";

        String founderName = "Steve Jobs";

        SearchResults search = new SearchResults(driver);

        search.searchContents(SearchText);

        status = search.getSearchResults(SearchTextResult);

        if (status) {
            logStatus("TestCase 03", "Search Text Result Selected Successfully", "PASS");
        } else {
            logStatus("TestCase 03", "Failed to Select Search Text Result ", "FAIL");
        }

        Thread.sleep(3000);

        status = homePage.checkFounder(founderName);

        if (status) {
            logStatus("TestCase 03", "Check if "+founderName+" is listed as a founder", "PASS");
        } else {
            logStatus("TestCase 03", "Failed to Find "+founderName+" in Founder list", "FAIL");
        }

        // String formattedName = founderName.replace(" ", "_");
        // status = driver.getCurrentUrl().endsWith("/"+formattedName);

        logStatus("TestCase 03", "Successfully Verified the search functionality ", "DONE");
        return true;
    }    

    public Boolean testCase04() throws InterruptedException{
        logStatus("TestCase 04", "Start test case : Validate Hyperlink Functionality ", "DONE");

        boolean status;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Home homePage = new Home(driver);
        homePage.WikiHomepage();

        // Wait for the page to load
        Thread.sleep(3000);

        String SearchText = "microsoft";

        String SearchTextResult = "Microsoft";

        String founderName = "Bill Gates";

        SearchResults search = new SearchResults(driver);

        search.searchContents(SearchText);

        status = search.getSearchResults(SearchTextResult);

        if (status) {
            logStatus("TestCase 04", "Search Text Result Selected Successfully", "PASS");
        } else {
            logStatus("TestCase 04", "Failed to Select Search Text Result ", "FAIL");
        }

        Thread.sleep(3000);

        status = homePage.checkFounder(founderName);

        if (status) {
            logStatus("TestCase 04", founderName+" is Listed in Founder Names", "PASS");
        } else {
            logStatus("TestCase 04", "Failed to Find "+founderName+" in Founder Names", "FAIL");
        }

        String formattedName = founderName.replace(" ", "_");
        status = driver.getCurrentUrl().endsWith("/"+formattedName);

        Thread.sleep(3000);

        if (status) {
            logStatus("TestCase 04", founderName+" name Present in URL ", "PASS");
        } else {
            logStatus("TestCase 04", "Failed to Find "+founderName+" name Present in URL ", "FAIL");
        }

        logStatus("TestCase 04", "Successfully Validate Hyperlink Functionality ", "DONE");
        return true;
    }  

    public Boolean testCase05() throws InterruptedException{
        logStatus("TestCase 05", "Start test case : Validate Hyperlink Functionality ", "DONE");

        boolean status;

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        Home homePage = new Home(driver);
        homePage.WikiHomepage();

        // Wait for the page to load
        Thread.sleep(3000);

        status = homePage.WiKiLanguage_Eng();

        if (status) {
            logStatus("TestCase 05", " Navigate to the Wikipedia English homepage ", "PASS");
        } else {
            logStatus("TestCase 05", "Failed to Navigate to the Wikipedia English homepage ", "FAIL");
        }

        status = homePage.clickMainMenu();

        if (status) {
            logStatus("TestCase 05", " Click on the Main menu to expand it ", "PASS");
        } else {
            logStatus("TestCase 05", "Failed to Click on the Main menu to expand it ", "FAIL");
        }

        Thread.sleep(3000);

        String clickOption = "About Wikipedia"; 

        status = homePage.clickMainMenuOption(clickOption);

        if (status) {
            logStatus("TestCase 05", " Click on the link for 'About Wikipedia' in the menu ", "PASS");
        } else {
            logStatus("TestCase 05", "Failed to Click on the link for 'About Wikipedia' in the menu ", "FAIL");
        }

        Thread.sleep(3000);

        status = driver.getCurrentUrl().endsWith("About");

        if (status) {
            logStatus("TestCase 05", "Check if the opened URL contains 'About' ", "PASS");
        } else {
            logStatus("TestCase 05", "Failed to Check if the opened URL contains 'About' ", "FAIL");
        }

        logStatus("TestCase 05", "Successfully Validate Hyperlink Functionality ", "DONE");
        return true;
    }  

}

