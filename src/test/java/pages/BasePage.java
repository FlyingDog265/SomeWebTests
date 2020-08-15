package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import settings.utils.PropertyLoader;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

// Базовая страница с основными методами
public abstract class BasePage {

    // Базовый url
    protected static final String BASE_URL = PropertyLoader.loadProperty("site.url");
    // url material
    protected static final String MATERIAL_URL = new File("src/test/resources/material").getAbsolutePath() + "/";

    // Селектор заголовка страницы
    protected SelenideElement header = $("body h1");

    // Метод получения заголовка текущей страницы
    public String getHeader() {
        return header.getText();
    }

    // Селектор страницы (тело страницы)
    public SelenideElement page = $("body");

    // Абстрактный метод открытия страницы по url
    public BasePage openPage() {
        Selenide.open(BASE_URL);
        return this;
    }

    // Абстрактный метод проверки заголовка страницы
    public abstract BasePage checkHeader();

    /**
     * переключиться на алерт
     */
    public static boolean isAlertPresent() {
        try {
            Configuration.timeout = 3_000;
            Selenide.switchTo().alert();
            return true;
        } catch (Exception Ex) {
            return false;
        } finally {
            Configuration.timeout = 10_000;
        }
    }

    /**
     * принять алерт
     */
    public void alertAccept() {
        if (isAlertPresent()) {
            confirm();
        }
    }

    public static boolean isElementPresent(By by) {
        Configuration.timeout = 0;
        ElementsCollection list = $$(by);
        Configuration.timeout = 3000;
        if (list.size() == 0) {
            return false;
        } else {
            return list.get(0).isDisplayed();
        }
    }

}
