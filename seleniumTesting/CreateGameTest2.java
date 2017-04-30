package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateGameTest2 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCreateGameTest2() throws Exception {
    driver.get(baseUrl + "/course/math");
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    driver.findElement(By.id("inputGameName")).clear();
    driver.findElement(By.id("inputGameName")).sendKeys("game");
    driver.findElement(By.id("inputGameDescription")).clear();
    driver.findElement(By.id("inputGameDescription")).sendKeys("desc");
    driver.findElement(By.id("inputGameName")).clear();
    driver.findElement(By.id("inputGameName")).sendKeys("bla");
    driver.findElement(By.id("inputGameInstructions")).clear();
    driver.findElement(By.id("inputGameInstructions")).sendKeys("inst");
    driver.findElement(By.id("inputGameQuestion")).clear();
    driver.findElement(By.id("inputGameQuestion")).sendKeys("how");
    driver.findElement(By.id("inputGameAnswer")).clear();
    driver.findElement(By.id("inputGameAnswer")).sendKeys("ayklam");
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
