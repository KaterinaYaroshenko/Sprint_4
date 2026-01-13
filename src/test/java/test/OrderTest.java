package test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Before;
import org.junit.After;
import pageobject.MainPage;
import pageobject.OrderPage;
import pageobject.RentPage;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String period;
    private final boolean isTopButton;

    public OrderTest(String name, String surname, String address, String metro,
                     String phone, String date, String period, boolean isTopButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.period = period;
        this.isTopButton = isTopButton;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Катя", "Чупрова", "Лужники 1", "Сокольники", "79998887766", "18.01.2026", "сутки", true},
                {"Дима", "Семенов", "Мира 10", "Черкизовская", "89002223355", "25.02.2026", "двое суток", false},
        };
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        MainPage mainPage = new MainPage(driver);
        mainPage.clickCookieButton();
    }

    @Test
    public void checkOrderFlow() {
        MainPage mainPage = new MainPage(driver);
        if (isTopButton) {
            mainPage.clickTopOrderButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        OrderPage orderPage = new OrderPage(driver);
        orderPage.fillFirstForm(name, surname, address, metro, phone);

        RentPage rentPage = new RentPage(driver);
        rentPage.fillRentForm(date, period);
        rentPage.confirmOrder();

        assertTrue("Окно успешного заказа не появилось", rentPage.isSuccessHeaderDisplayed());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}