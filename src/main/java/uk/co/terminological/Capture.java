package uk.co.terminological;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import javax.imageio.ImageIO;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

/**
 * Hello world!
 *
 */
public class Capture 
{
    public static void main( String[] args ) throws IOException {
    	takeScreenShot(
    			URI.create(args[0]),
    			new File(args[1])
    			);
    	
    }
    
    public static void takeScreenShot(URI url, File out) throws IOException {
    	//System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

    	ChromeDriver driver = new ChromeDriver();
    	
    	//driver.manage().window().setPosition(new Point(0,0));
    	driver.manage().window().setSize(new Dimension(1280,1280));
    	//driver.manage().window().fullscreen();
    	//driver.manage().window().maximize();
    	driver.executeScript("document.body.style.zoom=0.4");
    	
    	driver.get(url.toString());
    	
    	Screenshot ss = new AShot()
    	  .shootingStrategy(ShootingStrategies.viewportPasting(100))
    	  .takeScreenshot(driver);
    	
    	
    	
    	if (out.exists()) out.delete();
    	out.getCanonicalFile().getParentFile().mkdirs();
    	
    	ImageIO.write(ss.getImage(), "PNG", out);
    	
    	driver.quit();
    	System.exit(0);
    }
    
}
