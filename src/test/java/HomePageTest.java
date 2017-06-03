import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class HomePageTest extends ImageCompareTest {

    @Test
    public void testThisTest() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://www.idc.com");

        driver.findElement(By.id("notice-agree")).click();

        WebElement webpage = null;
        WebElement analyst = driver.findElement(By.id("AnalystBio_Container"));
        hideElement(analyst);

        WebElement message = driver.findElement(By.id("idc-message"));
        hideElement(message);

        WebElement explore = driver.findElement(By.id("accordion"));
        hideElement(explore);

        WebElement smallBanner = driver.findElement(By.className("SmallBanner"));
        hideElement(smallBanner);

        WebElement mainBanner = driver.findElement(By.className("MainBanner"));
        hideElement(mainBanner);

        WebElement mainTabs = driver.findElement(By.className("main-tabs"));
        // This will generate error, this was not commented out on the tested screenshot
//        hideElement(mainTabs);

        // Used for generating target image
        //takeScreenshot(webpage, "testPage.png");

        // Generate comparison
        takeScreenshot(webpage, "testPage-try.png");
        compareImages("testPage.png", "testPage-try.png", "testPage-diff.png");
        driver.quit();
    }

}
