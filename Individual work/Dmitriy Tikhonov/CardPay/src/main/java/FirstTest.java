import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FirstTest {
    private static WebDriver driver;
    private static WebElement cardNumber;
    private static WebElement cardHolder;
    private static Select expiresMonth;
    private static Select expiresYear;
    private static WebElement cardCvc;
    private static WebElement actionSubmit;
    private static WebElement actionCancel;
    private static WebElement cvcHintToogle;
    private static String NumberOrder;
    private static String TotalAmount;
    private static String Currency;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\test\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");

        cardNumber = driver.findElement(By.id("input-card-number"));
        cardHolder = driver.findElement(By.id("input-card-holder"));
        expiresMonth = new Select(driver.findElement(By.id("card-expires-month")));
        expiresYear = new Select(driver.findElement(By.id("card-expires-year")));
        cardCvc = driver.findElement(By.id("input-card-cvc"));
        actionSubmit = driver.findElement(By.id("action-submit"));
        actionCancel = driver.findElement(By.id("action-cancel"));

        NumberOrder = driver.findElement(By.id("order-number")).getText();
        TotalAmount = driver.findElement(By.id("total-amount")).getText();
        Currency = driver.findElement(By.id("currency")).getText();

        cvcHintToogle = driver.findElement((By.id("cvc-hint-toggle")));
    }

    @Test
    public void firstAttempts() throws IOException{
        cardNumber.sendKeys("4000000000000002");
        cardHolder.sendKeys("Dimasik");
        expiresMonth.selectByIndex(11);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("395");

        cvcHintToogle.click();
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("target\\screenshot\\screenshot.png"));
//        actionSubmit.click();
    }

}
