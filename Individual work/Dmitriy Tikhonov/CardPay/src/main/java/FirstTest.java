import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
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
    public void firstAttempts() throws IOException { //Первый тест
        cardNumber.sendKeys("4000000000000002");
        cardHolder.sendKeys("Dimasik");
        expiresMonth.selectByIndex(11);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("395");

        cvcHintToogle.click();

        /*/Делаем скрин/*/
        File cvcHintToogle = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(cvcHintToogle, new File("target\\screenshot\\cvcHintToogle.png"));

        actionSubmit.click();
        WebElement successAction = driver.findElement(By.id("success"));
        successAction.submit();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-status-title > span")).getText();
        Assert.assertEquals("Success", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("DIMASIK", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());

        /*/Делаем скрин/*/
        File CONFIRMEDas3DSecuretransaction = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(CONFIRMEDas3DSecuretransaction, new File("target\\screenshot\\CONFIRMED as 3-D Secure transaction.png"));

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void secondsAttempts() throws IOException { //Второй тест
        cardNumber.sendKeys("5555555555554444");
        cardHolder.sendKeys("Dimasik");
        expiresMonth.selectByIndex(11);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("395");

        actionSubmit.click();
        WebElement successAction = driver.findElement(By.id("success"));
        successAction.submit();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-status-title > span")).getText();
        Assert.assertEquals("Decline", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("DIMASIK", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());

        /*/Делаем скрин/*/
        File DECLINEDas3DSecuretransaction = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(DECLINEDas3DSecuretransaction, new File("target\\screenshot\\DECLINED as 3-D Secure transaction.png"));

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void thirdAttempts() throws IOException { //Третий тест
        cardNumber.sendKeys("4000000000000044");
        cardHolder.sendKeys("Dimasik");
        expiresMonth.selectByIndex(11);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("395");

        actionSubmit.click();
        WebElement successAction = driver.findElement(By.id("success"));
        successAction.submit();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-status-title > span")).getText();
        Assert.assertEquals("Info", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("DIMASIK", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());

        /*/Делаем скрин/*/
        File PENDINGas3DSecuretransaction = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(PENDINGas3DSecuretransaction, new File("target\\screenshot\\PENDING as 3-D Secure transaction.png"));

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void fourthAttempts() throws IOException { //Четвертый тест. Невалидные данные
        cardNumber.sendKeys("5305395333333333");
        cardHolder.sendKeys("Dimasik");
        expiresMonth.selectByIndex(11);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("395");

        actionSubmit.click();

        Assert.assertEquals("Card number is not valid", driver.findElement(By.cssSelector("#card-number-field > div > label")).getText());

        /*/Делаем скрин/*/
        File CardNumberIsNotValid = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(CardNumberIsNotValid, new File("target\\screenshot\\Card number is not valid.png"));
    }

//    @AfterClass
//    public static void tearDown() {
//        driver.quit();
//    }
}
