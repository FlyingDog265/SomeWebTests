package pages.mappage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageelements.TextElement;
import pages.BasePage;

import static com.codeborne.selenide.Selenide.elements;
import static com.codeborne.selenide.Selenide.open;

@SuppressWarnings("UnusedReturnValue")
public class TerminalCardPage extends BasePage {
    private static final String PAGE_URL = BASE_URL + "contacts/";

    @Override
    public TerminalCardPage checkHeader() {
        // У окна хидер = наименованию терминала, т.е. всегда разный
        return null;
    }

    @Step("Открыть страницу терминала по ID {ID}")
    public TerminalCardPage openPageID(int ID) {
        open(PAGE_URL + ID + "/");
        return TerminalCardPage.this;
    }

    @Step("Ожидание отображения наименования терминала")
    public TerminalCardPage waitNameOfTerminal() {
        nameOfTerminal
                .waitVisible();
        return TerminalCardPage.this;
    }

    @Step("Проверка отображения списка акций для города")
    public TerminalCardPage waitListOfPromosIsVisible() {
        listOfPromos
                .get(0)
                .waitUntil(Condition.visible, 1000);
        return TerminalCardPage.this;
    }

    @Step("Проверка присутствия акции {name} в блоке акций")
    public TerminalCardPage checkPromoPresence(String name) {
        for (SelenideElement promo : listOfPromos) {
            if (promo.find("a").getText().equals(name)) return TerminalCardPage.this;
        }
        throw new RuntimeException("В блоке акций нет акции: [" + name + "]");
    }

    @Step("Проверка отсутсвия акции {name} в блоке акций")
    public TerminalCardPage checkPromoAbsence(String name) {
        for (SelenideElement promo : listOfPromos) {
            Assert.assertNotEquals(name, promo.find("a").getText(), "В блоке присутствует акция: [" + name + "], а не должна.");
        }
        return TerminalCardPage.this;
    }

    @Step("Проверка присутствия ограничения {name} в блоке с ограничениями")
    public TerminalCardPage checkLimitationPresence(String name) {
        for (SelenideElement item : listOfLimitations) {
            if (item.getText().matches(name)) return TerminalCardPage.this;
        }
        throw new RuntimeException("В блоке c ограничениями нет пункта: [" + name + "]");
    }

    /**
     * =================================================================================================================
     * Методы для карточки терминала
     * =================================================================================================================
     */

    // Заголовок карточки терминала
    private final TextElement nameOfTerminal = new TextElement(
            "[class='contacts__page-title']"
    );

    // Список акций
    private final ElementsCollection listOfPromos = elements(
            By.xpath("//h3[text()[contains(.,'Акции')]]/following-sibling::ul/li")
    );

    // Список новостей
    private final ElementsCollection listOfNews = elements(
            By.xpath("//h3[text()[contains(.,'Новости')]]/following-sibling::ul/li")
    );

    // Список ограничений
    private final ElementsCollection listOfLimitations = elements(
            By.xpath("//h3[text()[contains(.,'Действуют ограничения')]]/following-sibling::p")
    );

}
