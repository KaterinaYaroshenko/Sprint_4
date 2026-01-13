package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    // Верхняя кнопка "Заказать"
    private final By topOrderButton = By.xpath(".//div[contains(@class, 'Header_Nav')]//button[contains(@class, 'Button_Button')]");

    // Нижняя кнопка "Заказать"
    private final By bottomOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]//button");

    // Шаблоны ID для FAQ
    private final String questionLocator = "accordion__heading-%d";
    private final String answerLocator = "accordion__panel-%d";

    // Куки
    private By cookieButton = By.id("rcc-confirm-button");

    public void clickCookieButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickTopOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(topOrderButton)).click();
    }

    public void clickBottomOrderButton() {
        WebElement element = driver.findElement(bottomOrderButton);
        // Скролл до кнопки
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        // Клик
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    public void clickQuestion(int index) {
        By question = By.id(String.format(questionLocator, index));
        WebElement element = driver.findElement(question);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public String getAnswerText(int index) {
        By answer = By.id(String.format(answerLocator, index));
        // Ждем появления текста
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(answer))
                .getText();
    }
}