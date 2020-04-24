package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class DirectURLAccess {
    WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        DirectURLAccess func = new DirectURLAccess();
        func.accessPage();
        func.AccessingChatPageByURL();
        TimeUnit.SECONDS.sleep(2);
        func.AccessingCallPageByURL();
        TimeUnit.SECONDS.sleep(2);
        func.AccessChat();
        TimeUnit.SECONDS.sleep(1);
        func.reCaptcha();
        TimeUnit.SECONDS.sleep(15);
        func.AccessingFeedbackPageByURL();
        TimeUnit.SECONDS.sleep(5);
        func.close();
    }
    public void accessPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        String link = "https://esc-frontend.netlify.app/";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--use-fake-ui-for-media-stream");
        driver = new ChromeDriver(chromeOptions);
        driver.get(link);
        TimeUnit.SECONDS.sleep(3);
    }

    public void AccessingChatPageByURL() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        String chatlink = "https://esc-frontend.netlify.app/chat";
        driver.get(chatlink);
        TimeUnit.SECONDS.sleep(3);
        driver.switchTo().alert().accept();
    }
    public void AccessingCallPageByURL() throws InterruptedException{
        TimeUnit.SECONDS.sleep(2);
        String calllink = "https://esc-frontend.netlify.app/call";
        driver.navigate().to(calllink);
        TimeUnit.SECONDS.sleep(3);
        driver.switchTo().alert().accept();
    }
    public void AccessingFeedbackPageByURL() throws InterruptedException{
        TimeUnit.SECONDS.sleep(2);
        String feedbacklink = "https://esc-frontend.netlify.app/feedback";
        driver.navigate().to(feedbacklink);
        TimeUnit.SECONDS.sleep(3);
        driver.switchTo().alert().accept();
    }
    public void AccessChat() throws InterruptedException {
        WebElement chat = driver.findElement(By.id("Chat"));
        WebElement namea = driver.findElement(By.id("FirstName"));
        WebElement nameb = driver.findElement(By.id("LastName"));
        WebElement category = driver.findElement(By.xpath("//*[@class='v-select__selections']"));
        String firstname = "John";
        String lastname = "Doe";
        namea.sendKeys(firstname);
        nameb.sendKeys(lastname);
        category.click();
        WebElement choice = driver.findElement((By.xpath("//*[@class='v-list-item__title'] [text()='Acorn Applications']")));
        TimeUnit.SECONDS.sleep(1);
        choice.click();
        chat.click();
        System.out.println("Accessing Chats");
    }
    public void reCaptcha() throws InterruptedException {
        WebElement reCaptchaText = driver.findElement(By.id("recaptcha"));
        TimeUnit.SECONDS.sleep(1);
        reCaptchaText.sendKeys("yes");
        TimeUnit.SECONDS.sleep(1);
        WebElement reCaptchaBtn = driver.findElement(By.id("recaptchaCheck"));
        reCaptchaBtn.click();
    }
    public void close(){
        driver.close();
    }
}
