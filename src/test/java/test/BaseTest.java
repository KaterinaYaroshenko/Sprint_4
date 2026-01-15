package test;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import pageobject.MainPage;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        driver.get(MainPage.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}