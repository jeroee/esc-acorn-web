package com.company;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class InputValidationFeedbackPage {
    WebDriver driver;
    public static void main(String[] args) throws InterruptedException {
        InputValidationFeedbackPage func = new InputValidationFeedbackPage();
        func.accessPage();
        func.accessChat();
        TimeUnit.SECONDS.sleep(1);
        func.reCaptcha();
        func.accessFeedback();
        func.feedbackBackButton();
        func.submitWithoutAnsweringFeedback();
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
    public void accessChat() throws InterruptedException {
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
    public void accessFeedback() throws InterruptedException {
        TimeUnit.SECONDS.sleep(20);
        System.out.println("Leaving Chats");
        WebElement exitChat = driver.findElement(By.id("exitChat"));
        exitChat.click();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Accessing Feedback");
    }

    public void feedbackBackButton() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Testing for Back Button");
        driver.navigate().back();
        TimeUnit.SECONDS.sleep(1);
        driver.switchTo().alert().accept();
        TimeUnit.SECONDS.sleep(3);
    }
    public void submitWithoutAnsweringFeedback() throws InterruptedException {
        System.out.println("Robust Testing for Feedback Form");
        //submit with no qns answered
        System.out.println("Test 1: Submit form with no ratings filled");
        WebElement exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(3);

        //submit with no qns answered but with comments only
        System.out.println("Test 2: Submit form with no ratings filled but with comments only");
        WebElement comments = driver.findElement(By.xpath("//*[@id=\"comments\"]"));
        comments.sendKeys("commenting");
        exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(3);

        //submit with no qns answered but with email only
        System.out.println("Test 3: Submit form with no ratings filled but with valid email only");
        comments = driver.findElement(By.xpath("//*[@id=\"comments\"]"));
        comments.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        WebElement email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        email.sendKeys("JohnDoe@gmail.com");
        exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(3);

        //submit with no qns answered but with comment and email
        System.out.println("Test 4: Submit form with no ratings filled but with valid email and comment");
        comments = driver.findElement(By.xpath("//*[@id=\"comments\"]"));
        email = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        comments.sendKeys("commenting");
        email.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        email.sendKeys("JohnDoe@gmail.com");
        exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(3);

        //with qn 1 answered
        System.out.println("Test 4: Submit form with 1 rating filled but with valid email and comment");
        Random r = new Random();
        int test1 = r.nextInt(5) + 1;
        WebElement Click1 = driver.findElement(By.xpath("//*[@id=\"rating1\"]/button["+ test1 +"]"));
        System.out.println(test1 +"/5 is selcted for Rating 1");
        Click1.click();
        comments.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        comments.sendKeys("commenting");
        email.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        email.sendKeys("JohnDoe@gmail.com");
        exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(3);

        //with qn 1 and 2 answered
        System.out.println("Test 4: Submit form with 2 rating filled but with valid email and comment");
        int test2rating2 = r.nextInt(5) + 1;
        WebElement Click2 = driver.findElement(By.xpath("//*[@id=\"rating2\"]/button["+ test2rating2 +"]"));
        System.out.println(test2rating2 +"/5 is selcted for Rating 2");
        Click2.click();
        comments.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        comments.sendKeys("commenting");
        email.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        email.sendKeys("JohnDoe@gmail.com");
        exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(3);

        //with all qns answered but with extremely long comments
        //int test3rating3 = r.nextInt(5) + 1;
//        WebElement Click3 = driver.findElement(By.xpath("//*[@id=\"rating3\"]/button["+ test3rating3 +"]"));
//        System.out.println(test3rating3 +"/5 is selcted for Rating 2");
//        Click3.click();
//        comments.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
//        comments.sendKeys(Generators.LongMesage());
//        email.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
//        email.sendKeys("JohnDoe@gmail.com");
//        exitReview = driver.findElement(By.id("Submit"));
//        exitReview.click();
//        TimeUnit.SECONDS.sleep(3);

        //with all qns answered with suitable comments but with no email
        System.out.println("Test 5: Submit form with all ratings filled with comment but no email");
        int test3rating3 = r.nextInt(5) + 1;
        WebElement Click3 = driver.findElement(By.xpath("//*[@id=\"rating3\"]/button["+ test3rating3 +"]"));
        System.out.println(test3rating3 +"/5 is selcted for Rating 3");
        Click3.click();
        comments.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        comments.sendKeys("commenting");
        email.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        //email.sendKeys("invalidEmail");
        exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(3);

        //with all ratings answered with email but no comments
        System.out.println("Test 6: Submit form with all ratings filled with valid email but no comments");
        comments.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        //comments.sendKeys("commenting");
        email.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        email.sendKeys("JohnDoe@gmail.com");
        exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(3);

        //with all ratings filled with comments but invalid email
        System.out.println("Test 7: Submit form with all ratings filled with comment and INVALID email");
        comments.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        comments.sendKeys("commenting");
        email.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        email.sendKeys("$^@*&#!(*&@");
        exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
        TimeUnit.SECONDS.sleep(3);

        //with all ratings filled with comments and valid email
        System.out.println("Test 5: Submit form with all ratings filled with comment and valid email");
        comments.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        comments.sendKeys("commenting");
        email.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        email.sendKeys("JohnDoe@gmail.com");
        exitReview = driver.findElement(By.id("Submit"));
        exitReview.click();
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
