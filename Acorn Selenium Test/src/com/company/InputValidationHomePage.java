package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class InputValidationHomePage {
    WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        InputValidationHomePage func = new InputValidationHomePage();
        func.accessPage();
        System.out.println("Test 1: Accessing without first name, last name or Category Selection");
        func.noFNnoLNnoCat();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Test 2: Accessing with Category Selection but without first name or last name");
        func.noFNnoLNCat();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Test 3: Accessing with Category Selection and first name but without last name");
        func.FNnoLNCat();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Test 4: Accessing with Category Selection and last name but without first name");
        func.noFNLNCat();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Test 5: Accessing with Category Selection, first name not valid");
        func.wrongFNLNCAT();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Test 6: Accessing with Category Selection, last name not valid");
        func.FNwrongLNCAT();
        TimeUnit.SECONDS.sleep(1);

        System.out.println("Test 7: Accessing with Category Selection, Valid First name and Valid Last name");
        func.FNLNCat();
        TimeUnit.SECONDS.sleep(3);
        func.reCaptcha();
        TimeUnit.SECONDS.sleep(3);
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
    //Test 1
    public void noFNnoLNnoCat(){
        WebElement chat1 = driver.findElement(By.id("Chat"));
        if (!chat1.isEnabled()){
            System.out.println("First Name and Last Name not filled, Category not selected, Cannot Access");
        }
        else{
            chat1.click();
        }
    }
    //Test 2
    public void noFNnoLNCat() throws InterruptedException {
        WebElement chat2 = driver.findElement(By.id("Chat"));
        WebElement category2 = driver.findElement(By.xpath("//*[@class='v-select__selections']"));
        category2.click();
        TimeUnit.SECONDS.sleep(1);
        WebElement choice2 = driver.findElement((By.xpath("//*[@class='v-list-item__title'] [text()='Acorn Applications']")));
        TimeUnit.SECONDS.sleep(1);
        choice2.click();
        if (!chat2.isEnabled()){
            System.out.println("First Name and Last Name not filled, Category selected, Cannot Access");
        }
        else{
            chat2.click();
        }
    }
    //Test 3
    public void FNnoLNCat() throws InterruptedException {
        WebElement name3a = driver.findElement(By.id("FirstName"));
        WebElement name3b = driver.findElement(By.id("LastName"));
        WebElement chat3 = driver.findElement(By.id("Chat"));
        name3a.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        TimeUnit.SECONDS.sleep(1);
        name3a.sendKeys("John");
        if (!chat3.isEnabled()){
            System.out.println("Last Name not filled, Category selected, Cannot Access");
        }
        else{
            chat3.click();
        }

    }
    //Test 4
    public void noFNLNCat() throws InterruptedException {
        WebElement chat4 = driver.findElement(By.id("Chat"));
        WebElement name4a = driver.findElement(By.id("FirstName"));
        WebElement name4b = driver.findElement(By.id("LastName"));
        TimeUnit.SECONDS.sleep(1);
        name4a.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        TimeUnit.SECONDS.sleep(1);
        name4b.sendKeys("Doe");
        TimeUnit.SECONDS.sleep(1);
        if (!chat4.isEnabled()){
            System.out.println("First Name not filled, Category selected, Cannot Access");
        }
        else{
            chat4.click();
        }

    }
    //Test 5
    public void wrongFNLNCAT() throws InterruptedException{
        WebElement chat8 = driver.findElement(By.id("Chat"));
        WebElement name8a = driver.findElement(By.id("FirstName"));
        WebElement name8b = driver.findElement(By.id("LastName"));
        name8a.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        name8b.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        TimeUnit.SECONDS.sleep(1);
        name8a.sendKeys("John#$%");
        name8b.sendKeys("Doe");
        TimeUnit.SECONDS.sleep(1);

        if (!chat8.isEnabled()){
            System.out.println("Wrong First Name Filled, Category selected, Cannot Access");
        }
        else{
            chat8.click();
        }
    }
    //Test 6
    public void FNwrongLNCAT() throws InterruptedException{
        WebElement chat9 = driver.findElement(By.id("Chat"));
        WebElement name9a = driver.findElement(By.id("FirstName"));
        WebElement name9b = driver.findElement(By.id("LastName"));
        name9a.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        name9b.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        TimeUnit.SECONDS.sleep(1);
        name9a.sendKeys("John");
        name9b.sendKeys("Doe!@#$%");
        TimeUnit.SECONDS.sleep(1);

        if (!chat9.isEnabled()){
            System.out.println("Wrong Last Name Filled, Category selected, Cannot Access");
        }
        else{
            chat9.click();
        }

    }
    //Test 7
    public void FNLNCat() throws InterruptedException {
        WebElement chat7 = driver.findElement(By.id("Chat"));
        WebElement name7a = driver.findElement(By.id("FirstName"));
        WebElement name7b = driver.findElement(By.id("LastName"));
        name7a.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        name7b.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        TimeUnit.SECONDS.sleep(1);
        name7a.sendKeys("John");
        name7b.sendKeys("Doe");
        TimeUnit.SECONDS.sleep(1);
        if (!chat7.isEnabled()){
            System.out.println("Wrong First Name Filled, Category selected, Cannot Access");
        }
        else{
            System.out.println("Access Granted, accessing chat now!");
            chat7.click();
        }
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
