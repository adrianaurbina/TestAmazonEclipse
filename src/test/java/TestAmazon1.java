import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import java.io.File;

public class TestAmazon1 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
    
	 WebDriverManager.edgedriver().setup();
	driver = new EdgeDriver();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }
	@Test
	  public void testAmazonPhone() throws Exception {
		
		String searchDescription = "Iphone 15 pro max";
	    driver.get("https://www.amazon.com.mx");
	    driver.findElement(By.id("twotabsearchtextbox")).click();
	    driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchDescription);
	    driver.findElement(By.id("nav-search-bar-form")).submit();
	    
	    WebElement firstResult = driver.findElement(By.xpath("//div[@class='s-main-slot s-result-list s-search-results sg-row']//div[@data-index='0']//a[@class='a-link-normal a-text-normal']"));
	    firstResult.click();
	    
	    boolean titleContainsName = driver.getTitle().toLowerCase().contains(searchDescription.toLowerCase());
	    
	   Assert.assertTrue(titleContainsName);
	    
	    WebElement element = driver.findElement(By.xpath("//div[@id='corePrice_feature_div']/div/div/span/span[2]"));
	    String price = element.getText();
	    System.out.print(price);
	    
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
