package pages.homepage;

import io.qameta.allure.Step;
import pageelements.Button;
import pageelements.Link;
import pageelements.TextElement;
import pageelements.TextField;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * =================================================================================================================
 * Блок "Баннеры" на главной странице
 * =================================================================================================================
 */
@SuppressWarnings("UnusedReturnValue")
public class BannersBlock extends HomePage {

    @Step("Поиск заказа по номеру: {0}")
    public BannersBlock searchOrder(String value) {
        fieldSearch.setText(value);
        buttonSearch.waitClickable().click();
        return BannersBlock.this;
    }

    @Step("Ввод в поле Поиск заказа")
    public BannersBlock fillSearchOrder(String value) {
        fieldSearch.setText(value);
        return BannersBlock.this;
    }

    @Step("Проверка введенного значение в поле Поиск заказа")
    public BannersBlock checkSearchOrder(String value) {
        fieldSearch.checkValue(value);
        return this;
    }

    @Step("Повторный поиск заказа по номеру {0}")
    public BannersBlock searchOrderRepeated(String value) {
        fieldSearch
                .waitClickable()
                .setText(value);
        buttonSearch
                .waitClickable()
                .click();
        return BannersBlock.this;
    }

    @Step("Клик на кнопку поиска заказа")
    public BannersBlock clickSearchButton() {
        buttonSearch
                .waitClickable()
                .click();
        return BannersBlock.this;
    }

    @Step("Проверка отображения Элементов блока 'Где мой груз?'")
    public BannersBlock checkElementsBlockSearch() {
        assertTrue(buttonSearch.isDisplayed(), "Кнопка поиска не отображается");
        assertTrue(titleWhereIsMyCargo.isDisplayed(), "Заголовок 'Где мой груз?' не отображается");
        assertEquals(titleWhereIsMyCargo.getText(), "Где мой груз?", "Заголовок блока содержит неверный текст");
        assertTrue(fieldSearch.isDisplayed(), "Поле поиска 'Номер заказа' не отображается");
        assertTrue(linkHowFindOrder.isDisplayed(), "Ссылка 'Как найти номер заказа?' не отображается");
        assertEquals(linkHowFindOrder.getText(), "Как найти номер заказа?", "Ссылка на расширенный трекер содержит неверный текст");
        return this;
    }

    @Step("Кликнуть по ссылке 'Как найти номер заказа?'")
    public BannersBlock clickLinkHowFindOrder() {
        linkHowFindOrder
                .waitClickable()
                .click();
        return BannersBlock.this;
    }

    @Step("Кликнуть по иконке поиска")
    public BannersBlock clickButtonSearch() {
        buttonSearch
                .waitClickable()
                .click();
        return BannersBlock.this;
    }

    @Step("Ввести номер заказа в поле поиска")
    public BannersBlock fillFieldSearch(String number) {
        fieldSearch.setText(number);
        return BannersBlock.this;
    }

    /**
     * =================================================================================================================
     * Elements
     * =================================================================================================================
     */


    // Поле "Найти груз"
    private final TextField fieldSearch = new TextField(
            "div[class^=\"HomeFindCargo\"] input[type=\"text\"]"
    );

    // Кнопка "Поиск" (лупа)
    private final Button buttonSearch = new Button(
            "div[class^=\"HomeFindCargo\"] button[type=\"submit\"]"
    );

    // Ссылка "Как найти номер заказа?"
    private final Link linkHowFindOrder = new Link(
            "div[class^=\"HomeFindCargo\"] a"
    );

    // Название блока "Где мой груз?"
    private final TextElement titleWhereIsMyCargo = new TextElement(
            "div[class^=\"HomeFindCargo\"] h2"
    );
}
