package pages.mappage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import pageelements.*;
import pages.BasePage;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.*;

@SuppressWarnings("UnusedReturnValue")
public class MapPage extends BasePage {
    private TerminalCardPopup terminalCardPopup;
    private static final String PAGE_URL = BASE_URL + "contacts/";

    public MapPage() {
        terminalCardPopup = new TerminalCardPopup();
    }

    @Override
    public MapPage openPage() {
        open(PAGE_URL);
        return this;
    }

    @Override
    public MapPage checkHeader() {
        // У страницы нет заголовка
        return null;
    }

    //    Метод открытия страницы с картой с id определенного города. int = 0 открывает карту России.
    public MapPage openPageCity(int city) {
        open(PAGE_URL + "?current=1&city_id=" + city);
        return this;
    }

    @Step("Базовая проверка элементов страницы")
    public MapPage checkBaseElements() {
        assertTrue(fieldInputAddress.isDisplayed(), "Поле ввода адреса не отображается!");
        assertTrue(buttonSearch.isDisplayed(), "Кнопка 'Найти' не отображается!");
        assertTrue(kindOfTerminal.isDisplayed(), "Выпадающий список с типом терминалов не отображается!");
        assertTrue(checkboxHeavyOfLarge.isDisplayed(), "Чекбокс 'Только терминалы деловых линий' не отображается!");
        assertTrue(containerOfAddresses.isDisplayed(), "Список адресов терминалов не отображается!");
        assertTrue(containerOfMap.isDisplayed(), "Карта не отображается!");
        assertTrue(buttonPromo.isDisplayed(), "Кнопка 'Открыть в Яндекс.картах' не отображается!");
        return MapPage.this;
    }

    @Step("Проверка отображения элементов дропдауна с видами терминалов")
    public MapPage checkDropdownItemsIsVisible() {
        itemKindOfTerminal
                .waitVisible();
        itemsKindOfTerminal
                .forEach(SelenideElement::isDisplayed);
        return MapPage.this;
    }

    @Step("Проверка, что в поле дропдауна отображается текст :: {text}")
    public MapPage checkMatchDropdownFieldText(String text) {
        Assert.assertEquals(kindOfTerminal
                        .getText(),
                text, "Текст в поле дропдауна не соответсвует ожидаемому значению!");
        return MapPage.this;
    }

    @Step("Проверка наименования {name} в соответсвии с номером {index}")
    public MapPage checkMatchKindOfTerminals(int index, String name) {
        Assert.assertEquals(itemsKindOfTerminal
                        .get(index)
                        .getText(),
                name, "Название пункта в списке не соответствует ожидаемому");
        return MapPage.this;
    }

    @Step("Проверка флага выбора в соответсвии с номером {index}")
    public MapPage checkFlagKindOfTerminals(int index) {
        Assert.assertEquals(itemsKindOfTerminal
                        .get(index)
                        .pseudo("::after", "content"), "\"\"",
                "Пункт " + index + " в списке не содержит флага выбора");
        return MapPage.this;
    }

    @Step("Выбор чекбокса 'Груз тяжелый или крупный'")
    public MapPage clickCheckboxHeavyOrLarge() {
        checkboxHeavyOfLarge
                .waitClickable()
                .click();
        return MapPage.this;
    }

    @Step("Ожидание отображения списка терминалов")
    public MapPage waitListOfTerminals() {
        containerOfAddresses
                .waitVisible();
        if (titleNoAddress.notExist()) {
            titleAddress
                    .waitVisible();
        }
        return MapPage.this;
    }

    @Step("Ожидание наполнения карты")
    public MapPage waitBaseElements() {
        waitListOfTerminals();
        containerOfMap
                .waitVisible();
        if (titleNoAddress.notExist()) {
            pointsOfTerminalsOnTheMap
                    .shouldHave(sizeGreaterThan(0));
        }
        sleep(1000);
        return MapPage.this;
    }

    @Step("Ввод ОСП {address} в поле поиска и клик по по кнопке Найти")
    public MapPage findAddress(String address) {
        fieldInputAddress
                .waitVisible()
                .clear()
                .setText(address);
        sleep(500); // чтобы дождаться прогрузки яндексовского автоподбора для скипа его кнопкой поиска, иначе перекрывает автоподбор Деллин
        buttonSearch
                .waitClickable()
                .click();
        sleep(500); // Чтобы дождаться появления автоподбора
        if (containerOfAutoList.isDisplayed()) {
            itemAddressAutoList
                    .first()
                    .click();
        }
        return MapPage.this;
    }

