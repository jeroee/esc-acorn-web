package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class InappropriateChat {
    WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        InappropriateChat func = new InappropriateChat();
        func.accessPage();
        func.AccessChat();
        TimeUnit.SECONDS.sleep(1);
        func.reCaptcha();
        TimeUnit.SECONDS.sleep(15);
        //For spam message testing
        func.ChatOverload();
        func.acceptAlert();
        TimeUnit.SECONDS.sleep(5);
        func.AccessChat();
        TimeUnit.SECONDS.sleep(1);
        func.reCaptcha();
        TimeUnit.SECONDS.sleep(15);
        //For long message testing
        func.LongMessage();
        func.acceptAlert();
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
        //WebElement choice = driver.findElement(By.xpath(Generators.catchoice()));
        TimeUnit.SECONDS.sleep(1);
        choice.click();
        TimeUnit.SECONDS.sleep(1);
        chat.click();
        System.out.println("Accessing Chats");

    }

    public void ChatOverload() throws InterruptedException {
        System.out.println("Begining Spam Message Test");
        int number = 0;
        while (number<21) {
            WebElement type = driver.findElement(By.id("WriteMessage"));
            type.sendKeys("John Doe" + number);
            //TimeUnit.SECONDS.sleep(1);
            WebElement sendMessage = driver.findElement(By.id("SendMessage"));
            sendMessage.click();
            //TimeUnit.SECONDS.sleep(1);
            number+=1;
        }
    }
    public void LongMessage(){
        System.out.println("Beginning Long Message Test");
        WebElement type = driver.findElement(By.id("WriteMessage"));
        System.out.println(Generators.LongMesage());
        type.sendKeys(Generators.LongMesage());
        WebElement sendMessage = driver.findElement(By.id("SendMessage"));
        sendMessage.click();
    }
    public void acceptAlert() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        driver.switchTo().alert().accept();
    }
    public void close(){
        driver.close();
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
