import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestCase1 {
    private WebDriver driver;

    @BeforeTest
    public void setUpDriver() {
        System.setProperty("web-driver.chrome.driver", "C:\\IdeaProjects\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void openWebSite(){
        driver.get("https://www.saucedemo.com/");
        WebElement form = driver.findElement(By.cssSelector(".form_column"));
        form.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        form.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        form.findElement(By.cssSelector(".submit-button ")).click();
    }

    @Test
    public void shouldDisplayProductsHeader() {
        WebElement productsTitle = driver.findElement(By.cssSelector(".title"));
        Assert.assertTrue(productsTitle.isDisplayed());
    }

    @Test
    public void shouldDisplayShoppingCard(){
        WebElement shoppingCart = driver.findElement(By.cssSelector(".shopping_cart_link"));
        Assert.assertTrue(shoppingCart.isDisplayed());
    }

    @Test
    public void shouldDisplayBurgerMenu(){
        WebElement burgerMenu = driver.findElement(By.cssSelector("#react-burger-menu-btn"));
        Assert.assertTrue(burgerMenu.isDisplayed());
    }

    @Test
    public void shouldDisplayTwitterLink(){
        WebElement twitterLink = driver.findElement(By.xpath("//a[.='Twitter']"));
        Assert.assertTrue(twitterLink.isDisplayed());
    }

    @Test
    public void shouldDisplayFacebookLink(){
        WebElement fbLink = driver.findElement(By.xpath("//a[.='Facebook']"));
        Assert.assertTrue(fbLink.isDisplayed());
    }

    @Test
    public void shouldDisplayLinkedInLink(){
        WebElement LILink = driver.findElement(By.xpath("//a[.='LinkedIn']"));
        Assert.assertTrue(LILink.isDisplayed());
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
