import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ImageTestingTest extends ImageCompareTest {

    @Test
    public void testThisTest() throws Exception {
        driver = new FirefoxDriver();
        driver.get("http://www.idc.com/getdoc.jsp?containerId=US41259417");
        Thread.sleep(5000);
        WebElement presentation = driver.findElement(By.id("pdfPresentationDiv"));
        WebElement analyst = driver.findElement(By.className("analystlink"));
        hideElement(analyst);
        // Used for generating target image
        //takeScreenshot(presentation, "testPresentation.png");

        // Generate comparison
        takeScreenshot(presentation, "testPresentation-try.png");
        compareImages("testPresentation.png", "testPresentation-try.png", "testPresentation-diff.png");
        driver.quit();
    }

}
