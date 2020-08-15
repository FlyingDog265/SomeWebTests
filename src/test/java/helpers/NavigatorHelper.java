package helpers;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class NavigatorHelper {

    public static void switchLastTab() {
        for (String winHandle : getWebDriver().getWindowHandles()) {
            getWebDriver().switchTo().window(winHandle);
        }
    }

    public static void switchToTab(int tab) {
        List<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles());
        getWebDriver().switchTo().window(tabs.get(tab));
    }

}