    @Step("Проверка присутствия ОПС [{name}] в списке адресов")
    public MapPage checkAddressTermIsPresence(String name) {
        assertTrue(listOfTerminals.find(Condition.text(name)).exists(), "Осп \"" + name + "\" отсутсвует в списке адресов!");
        return MapPage.this;
    }

    @Step("Проверка присутсвия плашки 'Партнер' для терминала'")
    public MapPage checkPartnerBlockForTerminal(String name) {
        assertTrue(getPartnerElement(listOfTerminals.find(Condition.text(name))).exists(), "Осп \"" + name + "\" не содержит плашку партнера!");
        assertEquals(getPartnerElement(listOfTerminals.find(Condition.text(name))).getText(), "партнёр",
                "Для \"" + name + "\" отображается некорректный текст в плашке партнера!");
        return MapPage.this;
    }

    @Step("Проверка присутсвия блока 'Только получение'")
    public MapPage checkOnlyGiveoutBlockForTerminal(String name) {
        assertTrue(getServiceElement(listOfTerminals.find(Condition.text(name))).exists(), "Осп \"" + name + "\" не содержит блок 'Только получение'!");
        assertEquals(getServiceElement(listOfTerminals.find(Condition.text(name))).getText(), "Только получение",
                "Для \"" + name + "\" отображается некорректный текст в блоке 'Только получение'!");
        return MapPage.this;
    }

    @Step("Проверка присутсвия блока 'Только отправка'")
    public MapPage checkOnlyReceiveBlockForTerminal(String name) {
        assertTrue(getServiceElement(listOfTerminals.find(Condition.text(name))).exists(), "Осп \"" + name + "\" не содержит блок 'Только отправка'!");
        assertEquals(getServiceElement(listOfTerminals.find(Condition.text(name))).getText(), "Только отправка",
                "Для \"" + name + "\" отображается некорректный текст в блоке 'Только отправка'!");
        return MapPage.this;
    }

    @Step("Проверка присутсвия блока 'Только отправка'")
    public MapPage checkNoTerminalBlock() {
        assertTrue(titleNoAddress.isDisplayed(), "Блок без терминалов не отображается!");
        return MapPage.this;
    }

    @Step("Проверка присутсвия блока 'Только отправка'")
    public MapPage checkTextNoTerminalBlock(String text) {
        assertEquals(titleNoAddress.getText(), text,
                "В блоке без терминалов отображается некорректный текст!");
        return MapPage.this;
    }

    //вспомогательный метод для проверки плашки "Партнер"
    public SelenideElement getPartnerElement(SelenideElement rootElement) {
        return rootElement.find("div[class^=Contacts__Partner]");
    }

    //вспомогательный метод для получения имени
    public SelenideElement getNameElement(SelenideElement rootElement) {
        return rootElement.find("div[class^=Contacts__Name]");
    }

    //вспомогательный метод для получения лэйбла "Только отправка/получение"
    public SelenideElement getServiceElement(SelenideElement rootElement) {
        return rootElement.find("div[class^=Contacts__ServiceLabel]");
    }

    @Step("Проверка отсутствия ОПС [{name}] в списке адресов")
    public MapPage checkAddressTermIsAbsence(String name) {
        listOfTerminals.forEach(address -> assertFalse(getNameElement(address).getText().contains(name),
                "Осп \"" + name + "\" присутсвует в списке адресов!"));
        return MapPage.this;
    }

    @Step("Клик по дропдауну с видами терминалов")
    public MapPage clickKindOfTerminal() {
        kindOfTerminal
                .waitClickable()
                .click();
        return MapPage.this;
    }

    @Step("Выбор вида в дропдауне с видами терминалов")
    public MapPage chooseKindOfTerminal(KindOfTerminal kind) {
        clickKindOfTerminal();
        checkDropdownItemsIsVisible();
        switch (kind) {
            case CAN_RECEIVE:
                itemsKindOfTerminal.get(0).click();
                return this;
            case CAN_GIVEOUT:
                itemsKindOfTerminal.get(1).click();
                return this;
            case STORAGE:
                itemsKindOfTerminal.get(2).click();
                return this;
            case OFFICE:
                itemsKindOfTerminal.get(3).click();
                return this;
        }
        return MapPage.this;
    }

    // Вспомогательный список для выбора типа терминала
    public enum KindOfTerminal {
        CAN_RECEIVE,
        CAN_GIVEOUT,
        STORAGE,
        OFFICE
    }

