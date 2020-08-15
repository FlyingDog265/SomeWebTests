package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;

public class LocationHelper {

    public static void setLocation(Location location) {
        Selenide.open();
        WebDriver driver = WebDriverRunner.getWebDriver();
        ((LocationContext) driver).setLocation(location);
        WebDriverRunner.setWebDriver(driver);
        Selenide.open();
    }
}