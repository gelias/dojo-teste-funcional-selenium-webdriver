package br.com.imed.discipline;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runners.model.TestClass;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DisciplinaTest{
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testDisciplineTestCase() throws Exception {
    driver.get(baseUrl + "/diario-classe/disciplina");
    driver.findElement(By.linkText("adicionar")).click();
    driver.findElement(By.name("disciplina.id")).clear();
    driver.findElement(By.name("disciplina.id")).sendKeys("1");
    driver.findElement(By.name("disciplina.nome")).clear();
    driver.findElement(By.name("disciplina.nome")).sendKeys("Java Web 3.0");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    try {
      assertEquals("1 Java Web 3.0", driver.findElement(By.cssSelector("span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("editar")).click();
    driver.findElement(By.name("disciplina.nome")).clear();
    driver.findElement(By.name("disciplina.nome")).sendKeys("Java Web");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    try {
      assertEquals("1 Java Web", driver.findElement(By.cssSelector("span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("deletar")).click();
    try {
      assertFalse(driver.findElement(By.cssSelector("BODY")).getText().matches("^[\\s\\S]*css=span[\\s\\S]*$"));
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
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
