import org.apache.commons.io.FileUtils;
import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageCompareTest {

    protected WebDriver driver;

    protected void takeScreenshot(WebElement element, String path) throws Exception {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        BufferedImage image = ImageIO.read(scrFile);
        if(element != null) {
            Point point = element.getLocation();
            BufferedImage elementImage = image.getSubimage(
                    point.getX(), point.getY(),
                    element.getSize().getWidth(), element.getSize().getHeight()
            );
            ImageIO.write(elementImage, "png", scrFile);
        }
        FileUtils.copyFile(scrFile, new File(path));
    }

    protected void compareImages (String exp, String cur, String diff) {
        // This instance wraps the compare command
        File expFile = new File(exp);
        File curFile = new File(cur);
        File diffFile = new File(diff);

        CompareCmd compare = new CompareCmd();

        // For metric-output
        compare.setErrorConsumer(StandardStream.STDERR);
        IMOperation cmpOp = new IMOperation();
        // Set the compare metric
        cmpOp.metric("mae");

        // Add the expected image
        cmpOp.addImage(expFile.getAbsolutePath());

        // Add the current image
        cmpOp.addImage(curFile.getAbsolutePath());

        // This stores the difference
        cmpOp.addImage(diffFile.getAbsolutePath());

        try {
            // Do the compare
            compare.run(cmpOp);
        }
        catch (Exception ex) {
            throw new RuntimeException("Images are not the same. See diff image " + diff);
        }
    }

    protected void hideElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].style.visibility='hidden'", element);
    }

}
