import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
    private static WebElement dataShipping;
    private static WebElement actionSubmit;
    private static WebElement actionCancel;
    private static String NumberOrder;
    private static String TotalAmount;
    private static String Currency;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/chromedriver");
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

        System.out.println("Test start.");
    }

    @Test
    public void firstAttepmts() {
        cardNumber.sendKeys("4000000000000002");
        cardHolder.sendKeys("JANE DOE");
        expiresMonth.selectByIndex(7);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("777");
        actionSubmit.click();

        WebElement successAction = driver.findElement(By.id("success"));
        successAction.submit();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status > div.payment-info-item-data")).getText();
        Assert.assertEquals("Confirmed", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("JANE DOE", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void secondsAttepmts() {

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

        cardNumber.sendKeys("5555555555554444");
        cardHolder.sendKeys("JANE DOE");
        expiresMonth.selectByIndex(7);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("777");
        actionSubmit.click();

        WebElement successAction = driver.findElement(By.id("success"));
        successAction.submit();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status > div.payment-info-item-data")).getText();
        Assert.assertEquals("Declined by issuing bank", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("JANE DOE", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void thirdAttempts() { //Третий тест

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

        cardNumber.sendKeys("4000000000000044");
        cardHolder.sendKeys("JANE DOE");
        expiresMonth.selectByIndex(8);
        expiresYear.selectByValue("2023");
        cardCvc.sendKeys("888");

        actionSubmit.click();
        WebElement successAction = driver.findElement(By.id("success"));
        successAction.submit();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-status-title > span")).getText();
        Assert.assertEquals("Info", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("JANE DOE", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());


        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void fourthAttepmts() {

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

        cardNumber.sendKeys("4000000000000077");
        cardHolder.sendKeys("JANE DOE");
        expiresMonth.selectByIndex(5);
        expiresYear.selectByValue("2022");
        cardCvc.sendKeys("666");
        actionSubmit.click();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status > div.payment-info-item-data")).getText();
        Assert.assertEquals("Confirmed", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("JANE DOE", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void fifthAttepmts() {

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

        cardNumber.sendKeys("5555555555554477");
        cardHolder.sendKeys("JANE DOE");
        expiresMonth.selectByIndex(12);
        expiresYear.selectByValue("2021");
        cardCvc.sendKeys("112");
        actionSubmit.click();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status > div.payment-info-item-data")).getText();
        Assert.assertEquals("Declined by issuing bank", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("JANE DOE", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void sixthAttepmts() {

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

        cardNumber.sendKeys("4000000000000051");
        cardHolder.sendKeys("JANE DOE");
        expiresMonth.selectByIndex(10);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("911");
        actionSubmit.click();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-status-title > span")).getText();
        Assert.assertEquals("Info", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("JANE DOE", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void isValidCardNumberAttepmts() {

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

        cardNumber.sendKeys("400000077");
        cardHolder.sendKeys("JANE DOE");
        expiresMonth.selectByIndex(5);
        expiresYear.selectByValue("2022");
        cardCvc.sendKeys("666");
        actionSubmit.click();

        String paymentStatus = driver.findElement(By.cssSelector("#card-number-field > div > label")).getText();
        Assert.assertEquals("Card number is not valid", paymentStatus);

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void isValidCardHolderAttepmts() {

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

        cardNumber.sendKeys("4000000000000077");
        cardHolder.sendKeys("4000000000000077");
        expiresMonth.selectByIndex(5);
        expiresYear.selectByValue("2022");
        cardCvc.sendKeys("666");
        actionSubmit.click();

        String paymentStatus = driver.findElement(By.cssSelector("#card-holder-field > div > label")).getText();
        Assert.assertEquals("Cardholder name is not valid", paymentStatus);

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void isValidExpiresDateAttepmts() {

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

        cardNumber.sendKeys("4000000000000077");
        cardHolder.sendKeys("JANE DOE");
        expiresMonth.selectByIndex(10);
        expiresYear.selectByValue("2020");
        cardCvc.sendKeys("666");
        actionSubmit.click();

        String paymentStatus = driver.findElement(By.cssSelector("#card-expires-field > div > label")).getText();
        Assert.assertEquals("Invalid date", paymentStatus);

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void isValid3ttepmts() {

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

        cardNumber.sendKeys("4000000000000077");
        cardHolder.sendKeys("JANE DOE");
        expiresMonth.selectByIndex(5);
        expiresYear.selectByValue("2022");
        cardCvc.sendKeys("66");
        actionSubmit.click();

        String paymentStatus = driver.findElement(By.cssSelector("#card-cvc-field > div > label")).getText();
        Assert.assertEquals("CVV2/CVC2/CAV2 is not valid", paymentStatus);

        driver.get("https://sandbox.cardpay.com/MI/cardpayment2.html?orderXml=PE9SREVSIFdBTExFVF9JRD0nODI5OScgT1JERVJfTlVNQkVSPSc0NTgyMTEnIEFNT1VOVD0nMjkxLjg2JyBDVVJSRU5DWT0nRVVSJyAgRU1BSUw9J2N1c3RvbWVyQGV4YW1wbGUuY29tJz4KPEFERFJFU1MgQ09VTlRSWT0nVVNBJyBTVEFURT0nTlknIFpJUD0nMTAwMDEnIENJVFk9J05ZJyBTVFJFRVQ9JzY3NyBTVFJFRVQnIFBIT05FPSc4NzY5OTA5MCcgVFlQRT0nQklMTElORycvPgo8L09SREVSPg==&sha512=998150a2b27484b776a1628bfe7505a9cb430f276dfa35b14315c1c8f03381a90490f6608f0dcff789273e05926cd782e1bb941418a9673f43c47595aa7b8b0d");
    }

    @Test
    public void isHoverMouseScreenshot() throws IOException {
        WebElement searchHint = driver.findElement(By.cssSelector("#cvc-hint"));

        Actions action = new Actions(driver);
        action.moveToElement(searchHint).perform();

        File cvcHint = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(cvcHint, new File("src/test/cvcHint.png"));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        System.out.println("Test end.");
    }
}