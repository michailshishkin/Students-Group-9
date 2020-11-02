import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
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
    private static WebElement actionHint;
    private static String NumberOrder;
    private static String TotalAmount;
    private static String Currency;
    private static String CardType;

    @Before
    public void setUp() {
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
        actionHint = driver.findElement(By.id("cvc-hint-toggle"));

        NumberOrder = driver.findElement(By.id("order-number")).getText();
        TotalAmount = driver.findElement(By.id("total-amount")).getText();
        Currency = driver.findElement(By.id("currency")).getText();
    }

    @Test
    public void T1() {
        cardNumber.sendKeys("4000000000000002");
        cardHolder.sendKeys("John Doe");
        expiresMonth.selectByIndex(7);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("777");
        actionSubmit.click();

        WebElement successAction = driver.findElement(By.id("success"));
        successAction.submit();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status")).getText();
        Assert.assertEquals("Payment status Confirmed", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("JOHN DOE", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());
    }

    @Test
    public void T2() {
        cardNumber.sendKeys("5555555555554444");
        cardHolder.sendKeys("Mr Smith");
        expiresMonth.selectByIndex(7);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("777");
        actionSubmit.click();

        WebElement successAction = driver.findElement(By.id("failure"));
        successAction.submit();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status")).getText();
        Assert.assertEquals("Payment status Declined by issuing bank", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("MR SMITH", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());
    }

    @Test
    public void T3() {
        cardNumber.sendKeys("4000000000000044");
        cardHolder.sendKeys("John Doe");
        expiresMonth.selectByIndex(7);
        expiresYear.selectByValue("2024");
        cardCvc.sendKeys("777");
        actionSubmit.click();

        WebElement successAction = driver.findElement(By.id("failure"));
        successAction.submit();

        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status")).getText();
        Assert.assertEquals("Payment status Pending", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("JOHN DOE", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());
    }

    @Test
    public void T4() {
        cardNumber.sendKeys("4000000000000077");
        cardHolder.sendKeys("Mr Pony");
        expiresMonth.selectByIndex(11);
        expiresYear.selectByValue("2023");
        cardCvc.sendKeys("125");
        actionSubmit.click();


        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status")).getText();
        Assert.assertEquals("Payment status Confirmed", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("MR PONY", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());
    }

    @Test
    public void T5() {
        cardNumber.sendKeys("5555555555554477");
        cardHolder.sendKeys("Mr Pony");
        expiresMonth.selectByIndex(11);
        expiresYear.selectByValue("2023");
        cardCvc.sendKeys("125");
        actionSubmit.click();


        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status")).getText();
        Assert.assertEquals("Payment status Declined by issuing bank", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("MR PONY", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());
    }

    @Test
    public void T6() {
        cardNumber.sendKeys("4000000000000051");
        cardHolder.sendKeys("Mr Pony");
        expiresMonth.selectByIndex(11);
        expiresYear.selectByValue("2023");
        cardCvc.sendKeys("125");
        actionSubmit.click();


        String paymentStatus = driver.findElement(By.cssSelector("#payment-item-status")).getText();
        Assert.assertEquals("Payment status Pending", paymentStatus);
        Assert.assertEquals(NumberOrder, driver.findElement(By.cssSelector("#payment-item-ordernumber > div.payment-info-item-data")).getText());
        Assert.assertEquals("MR PONY", driver.findElement(By.cssSelector("#payment-item-cardholder > div.payment-info-item-data")).getText());
        Assert.assertEquals(TotalAmount, driver.findElement(By.cssSelector("#payment-item-total-amount")).getText());
    }

    @Test
    public void T7_znakvoprosa() {

        cardNumber.sendKeys("4000000000000051");
        cardHolder.sendKeys("Mr Pony");
        expiresMonth.selectByIndex(11);
        expiresYear.selectByValue("2023");
        cardCvc.sendKeys("125");
        actionHint.click();


    }



    //@After
    //public void close() {
    //     driver.quit();
    // }

}