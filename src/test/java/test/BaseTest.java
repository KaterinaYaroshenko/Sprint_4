package test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {

        driver.quit();
    }
}