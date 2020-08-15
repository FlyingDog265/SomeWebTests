package settings.driver;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class WebDriverFactory {

    /**
     * @param start - если вернется "true" запуск тестов пойдет через Selenoid
     * @param hub   - хаб ссылка
     */
    public static void setUpDriver(String start, String hub) {
        if ("true".equals(start)) setRemoteBrowser(hub);
        else setLocalBrowser();
    }

    private static void setLocalBrowser() {
        WebDriverManager.chromedriver().proxy("proxy.dellin.ru:3128").version("84");
        baseSetUp();
        Configuration.startMaximized = true;
    }

    private static void setRemoteBrowser(String hub) {
        baseSetUp();
        Configuration.remote = hub;
        Configuration.browserSize = "1920x1080";
    }

    private static void baseSetUp() {
        String userAgentSettings = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like " +
                "Gecko) Chrome/80.0.3987.132 Safari/537.36";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("acceptInsecureCerts", true);
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-logging");

        chromeOptions.addArguments("user-agent=DellinSelenium " + userAgentSettings);
        chromeOptions.setCapability("acceptInsecureCerts", true);
        chromeOptions.addExtensions(new File("src/test/java/settings/utils/block_ntlm.crx"));

        Configuration.browser = "chrome";
        Configuration.browserCapabilities = capabilities;
        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    }

}
