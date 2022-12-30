import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase2 {
    private WebDriver driver;

    @BeforeTest
    public void setUpDriver() {
        System.setProperty("web-driver.chrome.driver", "C:\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void openWebSite(){
        driver.get("https://www.saucedemo.com/");
        this.login();
    }

    void login() {
        WebElement form = driver.findElement(By.cssSelector(".form_column"));
        form.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        form.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        form.findElement(By.cssSelector(".submit-button ")).click();
    }

    @Test
    public void shouldDisplayAndVerifyBackpack() {
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).click();

        WebElement itemContainer = driver.findElement(By.cssSelector("#inventory_details_desc_container"));
        WebElement title = itemContainer.findElement(By.xpath("//div[.='Sauce Labs Backpack']"));
        WebElement description = itemContainer.findElement(By.cssSelector(".inventory_details_desc"));
        WebElement price = itemContainer.findElement(By.cssSelector(".inventory_details_price"));

        Assert.assertTrue(title.isDisplayed());
        Assert.assertTrue(description.isDisplayed());
        Assert.assertTrue(price.isDisplayed());
    }

    @Test
    public void shouldAddToCartProduct(){
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.findElement(By.cssSelector((".shopping_cart_link"))).click();
    }

    @Test
    public void shouldAddToCartFirstProductGoBackAndOrderSecondAndOrderBoth(){
        driver.findElement(By.xpath("//div[normalize-space()='Sauce Labs Backpack']")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector((".shopping_cart_link"))).click();
        driver.findElement(By.cssSelector((".inventory_details_back_button"))).click();
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-fleece-jacket")).click();

        driver.findElement(By.cssSelector((".shopping_cart_link"))).click();
        driver.findElement(By.cssSelector("#checkout")).click();

        driver.findElement(By.cssSelector("#first-name")).sendKeys("Ana");
        driver.findElement(By.cssSelector("#last-name")).sendKeys("Babic");
        driver.findElement(By.cssSelector("#postal-code")).sendKeys("34000");
        driver.findElement(By.cssSelector("#continue")).click();

        driver.findElement(By.cssSelector("#finish")).click();
        WebElement succesMessage = driver.findElement(By.cssSelector(".complete-header"));
        Assert.assertTrue(succesMessage.isDisplayed());
    }

    @AfterMethod
    public void logout() {
        driver.findElement(By.cssSelector("#react-burger-menu-btn")).click();
        WebElement logoutButton = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Logout']")));
        logoutButton.click();
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
