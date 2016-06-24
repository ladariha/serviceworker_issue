/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.seleniumbugmvn;

import com.google.common.base.Predicate;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;

/**
 *
 * @author vriha
 */
public class Main {

    public static WebDriver browser;

    public static void main(String[] args) {

        DesiredCapabilities defaults = DesiredCapabilities.chrome();

        LoggingPreferences prefs = new LoggingPreferences();
        prefs.enable(LogType.BROWSER, Level.ALL);
        prefs.enable(LogType.CLIENT, Level.ALL);
        prefs.enable(LogType.DRIVER, Level.ALL);
        defaults.setCapability(CapabilityType.LOGGING_PREFS, prefs);
        browser = new ChromeDriver(defaults);

        browser.get("http://localhost:8776/app/index.html?tests=true");
        waitFor(7000);
        int i = 0;
        boolean errFound = false;
        while (i < 600 && !errFound) {
            waitFor(80000);
            String err = (String) ((JavascriptExecutor) browser).executeScript("return window.localStorage.getItem('httpError')");
            if (err != null) {
                System.err.println(err);
                errFound = true;
            }
            browser.navigate().refresh();
            i++;
        }

//        new Actions(browser).dragAndDrop(browser.findElement(By.xpath("//*[@id='source']")), browser.findElement(By.xpath("//*[@id='target']")));
        browser.quit();
        if (errFound) {
            System.err.println("Error found");
        } else {
            System.out.println("Error NOT found");
        }
        System.exit(errFound ? 1 : 0);
    }

    public static void waitFor(final long milliseconds) {
        final long start = System.currentTimeMillis();
        FluentWait<By> fluentWait = new FluentWait<>(By.tagName("body"));
        final long timeout = milliseconds;
        fluentWait.pollingEvery(10, TimeUnit.MILLISECONDS).withTimeout(160, TimeUnit.SECONDS).until(new Predicate<By>() {

            @Override
            public boolean apply(By by) {
                return System.currentTimeMillis() - start >= timeout;
            }
        });
    }

}
