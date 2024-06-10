package apTests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
// import java.util.Set;
import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.concurrent.TimeUnit;
// import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.*;

public class Home {

    RemoteWebDriver driver;
    String url = "https://www.wikipedia.org/";


    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void WikiHomepage() {
        this.driver.get(this.url);
    }

    public String WikiHeader(){

        String pageTitle = driver.getTitle();
        // System.out.println("Page Title: " + pageTitle);
        return pageTitle;
    }

    public boolean verifyFooterLinks(List<String> expectedLinks) {
        try {
            WebElement footer = driver.findElement(By.tagName("footer"));
            List<WebElement> footerLinks = footer.findElements(By.tagName("a"));

            boolean allLinksValid = true;
            for (String expectedLink : expectedLinks) {
                boolean linkFound = false;
                for (WebElement link : footerLinks) {
                    if (link.getText().contains(expectedLink)) {
                        linkFound = true;
                        break;
                    }
                }
                if (!linkFound) {
                    allLinksValid = false;
                    break;
                }
            }

            return allLinksValid;
        } catch (Exception e) {
            System.out.println("Error while verifying footer links: " + e.getMessage());
            return false;
        }
    }
 
    public Boolean checkFounder(String founderName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.infobox")));

            WebElement table = driver.findElement(By.cssSelector("table.infobox"));
            List<WebElement> tableRows = table.findElements(By.tagName("tr"));

            for (WebElement row : tableRows) {
                List<WebElement> thElements = row.findElements(By.tagName("th"));

                // Ensure that the row contains a 'th' element before proceeding
                if (!thElements.isEmpty() && thElements.get(0).getText().contains("Founders")) {
                    List<WebElement> foundNamesInList = row.findElements(By.xpath(".//td/div[@class='plainlist']/ul/li"));

                    for (WebElement getNameFormList : foundNamesInList) {
                        if (getNameFormList.getText().contains(founderName)) {
                            if(getNameFormList.getText().contains("Bill Gates")){
                                getNameFormList.findElement(By.linkText(founderName)).click();
                                wait.until(ExpectedConditions.urlContains(founderName.replace(" ", "_")));
                                return true;
                            }
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error while validating Founder name in Wiki Index Box: " + e.getMessage());
            return false;            
        }
    }

    public Boolean WiKiLanguage_Eng(){

        driver.findElement(By.id("js-link-box-en")).click();

        return true;
    }


    public Boolean clickMainMenu(){

        driver.findElement(By.id("vector-main-menu-dropdown-checkbox")).click();

        // driver.findElement(By.id("n-aboutsite")).click();

        return true;
    }

    public Boolean clickMainMenuOption(String clickOption){
 
        Wait<RemoteWebDriver> wait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(30))
        .pollingEvery(Duration.ofMillis(250))
        .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#p-navigation > div.vector-menu-content > ul")));

        List<WebElement> getListofMenu = driver.findElements(By.cssSelector("#p-navigation > div.vector-menu-content > ul > li"));

        for(WebElement checkOptionInList : getListofMenu){
            WebElement getOptionName = checkOptionInList.findElement(By.cssSelector("a"));
            String getString = getOptionName.getText();
            if(getString.equals(clickOption)){
                String getURL = getOptionName.getAttribute("href");
                driver.get(getURL);
                return true;
            }
        }

        return false;
    }
}
