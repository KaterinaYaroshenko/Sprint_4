package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

public class OrderPage {
    private final WebDriver driver;

    private final By firstNameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFirstForm(String name, String surname, String address, String metro, String phone) {
        driver.findElement(firstNameField).sendKeys(name);
        driver.findElement(lastNameField).sendKeys(surname);
        driver.findElement(addressField).sendKeys(address);

        driver.findElement(metroField).sendKeys(metro, Keys.ARROW_DOWN, Keys.ENTER);

        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
    }
}