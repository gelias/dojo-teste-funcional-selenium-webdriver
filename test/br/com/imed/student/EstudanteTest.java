package br.com.imed.student;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EstudanteTest {
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
  public void testStudentTestCase() throws Exception {
    driver.get(baseUrl + "/diario-classe/estudante");
    driver.findElement(By.linkText("adicionar")).click();
    driver.findElement(By.name("estudante.id")).clear();
    driver.findElement(By.name("estudante.id")).sendKeys("1");
    driver.findElement(By.name("estudante.nome")).clear();
    driver.findElement(By.name("estudante.nome")).sendKeys("Guilherme Elias");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    try {
      assertEquals("1 Guilherme Elias", driver.findElement(By.cssSelector("span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("editar")).click();
    driver.findElement(By.name("estudante.nome")).clear();
    driver.findElement(By.name("estudante.nome")).sendKeys("Guilherme da Silveira Elias");
    driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    try {
      assertEquals("1 Guilherme da Silveira Elias", driver.findElement(By.cssSelector("span")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.linkText("deletar")).click();
    // Warning: verifyTextNotPresent may require manual changes
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
