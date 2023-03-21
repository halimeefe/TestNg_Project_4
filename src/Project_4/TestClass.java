package Project_4;

import Utility.BaseDriver;
import Utility.Tools;
import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import sun.util.resources.ms.CalendarData_ms_MY;

import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.util.List;

public class TestClass  extends BaseDriver {

    @Test
    @Parameters("email")
    public void Case1(String email1) {

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        Actions actions = new Actions(driver);
        WebElement male = driver.findElement(By.id("gender-male"));
        male.click();

        WebElement register = driver.findElement(By.xpath("(//*[text()='Register'])[1]"));
        register.click();

        WebElement firstName = driver.findElement(By.cssSelector("[id='FirstName']"));
        firstName.sendKeys("Halime");
        actions.moveToElement(firstName).sendKeys(Keys.TAB).sendKeys("Efe").build().perform();
        WebElement webDay = driver.findElement(By.cssSelector("[name='DateOfBirthDay']"));
        Select select = new Select(webDay);
        select.selectByVisibleText("13");

        WebElement webMonth = driver.findElement(By.cssSelector("[name='DateOfBirthMonth']"));
        Select select1 = new Select(webMonth);

        select1.selectByVisibleText("April");

        WebElement webYear = driver.findElement(By.cssSelector("[name='DateOfBirthYear']"));
        Select select2 = new Select(webYear);

        select2.selectByVisibleText("1948");
        WebElement email = driver.findElement(By.cssSelector(" #Email"));
        email.sendKeys(email1);

        WebElement password = driver.findElement(By.cssSelector("[id='Password']"));
        password.sendKeys("123456");

        WebElement confirmPassword = driver.findElement(By.cssSelector("[id='ConfirmPassword']"));
        confirmPassword.sendKeys("123456");
        Tools.Bekle(2);

        WebElement registerr = driver.findElement(By.xpath("//button[@id='register-button']"));
        registerr.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Your registration completed']")));
        WebElement text = driver.findElement(By.xpath("//*[text()='Your registration completed']"));
        wait.until(ExpectedConditions.textToBe(By.xpath("//*[text()='Your registration completed']"), "Your registration completed"));
        Assert.assertTrue(text.getText().contains("Your registration"));
    }

    @Test
    @Parameters("email2")
    void Case2(String email2) {

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='ico-login']")));
        WebElement login = driver.findElement(By.cssSelector("[class='ico-login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(login).click().build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='email']")));
        WebElement remail = driver.findElement(By.xpath("//input[@class='email']"));
        remail.sendKeys(email2);

        WebElement repassword = driver.findElement(By.cssSelector("[id='Password']"));
        repassword.sendKeys("123456");
        WebElement loginn = driver.findElement(By.xpath("(//*[text()='Log in'])[2]"));
        loginn.click();

        System.out.println(driver.getCurrentUrl().equals("https://demo.nopcommerce.com/"));

        WebElement logout = driver.findElement(By.xpath("//*[text()='Log out']"));
        logout.click();

    }

    int i = 0;

    @Test( dataProvider = "login")

    void Case3(String remaill, String repassword) {

        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Log in']")));
        WebElement login = driver.findElement(By.xpath("//*[text()='Log in']"));
        actions.moveToElement(login).click().build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='Email']")));
        WebElement email = driver.findElement(By.xpath("//input[@id='Email']"));
        email.sendKeys(remaill);

        WebElement password = driver.findElement(By.cssSelector("[id='Password'] "));
        password.sendKeys(repassword);

        WebElement loginbuton = driver.findElement(By.xpath("(//button[@type='submit'])[2]"));
        loginbuton.click();

        if (i == 0) {

            WebElement wrong = driver.findElement(By.xpath("//*[text()='Login was unsuccessful. Please correct the errors and try again.']"));
            wait.until(ExpectedConditions.visibilityOf(wrong));
            Assert.assertTrue(wrong.getText().contains(" unsuccessful"));
            WebElement lgnClck = driver.findElement(By.xpath("//*[text()='Log in']"));
            lgnClck.click();
            Tools.Bekle(3);
            i++;
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Log out']")));

            WebElement logout = driver.findElement(By.xpath("//*[text()='Log out']"));
            Assert.assertTrue(logout.isDisplayed());

            WebElement logoutclick = driver.findElement(By.xpath("//*[text()='Log out']"));
            logoutclick.click();
        }
    }
    @DataProvider
    public Object[][] login() {
        Object[][] data = {{"fdg@gmail.com", "258794"}, {"lime@gmail.com", "123456"}};
        return data;
    }

    @Test
    void Case4() {
        List<WebElement> products = driver.findElements(By.xpath("(//*[@class='item-grid'])[2]//*[@class='product-item']"));
        for (WebElement w : products) {
            Assert.assertTrue(w.isDisplayed());
        }
    }

    @Test
    void Case5() {

        WebElement giftCard = driver.findElement(By.xpath("(//a[text()='Gift Cards '])[1]"));
        giftCard.click();
        WebElement giftClick = driver.findElement(By.xpath("//img[@alt='Picture of $50 Physical Gift Card']"));
        giftClick.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='recipient-name']")));

        WebElement recipientName = driver.findElement(By.cssSelector("[class='recipient-name']"));
        recipientName.sendKeys("Ayşe Nur");

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='message']")));
        WebElement msgbox = driver.findElement(By.cssSelector("[class='message']"));
        msgbox.sendKeys("Enjoy your Work");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Add to cart']")));
        WebElement AddToCart = driver.findElement(By.xpath("//*[text()='Add to cart']"));
        AddToCart.click();


    }
    @Test
    void Case6() {
        Actions actions = new Actions(driver);

        WebElement computers = driver.findElement(By.xpath("(//a[text()='Computers '])[1]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[text()='Computers '])[1]")));  //ul[@class='top-menu mobile'])//li//ul/li /a[1]
        actions.moveToElement(computers).click().build().perform();  //a[@href='/desktops'])[1]

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@href='/desktops'])[3]")));
        WebElement deskTop = driver.findElement(By.xpath("(//a[@href='/desktops'])[3]"));
        actions.moveToElement(deskTop).click().build().perform();

        WebElement desktopSec = driver.findElement(By.xpath("//a[text()='Build your own computer']"));
        desktopSec.click();

        WebElement webRam = driver.findElement(By.cssSelector("[id='product_attribute_2']"));
        actions.moveToElement(webRam).click().build().perform();
        Select ram = new Select(webRam);
        ram.selectByValue("5");

        WebElement webHdd = driver.findElement(By.xpath("(//input[@type='radio'])[2]"));
        actions.moveToElement(webHdd).click().build().perform();

        WebElement addToCart = driver.findElement(By.cssSelector("[id='add-to-cart-button-1']"));
        addToCart.click();

        WebElement dogrulama = driver.findElement(By.xpath("//*[text()='The product has been added to your ']"));
        Assert.assertTrue(dogrulama.isDisplayed());
    }

    @Test
    @Parameters("search")
    void Case7(String product) {

        WebElement search = driver.findElement(By.cssSelector("[id='small-searchterms']"));
        search.sendKeys(product);

        WebElement searchClick = driver.findElement(By.cssSelector("[type='submit']"));
        searchClick.click();//*[text()='Adobe Photoshop CS4']

        WebElement productName=driver.findElement(By.xpath("//*[text()='Adobe Photoshop CS4']"));
        Assert.assertTrue(productName.getText().equals("Adobe Photoshop CS4"));

    }
}
    








