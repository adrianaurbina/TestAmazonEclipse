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
		
		
	    driver.get("https://www.amazon.com");
	    driver.findElement(By.id("twotabsearchtextbox")).click();
	    driver.findElement(By.id("twotabsearchtextbox")).clear();
	    driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Iphone 15 pro max");
	    driver.findElement(By.id("nav-search-bar-form")).submit();
	    driver.findElement(By.xpath("//div[@id='search']/div/div/div/span/div/div[2]/div/div/span/div/div/div/div[2]/div/div/div/h2/a/span")).click();
	    
	    String currentUrl = driver.getCurrentUrl();
	  
	    String expectedUrl = "https://www.amazon.com/Apple-iPhone-15-Pro-Max/dp/B0CMZD7VCV/ref=sr_1_1?crid=2MLL6GB07N8XX&dib=eyJ2IjoiMSJ9.sd6YOd3xvFlAAGfN75liJ_OyJCTCrRXAzdif7WPpsJ5zaeLum1zJ7Hr3go5gRPhIPdI7xOKg1vKyYxdtqOl7QUZ9Z_iG5gdOXZ_Wz0a2yJp55F227PibWYSax2SR3Kwzc2cl7e0la0g0WVaay78bImS9TLFkWRmTzRU6UpgEmCTAlzEYxcVwE2qblDRksOitXEUCzMDoXH7cL0MKmb2mGn5Ca3xa2X3T2XYvRuV0rzA.tv8p5TK0TgvhJIRaK7hrIy8GTyKwXMZfBVYwDsBZDxs&dib_tag=se&keywords=Iphone%2B15%2Bpro%2Bmax&qid=1713974232&sprefix=%2Caps%2C178&sr=8-1&th=1";
	    
	   Assert.assertEquals(expectedUrl, currentUrl);
	    
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
