import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Random;


public class SeleniumWebSignupTest {
    private WebDriver webDriver;
    private final String TEST_URL = "https://selenium-blog.herokuapp.com";

    @BeforeTest
    public void start() throws InterruptedException {
        //Set location of your webdriver
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        //create an instance of EdgeDriver
        webDriver = new ChromeDriver();
        //Open selenium website
        webDriver.get(TEST_URL);
        //Verify that the user is on the right page by checking if the current page url is the same as the url enetered by the user
        if (webDriver.getCurrentUrl().contains(TEST_URL)) {
            System.out.println("Correct Page: The user is on the right page");
        } else {
            System.out.println("Wrong Page: The user is on the wrong page");
        }
        Thread.sleep(5000);

        //open the broswer in fullscreen
        webDriver.manage().window().fullscreen();


        //click on the signup button
        webDriver.findElement(By.xpath("/html/body/div[2]/div/a[2]")).click();
        //Tell the sysytem to wait for 5 seconds or the page to load
        Thread.sleep(5000);

    }

    @Test(priority = 0)
    public  void positiveSignup() throws InterruptedException{
        //Verify that user can signup succesfully using valid details
        int randomNumber = (int)Math.floor(Math.random()*(2900-1+2));
        webDriver.findElement(By.id("user_username")).sendKeys("Lucy"+randomNumber);
        String email = "azakalucy"+ randomNumber +"@gmail.com";
        webDriver.findElement(By.id("user_email")).sendKeys(email);
        webDriver.findElement(By.id("user_password")).sendKeys("@Password29");
        webDriver.findElement(By.id("submit")).click();
        Thread.sleep(5000);

    }




    @Test(priority = 1)
    public  void clickUser1Item() throws InterruptedException{
        //Verify that user can signup succesfully using valid details
        webDriver.findElement(By.xpath("/html/body/div[2]/div[1]/ul/div/div/li[1]/a")).click();
        Thread.sleep(1000);
        String expectedUrl ="https://selenium-blog.herokuapp.com/users/1";
        String currentUrl = webDriver.getCurrentUrl();
        if(currentUrl.equalsIgnoreCase(expectedUrl)){
            System.out.println("Correct Userpage1");
        }else{
            System.out.println("Wrong UserPage1");
        }

    }

    @Test(priority = 2)
    public  void logoutSuccesfully() throws InterruptedException{
        //Verify that user can signup succesfully using valid details
        webDriver.findElement(By.xpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[3]/a")).click();
        Thread.sleep(5000);

        String currentUrl = webDriver.getCurrentUrl();
        if(currentUrl.contains(TEST_URL)){
            System.out.println("Logout succesful");
        }else{
            System.out.println("Logout failed");
        }

    }

    @Test(priority = 3)
    public  void negativeSignupUsername() throws InterruptedException{
        //Verify that user can not signup with username less 3 char
        //click on the signup button
        webDriver.findElement(By.xpath("/html/body/div[2]/div/a[2]")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.id("user_username")).sendKeys("Lu");
        webDriver.findElement(By.id("user_email")).sendKeys("azakalucy+297@gmail.com");
        webDriver.findElement(By.id("user_password")).sendKeys("@Password29");
        webDriver.findElement(By.id("submit")).click();
        Thread.sleep(5000);

    }
    @Test(priority = 4)
    public  void negativeSignupEmail() throws InterruptedException{
        //Verify that user can not signup with username less 3 char
        webDriver.findElement(By.id("user_username")).sendKeys("Lude3");
        webDriver.findElement(By.id("user_email")).clear();
        webDriver.findElement(By.id("user_email")).sendKeys("azainvalud");
        webDriver.findElement(By.id("user_password")).sendKeys("@Password29");
        webDriver.findElement(By.id("submit")).click();
        Thread.sleep(5000);

    }

    @AfterTest()

    public  void stopTest()
    {
        //close the browser
        webDriver.quit();
    }

}
