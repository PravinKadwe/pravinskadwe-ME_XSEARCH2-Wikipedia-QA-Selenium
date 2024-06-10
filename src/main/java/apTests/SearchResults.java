package apTests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


public class SearchResults {
    RemoteWebDriver driver;

    public SearchResults(RemoteWebDriver driver) {
        this.driver = driver;
    }


    public Boolean searchContents(String SearchText){

        WebElement SearchBox = driver.findElement(By.id("searchInput"));

        SearchBox.sendKeys(SearchText);

        return true;
    }

    public Boolean getSearchResults(String SearchTextResult){

        try {
            WebElement searchSelectList = driver.findElement(By.id("typeahead-suggestions"));

            List<WebElement> divDropDown = searchSelectList.findElements(By.cssSelector(".suggestions-dropdown .suggestion-link"));
    
            for (WebElement optionList : divDropDown) {
                WebElement suggestionText = optionList.findElement(By.cssSelector(".suggestion-text .suggestion-title"));
                String suggestionTitle = suggestionText.getText();
    
                if (suggestionTitle.contains(SearchTextResult)) {
                    optionList.click();
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error while validating Search Text Result" + e.getMessage());
            return false;
        }
    }
    
}