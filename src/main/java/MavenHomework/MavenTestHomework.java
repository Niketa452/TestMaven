package MavenHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MavenTestHomework {
    protected static WebDriver driver;//calling webdriver

    public String generateEmail(String startValue){
        String email = startValue.concat((new Date().toString().replaceAll(" ", "").replaceAll(":","")));
        return email;
    }
    public static String randomDate(){
        DateFormat format = new SimpleDateFormat("ddMMyyHHssmm");
        return format.format(new Date());
    }


    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\Webdriver\\chromedriver.exe");//calling the chromedriver path
        driver = new ChromeDriver();//creating chrome driver object
        driver.manage().window().fullscreen();//to maximise the web page.
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//waiting time before opening teh website
    }
    @Test
    public void registrationNopCommerce(){
        driver.get("https://demo.nopcommerce.com/");//web page url given
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();//to click on register button for registration
    driver.findElement(By.id("FirstName")).sendKeys("Niketa");//registration form compulsory fields to be filled, Enter name
    driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Parekh");//Enter surname
    driver.findElement(By.name("Email")).sendKeys(generateEmail("test")+"@gmail.com" );//Enter email
    driver.findElement(By.id("Password")).sendKeys("london26");//Enter password
    driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("london26");//Enter Confirm password
    driver.findElement(By.xpath("//input[@type='submit' and @name='register-button']")).click();//click on register button
        String actualMessage = driver.findElement(By.className("result")).getText();//storing value of actual message in a string variable
        String expectedMessage = "Your registration completed";//actual display message
        System.out.println("Actual message is " + actualMessage);
        Assert.assertEquals(actualMessage, expectedMessage);//validating acutal with expected


}
@Test
    public void emailAProductToAFriend() throws InterruptedException {
        registrationNopCommerce();
    driver.findElement(By.xpath("//img")).click();//returning back to the home page for purchasing
    driver.findElement(By.linkText("Computers")).click();//click on computers category
    driver.findElement(By.linkText("Notebooks")).click();//click on notebook category
    driver.findElement(By.linkText("Apple MacBook Pro 13-inch")).click();//selecting preferred notebook
    driver.findElement(By.xpath("//input[@value='Email a friend']")).click();//clicking on the email a friend button to refer the product
    driver.findElement(By.className("friend-email")).sendKeys("karishma@gmail.com");//Enter friend's email address
    driver.findElement(By.xpath("//textarea")).sendKeys("Hi, this looks like a great product. Consider buying?");
    driver.findElement(By.name("send-email")).click();//Click on send button
    Thread.sleep(5000);//waiting time before closing
    String expectedconfirmationmessage = "Your message has been sent.";//storing expected message in a string variable
    String actualmessage=driver.findElement(By.xpath("//div[@class='result']")).getText();//getting actual text from the webpage
    System.out.println("Acutal confirmation message is : "+actualmessage);//
    Assert.assertEquals(actualmessage,expectedconfirmationmessage);//comparing actual result with expected.
    }

@Test
    public void userShouldBeAbleToNavigateToCameraAndPhotoPage(){
        driver.get("https://demo.nopcommerce.com/");//to enter url
    Actions actions = new Actions(driver);//creating instance of action class for mouse hover.
    WebElement electronicsMenu = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));//storing location of dropdown in webelement variable
    actions.moveToElement(electronicsMenu).perform();
    WebElement selectElectronics = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Camera & photo')]"));
    selectElectronics.click();//selecting and clicking camera and photo subcategory.
    String actual_msg = driver.findElement(By.xpath("//h1")).getText();//getting and storing actual display message.
    String expected_msg = "Camera & photo";
    System.out.println("Actual tittle displayed is : "+actual_msg);
    Assert.assertEquals(actual_msg, expected_msg);//validating actual message with the expected message
}
    @Test
    public void userShouldBeAbleToFilterJewelleryByPrice() {
        String ans;
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath(" //ul[@class='top-menu notmobile']//a[contains(text(),'Jewelry')]")).click();//clicking on 'Jewellery' on the homepage.
        driver.findElement(By.cssSelector("a[href='//demo.nopcommerce.com/jewelry?price=700-3000']")).click();//clicking on the filter attribute of range $700-$3000
        String price = driver.findElement(By.xpath("//span[@class='price actual-price']")).getText();//getting and storing text according to the filter selected.
        System.out.println(price);
        price = price.substring(0, 6);//to eliminate junk characters and converting string to integer value.
        price = price.replaceAll("[^0-9]", "");
        int x = Integer.parseInt(price);
        if (x >= 700 && x <= 3000) {//checking the filter function
            ans = "PASSED";
            System.out.println("Your test has " + ans);
        } else {

            ans = "FAILED";
            System.out.println("Your test has " + ans);

        }
        String pageTitle=driver.findElement(By.xpath("//h1[contains(text(),'Jewelry')]")).getText();//verifying user is navigated to jewllery page
        String actual_title="Jewelry";
        System.out.println("you are navigated to : "+actual_title+ " page." );
        Assert.assertEquals(pageTitle,actual_title);
    }
    @Test
    public void userShouldBeAbleToAddTheProductsToTheShoppingCart() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Books')]")).click();//clicking on books link
        driver.findElement(By.xpath("//img[@alt='Picture of Fahrenheit 451 by Ray Bradbury']")).click();//Clicking on a book
        driver.findElement(By.cssSelector("#add-to-cart-button-37")).click();//adding a book to the cart
        driver.findElement(By.xpath("//span[contains(text(),'Books')]")).click();//clicking on books link
        driver.findElement(By.cssSelector("img[title$='Prejudice']")).click();//selecting another book to add to cart
        driver.findElement(By.cssSelector("#add-to-cart-button-39")).click();//adding to cart
        Thread.sleep(5000);//instructing browser to wait
        driver.findElement(By.xpath("//span[@class='cart-label']")).click();//clicking on shopping cart lable to view the products addied
        Actions actions = new Actions(driver);//creating instance of Action class
        WebElement shoppincart = driver.findElement(By.xpath("//span[@class='cart-label']"));//storing webelement in a variable
        actions.moveToElement(shoppincart).perform();//storing and getting string value in a variable
        String qty = driver.findElement(By.xpath("//span[@class='cart-qty']")).getText();
        System.out.println("Actual quantity ordered : " + qty);
        String expected_qty = "(2)";
        Assert.assertEquals(qty, expected_qty);//validating that shopping cart shows exact number of products shopped
    }

        @AfterMethod
        public void teardownr(){

            driver.quit();


        }

    }