    @Step("Выбор адреса ОСП {name} из списка адресов")
    public TerminalCardPopup clickTerminalName(String name) {
        getNameElement(listOfTerminals
                .find(Condition.text(name)))
                .click();
        return new TerminalCardPopup();
    }

    @Step("Клик на ОСП в карточке подразделения")
    public MapPage clickCity() {
        linkCity.waitClickable()
                .click();
        return MapPage.this;
    }

    @Step("Наведение курсора на текст 'Груз тяжелый или крупный'")
    public MapPage hoverTextHeavyOfLarge() {
        textHeavyOfLarge
                .hover();
        return MapPage.this;
    }

    @Step("Ожидание отображения поп-апа 'Груз тяжелый или крупный'")
    public MapPage waitPopupHeavyOfLarge() {
        popupHeavyOfLarge
                .waitVisible();
        return MapPage.this;
    }

    @Step("Проверка отображения поп-апа 'Груз тяжелый или крупный'")
    public MapPage checkPopupHeavyOfLargeIsVisible() {
        assertTrue(popupHeavyOfLarge
                .isDisplayed(), "Поп-ап 'Груз тяжелый или крупный' не отображается!");
        return MapPage.this;
    }

    @Step("Проверка текста поп-апа 'Груз тяжелый или крупный'")
    public MapPage checkMatchTextPopupHeavyOfLarge(String excepted) {
        assertEquals(popupHeavyOfLarge
                        .getText(),
                excepted, "Текст в попапе не соответствует ожидаемому значению!");
        return MapPage.this;
    }

    /**
     * =================================================================================================================
     * Elements
     * =================================================================================================================
     */

    // Поле ввода адреса (Яндекс)
    private final TextField fieldInputAddress = new TextField(
            "input[placeholder='Введите адрес']"
    );

    // Кнопка Найти (Яндекс)
    private final Button buttonSearch = new Button(
            By.xpath("*//ymaps[text()='Найти']")
    );

    // Список терминалов
    private final ElementsCollection listOfTerminals = new Item(
            "div[class^=Contacts__Item]"
    ).getElements();

    // Список адресов в блоке автоподбора
    private final ElementsCollection itemAddressAutoList = new Item(
            "ymaps[class*=popup] ymaps[class*='item__title']"
    ).getElements();

    //Дропдаун видов терминалов
    private final DropdownField kindOfTerminal = new DropdownField(
            "div[class^='Contacts__Filter']"
    );

    // Контейнер списка адресов терминалов
    private final Item containerOfAddresses = new Item(
            "div[class^='Contacts__List']"
    );

    // Элемент карты для ожидания отображения адресов
    private final Item titleAddress = new Item(
            "div[class^=Contacts__Item]:nth-of-type(1)"
    );

    // Элемент карты без адресов
    private final Item titleNoAddress = new Item(
            "div[class^=Contacts__NoData] p"
    );

    // Контейнер карты
    private final Item containerOfMap = new Item(
            "ymaps[class^='ymaps'][style*='url']"
    );

    // Контейнер списка автоподбора
    private final Item containerOfAutoList = new Item(
            "ymaps[class*=popup] > ymaps[class*=popup]"
    );

    // Список видов терминала
    private final ElementsCollection itemsKindOfTerminal = new Item(
            "div[class^='Contacts__Option'] > div"
    ).getElements();

    // Элемент списка для ожидания отображения
    private final Item itemKindOfTerminal = new Item(
            "div[class^='Contacts__Option'] > div:nth-of-type(1)"
    );

    // коллекция видимых на карте иконок терминалов
    private final ElementsCollection pointsOfTerminalsOnTheMap = new Image(
            "ymaps[class^='ymaps'][class*='placemark-overlay'] > ymaps > svg"
    ).getElements();

    // Чекбокс "Груз тяжелый или крупный"
    private final CheckBox checkboxHeavyOfLarge = new CheckBox(
            "label[class^='Contacts__CheckableFilter'] input"
    );

    // Текст "Груз тяжелый или крупный"
    private final TextElement textHeavyOfLarge = new TextElement(
            "label[class^='Contacts__CheckableFilter'] span"
    );

    // Всплывашка "Груз тяжелый или крупный"
    private final TextElement popupHeavyOfLarge = new TextElement(
            "[id^='mui']"
    );

    // ОСП в карточке подразделения
    private final Link linkCity = new Link(
            "[class=contacts__page-title] a"
    );

    // Кнопка "Открыть в Яндекс.картах"
    private final Button buttonPromo = new Button(
            "ymaps[class^='ymaps'][class$='promo']"
    );

}