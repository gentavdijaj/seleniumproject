package serenity;

import net.serenitybdd.junit.runners.SerenityRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@RunWith(SerenityRunner.class)
public class Deutsch {

  WebDriver chromeDriver;


  @Test
  public void shouldInstantiateAWebDriverInstanceForAWebTest() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setExperimentalOption("detach", true);

    chromeDriver = new ChromeDriver(chromeOptions);
    chromeDriver.get("https://service2.diplo.de/rktermin/extern/choose_categoryList.do?locationCode=pris&realmId=362");

    isPageLoaded();
  }

  private void isPageLoaded() {
    boolean pageIsLoaded = waitForElementVisibility();
    System.out.println("First visibility: " + pageIsLoaded);
    while (!pageIsLoaded) {
      System.out.println("Into while loop!");
      chromeDriver.navigate().refresh();
      pageIsLoaded = waitForElementVisibility();
      System.out.println("Is visible on WHILE: " + pageIsLoaded);
    }
  }

  private boolean waitForElementVisibility() {
    try {
      new WebDriverWait(chromeDriver, 30).until(visibilityOf(chromeDriver.findElement(By.xpath("//h1[text() = " +
          "'Appointment-System of the German Foreign Office — Pristina']"))));
      return true;
    } catch (Exception t) {
      return false;
    }
  }
}