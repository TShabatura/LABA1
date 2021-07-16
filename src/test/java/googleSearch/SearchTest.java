package googleSearch;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SearchTest {
    private WebDriver driver;

    @BeforeTest
    public void profileSetUp(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @BeforeMethod
    public void testSetUP(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }

    @Test
    public void checkImageTabContainsImage() throws IOException {
        driver.findElement(By.xpath("//input[@title = 'Пошук']")).sendKeys("image", Keys.ENTER);
        driver.findElement(By.xpath("//a[contains(text(), 'Зображення')]")).click();
        List<WebElement> list = driver.findElements(By.xpath("//img"));
        File screenshot = ((TakesScreenshot) driver).
                getScreenshotAs(OutputType.FILE);
        String path = "./target/screenshots/" + screenshot.getName();
        FileUtils.copyFile(screenshot, new File(path));
        Assert.assertTrue(list != null);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
