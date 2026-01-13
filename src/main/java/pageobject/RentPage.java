package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;


public class RentPage {
    private final WebDriver driver;

    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By periodField = By.className("Dropdown-control");
    private final By orderButton = By.xpath(".//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By successOrderHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillRentForm(String date, String period) {

        driver.findElement(dateField).sendKeys(date, Keys.ENTER);

        driver.findElement(periodField).click();

        By periodOption = By.xpath(".//div[@class='Dropdown-menu']/div[text()='" + period + "']");
        driver.findElement(periodOption).click();

        driver.findElement(orderButton).click();
    }

    public void confirmOrder() {

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }

    public boolean isSuccessHeaderDisplayed() {

        WebElement header = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(successOrderHeader));

        return header.getText().contains("Заказ оформлен");
    }
}
