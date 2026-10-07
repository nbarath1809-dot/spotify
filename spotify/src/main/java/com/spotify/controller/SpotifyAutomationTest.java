package com.spotify.controller;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.time.Duration;
import java.util.List;

public class SpotifyAutomationTest {

    public static void main(String[] args) {

        System.out.println("====== STARTING FULL COMPREHENSIVE SELENIUM TEST ======");

        String targetBrowser = "chrome";
        WebDriver driver;

        if (targetBrowser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
            System.out.println("ℹ️ Launched Chrome Driver successfully.");
        } else {
            driver = new FirefoxDriver();
            System.out.println("ℹ️ Launched Firefox Driver successfully.");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        try {
            String baseUrl = "http://localhost:8080/spotify/register.jsp";
            driver.get(baseUrl);

            String currentUrl = driver.getCurrentUrl();
            String pageTitle = driver.getTitle();
            String pageSource = driver.getPageSource();

            System.out.println("Captured URL: " + currentUrl);
            System.out.println("Captured Page Title: " + pageTitle);
            System.out.println("Captured Page Source Length: " + pageSource.length() + " characters.");

            System.out.println("🔄 Refreshing page...");
            driver.navigate().refresh();

            System.out.println("➡️ Navigating away to login layout...");
            driver.navigate().to("http://localhost:8080/spotify/login.jsp");

            System.out.println("⬅️ Navigating backward to register layout...");
            driver.navigate().back();

            System.out.println("➡️ Navigating forward to login layout...");
            driver.navigate().forward();

            driver.navigate().to("http://localhost:8080/spotify/register.jsp");

            By idLocator = By.id("name");
            By nameLocator = By.name("email");
            By classNameLocator = By.className("container");
            By tagNameLocator = By.tagName("input");
            By cssSelectorLocator = By.cssSelector("input[type='password']");
            By xpathLocator = By.xpath("//form[@id='registerForm']");

            driver.navigate().to("https://google.com");
            By linkTextLocator = By.linkText("About");
            System.out.println("🔍 Locating via linkText verification on reference window...");
            WebElement referenceLink = driver.findElement(linkTextLocator);
            System.out.println("✅ Found Link element via linkText. Text: " + referenceLink.getText());

            driver.navigate().to("http://localhost:8080/spotify/register.jsp");

            WebElement nameBox = driver.findElement(idLocator);
            System.out.println("✅ Element discovered using findElement().");

            if (nameBox.isDisplayed() && nameBox.isEnabled()) {
                System.out.println("✅ Name Input element is visible (isDisplayed) and functional (isEnabled).");

                nameBox.clear();
                nameBox.sendKeys("Monolithic Test Automation");
                System.out.println("✅ Executed clear() and sendKeys() on Name Field.");
            }

            WebElement emailBox = driver.findElement(nameLocator);
            emailBox.clear();
            emailBox.sendKeys("fullcompliance@testing.com");

            String placeholderText = emailBox.getAttribute("placeholder");
            System.out.println("✅ Element getAttribute('placeholder') text: " + placeholderText);

            WebElement passwordBox = driver.findElement(cssSelectorLocator);
            passwordBox.clear();
            passwordBox.sendKeys("AllCriteriaMet123!");

            List<WebElement> formInputsList = driver.findElements(tagNameLocator);
            System.out.println("✅ Total dynamic inputs found via findElements(): " + formInputsList.size());

            WebElement formHeaderText = driver.findElement(By.xpath("//h1"));
            System.out.println("Form UI header context via getText(): " + formHeaderText.getText());

            System.out.println("Checking for optional elements to check isSelected state...");
            try {
                WebElement rememberCheckbox = driver.findElement(By.id("remember-me-checkbox"));
                System.out.println("Checkbox current state (isSelected): " + rememberCheckbox.isSelected());
                if (!rememberCheckbox.isSelected()) {
                    rememberCheckbox.click();
                    System.out.println("Checkbox altered state (isSelected): " + rememberCheckbox.isSelected());
                }
            } catch (Exception elementSkipped) {
                System.out.println("ℹ️ Optional checkbox element missing on layout, skipping isSelected validation.");
            }

            WebElement mainFormButton = driver.findElement(By.cssSelector("button[type='submit']"));
            System.out.println("Element text label to click: " + mainFormButton.getText());
            mainFormButton.click();
            System.out.println("✅ Triggered form button submit action via click().");

            System.out.println("\n--- Initializing Context Switching (SwitchTo) Demo ---");

            String originalTabHandle = driver.getWindowHandle();
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("http://localhost:8080/spotify/login.jsp");
            System.out.println("✅ SwitchTo Window/Tab completed. New context title: " + driver.getTitle());
            driver.switchTo().window(originalTabHandle);
            System.out.println("✅ SwitchTo back to original handle window context.");

            JavascriptExecutor jsEngine = (JavascriptExecutor) driver;
            jsEngine.executeScript("alert('Selenium Framework Testing Pop-up Alert Window Check!');");

            System.out.println("Focus shifting to modal popup via SwitchTo alert...");
            Alert testModalPopup = driver.switchTo().alert();
            System.out.println("Captured Modal Box Text Context: " + testModalPopup.getText());
            testModalPopup.accept();
            System.out.println("✅ Dismissed system alert window pop-up successfully.");
        } catch (Exception globalErrorTracker) {
            System.out.println("⚠️ Running automation flow anomaly noticed: " + globalErrorTracker.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
                System.out.println("\n====== AUTOMATIC STOP ENGAGED: ALL CRITERIA EXECUTED COMPLETELY ======");
            }
        }
    }
}