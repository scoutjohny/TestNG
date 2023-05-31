import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class DemoTestNG {
    WebDriver driver;
    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser){
        if(browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_114.exe");
            driver = new ChromeDriver();
        } else if(browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver0322.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
    @Test
    @Parameters({"username","password","url","errorMessage","testType"})
    public void loginToSauceDemo(String username, String password, String url, @Optional String errorMessage, String testType){
        if(!username.equals("empty")) {
            driver.findElement(By.id("user-name")).sendKeys(username);
        }
        if(!password.equals("empty")) {
            driver.findElement(By.id("password")).sendKeys(password);
        }
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.getCurrentUrl(),url);
        if(testType.equals("negative")){
            Assert.assertEquals(driver.findElement(By.cssSelector("h3")).getText(),errorMessage);
        }
    }

}
