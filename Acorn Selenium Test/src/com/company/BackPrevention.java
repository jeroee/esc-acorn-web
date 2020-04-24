package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class BackPrevention {
    WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        BackPrevention func = new BackPrevention();
        func.accessPage();
        func.accessChat();
        //func.accessCall();
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
    public void accessCall() throws InterruptedException {
        WebElement chat = driver.findElement(By.id("Call"));
        WebElement namea = driver.findElement(By.id("FirstName"));
        WebElement nameb = driver.findElement(By.id("LastName"));
        WebElement category = driver.findElement(By.xpath("//*[@class='v-select__selections']"));
        String firstname = "John";
        String lastname = "Doe";
        namea.sendKeys(firstname);
        nameb.sendKeys(lastname);
        category.click();
        WebElement choice = driver.findElement((By.xpath("//*[@class='v-list-item__title'] [text()='Acorn Products']")));
        //WebElement choice = driver.findElement(By.xpath(Generators.catchoice()));
        TimeUnit.SECONDS.sleep(1);
        choice.click();
        chat.click();
        System.out.println("Accessing Chats");
        navigateBackByURL();

    }

    public void accessChat() throws InterruptedException {
        WebElement chat = driver.findElement(By.id("Chat"));
        WebElement namea = driver.findElement(By.id("FirstName"));
        WebElement nameb = driver.findElement(By.id("LastName"));
        WebElement category = driver.findElement(By.xpath("//*[@class='v-select__selections']"));
        String firstname = "John";
        String lastname = "Doe";
        namea.sendKeys(firstname);
        nameb.sendKeys(lastname);
        TimeUnit.SECONDS.sleep(1);
        category.click();
        WebElement choice = driver.findElement((By.xpath("//*[@class='v-list-item__title'] [text()='Acorn Applications']")));
        //WebElement choice = driver.findElement(By.xpath(Generators.catchoice()));
        TimeUnit.SECONDS.sleep(1);
        choice.click();
        TimeUnit.SECONDS.sleep(1);
        chat.click();
        System.out.println("Accessing Chats");
        TimeUnit.SECONDS.sleep(1);
        reCaptcha();
        navigateBackByURL();
        TimeUnit.SECONDS.sleep(5);
        driver.close();
    }
    public void navigateBackByURL() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        driver.navigate().back();
        TimeUnit.SECONDS.sleep(1);
        driver.switchTo().alert().accept();
        TimeUnit.SECONDS.sleep(15);
        driver.navigate().back();
        TimeUnit.SECONDS.sleep(1);
        driver.switchTo().alert().accept();
    }
    public void reCaptcha() throws InterruptedException {
        WebElement reCaptchaText = driver.findElement(By.id("recaptcha"));
        TimeUnit.SECONDS.sleep(1);
        reCaptchaText.sendKeys("yes");
        TimeUnit.SECONDS.sleep(1);
        WebElement reCaptchaBtn = driver.findElement(By.id("recaptchaCheck"));
        reCaptchaBtn.click();
    }

}
