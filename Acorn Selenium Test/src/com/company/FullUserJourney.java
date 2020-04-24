package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/*
Flow of the Full User Journey:
Homepage --> Connect to Agent --> Chat Page --> Swap to Calls --> Call Page --> End Call --> Feedback Page --> Provide Feedback
 */
public class FullUserJourney {
    public static void main(String[] args) throws InterruptedException {
        //Opening Chrome Driver
        ChromeOptions optionsC = new ChromeOptions();
        optionsC.addArguments(Arrays.asList("disable-infobars", "ignore-certificate-errors", "start-maximized","use-fake-ui-for-media-stream"));
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(optionsC);
        driver.get("https://esc-frontend.netlify.app/");
        driver.manage().window().maximize();
        System.out.println("input");

        //Main Page
        WebElement FirstNameMain = driver.findElement(By.id("FirstName"));
        FirstNameMain.sendKeys("Chen");
        TimeUnit.SECONDS.sleep(2);
        WebElement LastNameMain = driver.findElement(By.id("LastName"));
        LastNameMain.sendKeys("Yan");
        WebElement category = driver.findElement(By.xpath("//*[@class='v-select__selections']"));
        category.click();
        TimeUnit.SECONDS.sleep(2);
        WebElement choice = driver.findElement(By.xpath("//*[@class='v-list-item__title'] [text()='Acorn Applications']"));
        choice.click();
        TimeUnit.SECONDS.sleep(1);


        WebElement chat = driver.findElement(By.id("Chat"));
        chat.click();
        WebElement reCaptchaText = driver.findElement(By.id("recaptcha"));
        TimeUnit.SECONDS.sleep(2);
        reCaptchaText.sendKeys("yes");
        TimeUnit.SECONDS.sleep(2);
        WebElement reCaptchaBtn = driver.findElement(By.id("recaptchaCheck"));
        reCaptchaBtn.click();


        //Chat Page
        TimeUnit.SECONDS.sleep(22);
        WebElement type = driver.findElement(By.id("WriteMessage"));
        type.sendKeys("Hello I need help!");
        TimeUnit.SECONDS.sleep(1);
        WebElement sendMessage = driver.findElement(By.id("SendMessage"));
        sendMessage.click();

        TimeUnit.SECONDS.sleep(6);
        type.sendKeys("Can i know more details about Acorn Applications?");
        WebElement sendMessage2 = driver.findElement(By.id("SendMessage"));
        sendMessage2.click();
        TimeUnit.SECONDS.sleep(6);
        type.sendKeys(" I still have some questions about Acorn Applications , can we move to call ? It maybe more helpful  ");
        WebElement sendMessage3 = driver.findElement(By.id("SendMessage"));
        sendMessage3.click();

        TimeUnit.SECONDS.sleep(6);
        WebElement MoveToCall = driver.findElement(By.id("moveToCall"));
        MoveToCall.click();

        //Call Page
        TimeUnit.SECONDS.sleep(30);
        WebElement EndCall = driver.findElement(By.id("endcall"));
        EndCall.click();

        //Feedback page
        // Rating1
        TimeUnit.SECONDS.sleep(1);
        WebElement Click1 = driver.findElement(By.xpath("//*[@id=\"rating1\"]/button[4]"));
        Click1.click();

        // Rating2
        //TimeUnit.SECONDS.sleep(1);
        WebElement Click2 = driver.findElement(By.xpath("//*[@id=\"rating2\"]/button[3]"));
        //WebElement FirstName = driver.findElement(By.xpath("//*[@id=\"FirstName\"]"));
        //WebElement FirstName = driver.findElement(By.id("FirstName"));
        Click2.click();

        // Rating3
        TimeUnit.SECONDS.sleep(1);
        WebElement Click3 = driver.findElement(By.xpath("//*[@id=\"rating3\"]/button[1]"));
        Click3.click();

        TimeUnit.SECONDS.sleep(1);
        WebElement Comments = driver.findElement(By.xpath("//*[@id=\"comments\"]"));
        Comments.sendKeys("NA");

        TimeUnit.SECONDS.sleep(1);
        WebElement Email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        Email.sendKeys("chen_yan@mymail.sutd.edu.sg");



        WebElement exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(5);

        driver.close();
    }
}
