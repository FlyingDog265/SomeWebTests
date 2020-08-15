package pages.homepage;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageelements.Link;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.WebDriverRunner.url;
import static helpers.NavigatorHelper.switchLastTab;

/**
 * =================================================================================================================
 * Блок с контентом на главной странице
 * =================================================================================================================
 */
@SuppressWarnings("UnusedReturnValue")
public class CustomContentBlock extends HomePage {

    @Step("Клик на ссылку в блоке 'Задать другой вопрос' и проверка осуществления перехода")
    public CustomContentBlock clickLinkAskQuestion(String linkText, String url) {
        linkBlockAskQuestion
                .find(text(linkText))
                .click();
        switchLastTab();
        Assert.assertTrue(url().contains(url), "Не осуществлен переход на страницу");
        return this;
    }

    @Step("Клик на ссылку в меню, в блоке контента")
    public CustomContentBlock clickLinkListMenu(String linkText) {
        linkListMenu
                .find(text(linkText))
                .click();
        return this;
    }

    @Step("Клик на ссылку форма расчет заказа и переключение на нее")
    public CustomContentBlock clickLinkOrderCalculationForm() {
        linkOrderCalculationForm.waitClickable()
                .click();
        switchLastTab();
        return this;
    }


    /**
     * =================================================================================================================
     * Elements
     * =================================================================================================================
     */

    // Лист ссылок после кнопки Рассчитать
    private final ElementsCollection linkListMenu = new Link(
            "[class^=MenuElement]"
    ).getElements();

    // Ссылки в блоке Задать другой вопрос
    private final ElementsCollection linkBlockAskQuestion = new Link(
            "[class^=IconsBlock__StyledIconsBlock] [class^=Item-]"
    ).getElements();
    // Форма расчет заказа
    private final Link linkOrderCalculationForm = new Link(
            By.linkText("формы расчета-заказа")
    );

}
