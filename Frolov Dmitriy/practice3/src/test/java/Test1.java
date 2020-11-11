import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class Test1 {

    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();

    }

    @Test
    public void test1() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://sandbox.cardpay.com/MI/payment.html?uuid=h5eBBbHF427abFecA26ge2CD");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("form")));
        driver.findElement(By.id("input-card-number")).sendKeys("4000000000000002");
        driver.findElement(By.id("input-card-holder")).sendKeys("JOE BADEN");
        Random random = new Random();
        int n = random.nextInt(12) + 1;
        driver.findElement(By.id("card-expires-month")).sendKeys("+ n +");
    }

    @After
    public void close() {
        driver.quit();
    }
}
