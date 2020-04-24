package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class CallJourney {
    WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        CallJourney func = new CallJourney();
        func.accessPage();
        func.accessCall();

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
        WebElement call = driver.findElement(By.id("Call"));
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
        TimeUnit.SECONDS.sleep(1);
        call.click();
        System.out.println("Accessing Chats");

    }



}
