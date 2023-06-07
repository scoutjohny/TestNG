import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Wait {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_114.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //Implicitno čekanje - Čeka dinamički svaki element da se pojavi određeni broj sekundi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.ctshop.rs/");
    }
    @AfterMethod
    public void teardown(){
       // driver.quit();
    }

    @Test
    public void ctShopWaitDemo() throws InterruptedException {
        //Grubo čekanje - zamrzava izvršavanje programa na određeni broj milisekundi
        //Thread.sleep(3000);

        //Eksplicitno čekanje - Čeka dinamički da se određeni uslov ispuni za određeni broj sekundi i to proverava na svakih 0,5s
        WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-input-header")));
        driver.findElement(By.id("search-input-header")).sendKeys("MacBook Pro");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("button"))));
        driver.findElement(By.className("button")).click();
        driver.findElement(By.xpath("//a[@href='/customer/account/create']")).click();

        //Select - padajući meni
        Select pravnoLice = new Select(driver.findElement(By.cssSelector("#pravnolice")));
        pravnoLice.selectByVisibleText("Da");
        Assert.assertTrue(driver.findElement(By.cssSelector("#pib")).isDisplayed());

        driver.findElement(By.cssSelector("#firstname")).sendKeys("Tester");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Testerovic");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("tester@yopmail.com");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Testerovic");
        driver.findElement(By.cssSelector("#password")).sendKeys("Test001!");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("Test001!");


        //Logika za proveru da li je checkbox obeležen i da ga obeležimo ukoliko nije
        if(!driver.findElement(By.cssSelector("#is_subscribed")).isSelected()){
            driver.findElement(By.cssSelector("#is_subscribed")).click();
        }

        if(!driver.findElement(By.cssSelector("[name='rememberme']")).isSelected()){
            driver.findElement(By.cssSelector("[name='rememberme']")).click();
        }

        //Otvaranje novog tab-a pomoću javaScript-a:
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open()");

        //menjanje tab-ova preko liste svih otvorenih tabova:
        List<String> windowHandles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles.get(1));
        driver.get("https://www.google.com/");
        Thread.sleep(2000);
        js.executeScript("window.open()");
        List<String> windowHandles1 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles1.get(2));
        driver.get("https://www.tehnomanija.rs/");
        Thread.sleep(2000);
        driver.switchTo().window(windowHandles1.get(1));
        driver.close();
        List<String> windowHandles2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(windowHandles2.get(1));

        //Logika za otvaranje više tabova i odlazak na URL na svakom od njih
        for(int i=1;i<11;i++){
            js.executeScript("window.open()");
            List<String> windowHandles3 = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(windowHandles3.get(i+1));
            driver.get("https://gigatron.rs/");
        }


    }
}
