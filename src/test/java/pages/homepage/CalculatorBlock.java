package pages.homepage;

import io.qameta.allure.Step;
import org.testng.Assert;
import pageelements.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;
import static helpers.NavigatorHelper.switchToTab;
import static org.testng.Assert.*;

/**
 * =================================================================================================================
 * Блок "Экспресс калькулятор" на главной странице
 * =================================================================================================================
 */
@SuppressWarnings("UnusedReturnValue")
public class CalculatorBlock extends HomePage {

    @Step("Клик на поле ввода населенного пункта отправления в блоке Калькулятора")
    public CalculatorBlock clickFieldDerival() {
        fieldDerival.waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Клик на поле ввода населенного пункта прибытия в блоке Калькулятора")
    public CalculatorBlock clickFieldArrival() {
        fieldArrival.waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Ввести {city} в поле населенного пункта отправления")
    public CalculatorBlock fillCityDerival(String city) {
        scrollToTitleOfTheCalculator();
        clickFieldDerival();
        fieldDerival
                .clear()
                .setText(city);
        return CalculatorBlock.this;
    }

    @Step("Выбрать населенный пункт отправления {city}")
    public CalculatorBlock chooseCityDerival(String city) {
        fillCityDerival(city);
        listOfCities.getItems()
                .find(visible)
                .shouldHave(text(city))
                .click();
        return CalculatorBlock.this;
    }

    @Step("Выбрать населенный пункт отправления {city} с помощью клавиш")
    public CalculatorBlock chooseCityDerivalManually(String city) {
        fillCityDerival(city);
        sleep(1000);
        fieldDerival
                .pressEnter();
        return CalculatorBlock.this;
    }

    @Step("Выбрать населенный пункт прибытия {city} с помощью клавиш")
    public CalculatorBlock chooseCityArrivalManually(String city) {
        fillCityArrival(city);
        sleep(1000);
        fieldArrival
                .pressEnter();
        return CalculatorBlock.this;
    }

    @Step("Клик на стрелку перестановки направления")
    public CalculatorBlock clickArrows() {
        imageArrows
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Проверить НП отправления на соответствие ожидаемому значению: {exceptedCity}")
    public CalculatorBlock checkMatchCityDerival(String exceptedCity) {
        Assert.assertEquals(fieldDerival.getAttribute("value"), exceptedCity,
                "Населенный пункт не совпадает с ожидаемым значением");
        return CalculatorBlock.this;
    }

    // вспомогательный медот для передачи ист/ложь совпадения города отправки
    public boolean matchCities(String exceptedCity) {
        return fieldDerival.getAttribute("value").equals(exceptedCity);
    }

    @Step("Проверка отображения первого значения в списке НП на соответсвие ожидаемому {exceptedCity}")
    public CalculatorBlock checkFirstItemList(String exceptedCity) {
        assertTrue(listOfCities
                        .getItems()
                        .get(0)
                        .getText()
                        .contains(exceptedCity),
                "Первый населенный пункт списка не совпадает с ожидаемым значением");
        return CalculatorBlock.this;
    }

    @Step("Ввести {city} в поле населенного пункта прибытия")
    public CalculatorBlock fillCityArrival(String city) {
        scrollToTitleOfTheCalculator();
        clickFieldArrival();
        fieldArrival
                .clear()
                .setText(city);
        return CalculatorBlock.this;
    }

    @Step("Выбрать населенный пункт прибытия {city}")
    public CalculatorBlock chooseCityArrival(String city) {
        fillCityArrival(city);
        listOfCities.getItems()
                .find(visible)
                .shouldHave(text(city))
                .click();
        return CalculatorBlock.this;
    }

    @Step("Проверить НП прибытия на соответствие ожидаемому значению: {exceptedCity}")
    public CalculatorBlock checkMatchCityArrival(String exceptedCity) {
        Assert.assertEquals(fieldArrival.getAttribute("value"), exceptedCity,
                "Населенный пункт не совпадает с ожидаемым значением");
        return CalculatorBlock.this;
    }

    @Step("Проверить подсказку в списке НП")
    public CalculatorBlock checkHintInItemList() {
        assertTrue(textLintOfList1
                        .getText()
                        .contains("Уточните поиск населённого пункта указав область, район или край"),
                "Подсказка не отображается или отображается некорректно!");
        return CalculatorBlock.this;
    }

    @Step("Клик на кнопку рассчитать")
    public void clickCalculate() {
        buttonCalculate.waitClickable().click();
    }

    @Step("Получение стоимости перевозки От адреса до адреса")
    public String receivePriceDoorToDoor() {
        return textPriceDoorToDoor.getText().replace("₽", "").trim();
    }

    @Step("Получение кол-во дней перевозки От адреса до адреса")
    public String receivePeriodDoorToDoor() {
        return textPeriodDoorToDoor.getText();
    }

    @Step("Получение стоимости перевозки Между терминалами")
    public String receivePriceBetweenTerminals() {
        return textPriceBetweenTerminals.getText().replace("₽", "").trim();
    }

    @Step("Получение кол-во дней перевозки Между терминалами")
    public String receivePeriodBetweenTerminals() {
        return textPeriodBetweenTerminals.getText();
    }

    @Step("Получение стоимости перевозки Между терминалами")
    public String receivePriceTransport() {
        return textPriceTransport.getText().replace("₽", "").trim();
    }

    @Step("Проверка отображения заголовка блока Калькулятора")
    public CalculatorBlock checkTitleOfTheCalculator() {
        assertTrue(titleOfTheCalculator.isDisplayed(),
                "Заголовок блока 'Калькулятор' не отображается");
        return CalculatorBlock.this;
    }

    @Step("Проверка текса в заголовке калькулятора на соответсвие {excepted}")
    public CalculatorBlock checkMatchTextTitleOfTheCalculator(String excepted) {
        assertEquals(titleOfTheCalculator.getText(), excepted,
                "Заголовок блока 'Калькулятор' не содержит ожидаемое значение");
        return CalculatorBlock.this;
    }

    @Step("Проверка текса под кнопкой 'Рассчитать' на соответсвие {excepted}")
    public CalculatorBlock checkTextUnderTheCalculator() {
        assertEquals(titleUnderCalculator.getText(),
                "*Указаны минимальная стоимость и ориентировочные сроки",
                "Заголовок под блоком 'Калькулятор' не содержит ожидаемое значение");
        return CalculatorBlock.this;
    }

    @Step("Проверка содержания текста сообщения в блоке направления")
    public CalculatorBlock checkMatchMessageDirection(String excepted) {
        assertEquals(titleMessageCalculator.getText(), excepted,
                "Сообщение в блоке направления не содержит ожидаемое значение!");
        return CalculatorBlock.this;
    }

    @Step("Проверка содержания ссылки сообщения в блоке направления")
    public CalculatorBlock checkLinkMessageDirection(String excepted) {
        assertTrue(linkMessageCalculator.getUrl().contains(excepted),
                "Ссылка в блоке направления не содержит ожидаемое значение!");
        return CalculatorBlock.this;
    }

    @Step("Прокрутить страницу до калькутятора")
    public CalculatorBlock scrollToTitleOfTheCalculator() {
        titleOfTheCalculator.scrollTo();
        return CalculatorBlock.this;
    }

    @Step("Ожидание, пока кнопка 'Рассчитать' не станет активна")
    public CalculatorBlock waitCalculate() {
        sleep(1000); // При изменении стоимости тест может лихо пойти дальше, не дождавшись отображения лоадеров
        animatePreloader
                .waitUntil(not(visible), 30000);
        animatePreloaderBlock
                .waitUntil(not(visible), 30000);
        sleep(2000); // чтобы цены успели прогрузиться на свои места.
        return CalculatorBlock.this;
    }

    @Step("Проверка, что кнопка 'Рассчитать' активна")
    public CalculatorBlock checkButtonCalculateEnabled() {
        sleep(3000);
        assertTrue(buttonCalculate.has(attribute("href")), "Кнопка 'Расчитать' не активна");
        return CalculatorBlock.this;
    }

    @Step("Проверка ,что кнопка 'Рассчитать' не активна")
    public CalculatorBlock checkButtonCalculateDisabled() {
        assertFalse(buttonCalculate.has(attribute("href")), "Кнопка 'Расчитать' активна, а не должна");
        return CalculatorBlock.this;
    }

    @Step("Проверка отображения элементов блока 'Между терминалами'")
    public CalculatorBlock checkElementsBlockTerminal() {
        assertTrue(titleOfBetweenTerminals.isDisplayed(), "Заголовок блока 'Между терминалами' не отображается");
        assertTrue(radioTerminalStandard.isDisplayed(), "Кнопка 'Стандарт' не отображается");
        assertTrue(radioTerminalAvia.isDisplayed(), "Кнопка 'Авиа' не отображается");
        assertTrue(radioTerminalExpress.isDisplayed(), "Кнопка 'Экспресс' не отображается");
        return CalculatorBlock.this;
    }

    @Step("Нажать на кнопку 'Между терминалами - Стандарт'")
    public CalculatorBlock clickTerminalStandard() {
        radioTerminalStandard
                .waitClickable()
                .click();
        return this;
    }

    @Step("Нажать на кнопку 'Между терминалами - Экспресс'")
    public CalculatorBlock clickTerminalExpress() {
        radioTerminalExpress
                .waitClickable()
                .click();
        return this;
    }

    @Step("Нажать на кнопку 'Между терминалами - Авиа'")
    public CalculatorBlock clickTerminalAvia() {
        radioTerminalAvia
                .waitClickable()
                .click();
        return this;
    }

    @Step("Нажать на кнопку 'Между терминалами - Документы'")
    public CalculatorBlock clickTerminalDocuments() {
        radioTerminalDocuments
                .waitClickable()
                .click();
        return this;
    }

    @Step("Проверка отображения элементов блока 'От адреса до адреса'")
    public CalculatorBlock checkElementsBlockDoorToDoor() {
        assertTrue(titleOfDoorToDoor.isDisplayed(), "Заголовок блока 'От адреса до адреса' не отображается");
        assertTrue(radioHomeStandard.isDisplayed(), "Кнопка 'Стандарт' не отображается");
        assertTrue(radioHomeDocuments.isDisplayed(), "Кнопка 'Документы' не отображается");
        assertTrue(radioHomePackage.isDisplayed(), "Кнопка 'Малогабарит' не отображается");
        assertTrue(radioHomeExpress.isDisplayed(), "Кнопка 'Экспресс' не отображается");
        assertTrue(radioHomeAvia.isDisplayed(), "Кнопка 'Авиа' не отображается");
        return CalculatorBlock.this;
    }

    @Step("Нажать на кнопку 'От адреса до адреса - Стандарт'")
    public CalculatorBlock clickHomeStandard() {
        radioHomeStandard
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Проверка активности кнопки 'От адреса до адреса - Стандарт'")
    public CalculatorBlock checkHomeStandardIsSelected() {
        assertTrue(radioHomeStandard
                        .getCssValue("background")
                        .contains("rgb(233, 137, 48), rgb(233, 167, 48)"),
                "Активная кнопка 'От адреса до адреса - Стандарт' должна быть оранжевая!");
        return this;
    }

    @Step("Проверка отсутствия активности кнопки 'От адреса до адреса - Стандарт'")
    public CalculatorBlock checkHomeStandardIsNotSelected() {
        assertFalse(radioHomeStandard
                        .getCssValue("background")
                        .contains("rgb(233, 137, 48), rgb(233, 167, 48)"),
                "Не активная кнопка 'От адреса до адреса - Стандарт' не должна быть оранжевая!");
        return this;
    }

    @Step("Нажать на кнопку 'От адреса до адреса - Документы'")
    public CalculatorBlock clickHomeDocuments() {
        radioHomeDocuments
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Нажать на кнопку 'От адреса до адреса - Малогабарит'")
    public CalculatorBlock clickHomePackage() {
        radioHomePackage
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Нажать на кнопку 'От адреса до адреса - Экспресс'")
    public CalculatorBlock clickHomeExpress() {
        radioHomeExpress
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Проверка активности кнопки 'От адреса до адреса - Экспресс'")
    public CalculatorBlock checkHomeExpressSelected() {
        assertTrue(radioHomeExpress
                        .getCssValue("background")
                        .contains("rgb(233, 137, 48), rgb(233, 167, 48)"),
                "Активная кнопка 'От адреса до адреса - Экспресс' должна быть оранжевая!");

        return this;
    }

    @Step("Проверка не активности кнопки 'От адреса до адреса - Экспресс'")
    public CalculatorBlock checkHomeExpressNotSelected() {
        assertFalse(radioHomeExpress
                        .getCssValue("background")
                        .contains("rgb(233, 137, 48), rgb(233, 167, 48)"),
                "Неактивная кнопка 'От адреса до адреса - Экспресс' должна не должна быть оранжевой!");
        return this;
    }

    @Step("Нажать на кнопку 'От адреса до адреса - Авиа'")
    public CalculatorBlock clickHomeAvia() {
        radioHomeAvia
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Проверка отображения элементов блока 'Выделенный транспорт'")
    public CalculatorBlock checkElementsBlockDedicatedTransport() {
        assertTrue(titleOfDedicatedTransport.isDisplayed(), "Заголовок блока 'Выделенный транспорт' не отображается");
        assertTrue(radioTransportDelivery.isDisplayed(), "Кнопка 'Малотоннажные ТС' не отображается");
        assertTrue(radioTransportTruck.isDisplayed(), "Кнопка 'Еврофура' не отображается");
        assertTrue(radioTransportRent.isDisplayed(), "Кнопка 'Почасовая аренда' не отображается");
        assertTrue(radioTransportContainer.isDisplayed(), "Кнопка 'Контейнер' не отображается");
        return CalculatorBlock.this;
    }

    @Step("Нажать на кнопку 'Выделенный транспорт - Малотоннажные ТС'")
    public CalculatorBlock clickTransportDelivery() {
        radioTransportDelivery
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Нажать на кнопку 'Выделенный транспорт - Еврофура'")
    public CalculatorBlock clickTransportTruck() {
        radioTransportTruck
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Нажать на кнопку 'Выделенный транспорт - Почасовая аренда'")
    public CalculatorBlock clickTransportRent() {
        radioTransportRent
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Нажать на кнопку 'Выделенный транспорт - Контейнер'")
    public CalculatorBlock clickTransportContainer() {
        radioTransportContainer
                .waitClickable()
                .click();
        return CalculatorBlock.this;
    }

    @Step("Проверка отображения фразы 'Не осуществляется по этому направлению' для блока 'Между терминалами'")
    public CalculatorBlock checkTerminalsNotAvailable() {
        assertTrue(titleTerminalsNotAvailable.isDisplayed(),
                "Фраза 'Не осуществляется по этому направлению' для блока 'Между терминалами' не отображается");
        return CalculatorBlock.this;
    }

    @Step("Проверка отображения фразы 'Не осуществляется по этому направлению' для блока 'От адреса до адреса")
    public CalculatorBlock checkDoorToDoorNotAvailable() {
        assertTrue(titleDoorToDoorNotAvailable.isDisplayed(),
                "Фраза 'Не осуществляется по этому направлению' для блока 'От адреса до адреса' не отображается");
        return CalculatorBlock.this;
    }

    @Step("Проверка отображения стоимости перевозки От адреса до адреса")
    public CalculatorBlock checkPriceDoorToDoor() {
        assertTrue(textPriceDoorToDoor.getText().contains("₽"), "Стоимость перевозки От адреса до адреса не отображается");
        return CalculatorBlock.this;
    }

    @Step("Проверка отображения стоимости перевозки Между терминалами")
    public CalculatorBlock checkPriceBetweenTerminals() {
        assertTrue(textPriceBetweenTerminals.getText().contains("₽"), "Стоимость перевозки Между терминалами не отображается");
        return CalculatorBlock.this;
    }

    @Step("Блок 'Между терминалами'. Проверка текста выбранного вида перевозки на соотетствие {exceptedText}")
    public CalculatorBlock checkMatchTitleTerminalsSelected(String exceptedText) {
        assertEquals(titleTerminalsSelected.getText(), exceptedText, "Текст под иконкой не соответсвует ожидаемому значению");
        return CalculatorBlock.this;
    }

    @Step("Блок 'От адреса до адреса'. Проверка текста выбранного вида перевозки на соотетствие {exceptedText}")
    public CalculatorBlock checkMatchTitleHomeSelected(String exceptedText) {
        assertEquals(titleHomeSelected.getText(), exceptedText, "'От адреса до адреса' Текст под иконкой не соответсвует ожидаемому значению");
        return CalculatorBlock.this;
    }

    @Step("Блок 'Выделенный транспорт'. Проверка текста выбранного вида перевозки на соотетствие {exceptedText}")
    public CalculatorBlock checkMatchTitleTransportSelected(String exceptedText) {
        assertEquals(titleTransportSelected.getText(), exceptedText, "'Выделенный транспорт' Текст под иконкой не соответсвует ожидаемому значению");
        return CalculatorBlock.this;
    }

    @Step("Блок 'Между терминалами'. Сравнение цены API и сайта")
    public CalculatorBlock checkTerminalPrices(String priceAPI) {
        sleep(1000); // На случай если цены не успеют прогрузиться
        if (priceAPI.equals("0")) {
            assertTrue(titleTerminalsNotAvailable.isDisplayed(), "'Между терминалами' Отсутствие цены в API и на сайте не совпадает");
        } else {
            assertTrue(textPriceBetweenTerminals.isDisplayed(), "'Между терминалами', блок со стоимостью не отображается!");
            assertEquals(priceAPI.split("\\D", 2)[0],
                    receivePriceBetweenTerminals().replace("₽", " ").replace(",", "").replace(" ", ""),
                    "'Между терминалами' Цены в API и на сайте не совпадают");
        }
        return CalculatorBlock.this;
    }

    @Step("Блок 'Между терминалами'. Сравнение скоров доставки API и сайта")
    public CalculatorBlock checkTerminalPeriods(String periodAPI) {
        sleep(1000); // На случай если цены не успеют прогрузиться
        if (periodAPI == null) {
            assertTrue(titleTerminalsNotAvailable.isDisplayed(), "'Между терминалами' Отсутствие цены в API и на сайте не совпадает");
        } else {
            assertTrue(textPeriodBetweenTerminals.isDisplayed(), "'Между терминалами', блок со сроками не отображается!");
            assertEquals(periodAPI, receivePeriodBetweenTerminals().split("\\s", 2)[0], "'Между терминалами' Срок достави в API и на сайте не совпадает");
        }
        return CalculatorBlock.this;
    }

    @Step("Блок 'От адреса до адреса'. Сравнение цены API и сайта")
    public CalculatorBlock checkHomePrices(String priceAPI) {
        sleep(1000); // На случай если цены не успеют прогрузиться
        if (priceAPI.equals("0")) {
            assertTrue(titleDoorToDoorNotAvailable.isDisplayed(), "'От адреса до адреса' Отсутствие цены в API и на сайте не совпадает!");
        } else {
            assertTrue(textPriceDoorToDoor.isDisplayed(), "'От адреса до адреса', блок со стоимостью не отображается!");
            assertEquals(priceAPI.split("\\D", 2)[0],
                    receivePriceDoorToDoor().replace("₽", " ").replace(",", "").replace(" ", ""),
                    "'От адреса до адреса' Цены в API и на сайте не совпадают!");
        }
        return CalculatorBlock.this;
    }

    @Step("Блок 'От адреса до адреса'. Сравнение сроков доставки API и сайта")
    public CalculatorBlock checkHomePeriods(String periodAPI) {
        sleep(1000); // На случай если цены не успеют прогрузиться
        if (periodAPI == null) {
            assertTrue(titleDoorToDoorNotAvailable.isDisplayed(), "'От адреса до адреса' Отсутствие цены в API и на сайте не совпадает");
        } else {
            assertTrue(textPeriodDoorToDoor.isDisplayed(), "'От адреса до адреса', блок со сроками не отображается!");
            assertEquals(periodAPI, receivePeriodDoorToDoor().split("\\s", 2)[0], "'От адреса до адреса' Срок достави в API и на сайте не совпадает");
        }
        return CalculatorBlock.this;
    }

    @Step("Блок 'Выделенный транспорт'. Сравнение цены API: {priceAPI} и сайта")
    public CalculatorBlock checkTransportPrices(String priceAPI) {
        sleep(1000); // На случай если цены не успеют прогрузиться
        if (priceAPI.equals("0")) {
            assertTrue(titleTransportNotAvailable.isDisplayed(), "'Выделенный транспорт' блок отсутствия стоимости не отображается!");
            assertFalse(titleTransportNotAvailable.getText().contains("₽"), "'Выделенный транспорт' Отсутствие цены в API и на сайте не совпадает!");
        } else if (priceAPI.equals("По запросу")) {
            assertTrue(titleTransportNotAvailable.isDisplayed(), "Для вида перевозки 'Контейнер' не отображается надпись 'По запросу'!");
            assertEquals(priceAPI, titleTransportNotAvailable.getText(), "Для вида перевозки 'Контейнер' не отображается надпись 'По запросу'!");
        } else {
            assertTrue(textPriceTransport.isDisplayed(), "'Выделенный транспорт' Блок со стоимостью перевозки не отображается!");
            assertEquals(priceAPI.split("\\D", 2)[0],
                    receivePriceTransport().replace("₽", " ").replace(",", " ").replace(" ", ""),
                    "'Выделенный транспорт' Цены в API и на сайте не совпадают!");
        }
        return CalculatorBlock.this;
    }

    @Step("Проверка отсутствия стоимости для 'Прямой перевозки' при указании удаленных городов, не принадлежащих одной области")
    public CalculatorBlock checkTransportDifferentSubj() {
        assertTrue(titleTransportNotAvailable.isDisplayed(), "На вкладке 'Малотоннажные ТС' при указании удаленных городов, не отображается текст 'По запросу'");
        assertTrue(titleTransportNotAvailable.getText().contains("По запросу"), "Текст в блоке 'По запросу' не соответствует ожидаемому значению");
        return CalculatorBlock.this;
    }

    @Step("Проверка наличия в кнопке 'Между терминалами - Стандарт' цены и периода перевозки")
    public CalculatorBlock checkTerminalPriceAndPeriodFromButton() {
        assertTrue(priceAndPeriodTerminalStandard.isDisplayed(),
                "В кнопке 'От адреса до адреса - Стандарт' цена и период перевозки не отображаются!");
        return CalculatorBlock.this;
    }

    @Step("Проверка наличия в кнопке 'От адреса до адреса - Стандарт' цены и периода перевозки")
    public CalculatorBlock checkHomePriceAndPeriodFromButton() {
        assertTrue(priceAndPeriodHomeStandard.isDisplayed(),
                "В кнопке 'От адреса до адреса - Стандарт' цена и период перевозки не отображаются!");
        return CalculatorBlock.this;
    }

    @Step("Клик по ссылке в 'А вы знали?' и проверка осуществления перехода")
    public CalculatorBlock clickLinkBlockDidYouKnow(String url) {
        linkDidYouKnow.waitClickable()
                .click();
        switchToTab(1);
        assertTrue(url().contains(url), "Не верный url в блоке 'А вы знали?'");
        return this;
    }

    @Step("Проверка наличия ссылки в блоке 'А вы знали?'")
    public CalculatorBlock checkLinkBlockDidYouKnow() {
        assertTrue(linkDidYouKnow.is(exist), "Нет ссылки в блоке 'А вы знали?'");
        return this;
    }

    @Step("Проверка проверка соответствия заголовка в блоке 'А вы знали?' значению {expectedTitle}")
    public CalculatorBlock titleRefresh(String expectedTitle) {
        int i = 0;
        String title;
        do {
            i = i + 1;
            refresh();
            openPage()
                    .waitFormLogoDellin()
                    .getCalculatorBlock()
                    .waitCalculate()
                    .scrollToTitleOfTheCalculator();
            sleep(200);
            title = titleYouKnow.getText();
        } while ((!expectedTitle.equals(title)) && (i < 5));
        return CalculatorBlock.this;
    }

    @Step("Проверка не отображения подсказок на главной странице")
    public CalculatorBlock checkHintNotDisplayed(String hints) {
        Assert.assertFalse(textYouKnow.getText().contains(hints), "Отображается подсказка, а не должна");
        return this;
    }

    /**
     * =================================================================================================================
     * Elements
     * =================================================================================================================
     */

    // Заголовок блока
    private static final TextElement titleOfTheCalculator = new TextElement(
            "div[class^=HomeOrder] div[class^=Heading]"
    );

    // Заголовок блока "Между терминалами"
    private static final TextElement titleOfBetweenTerminals = new TextElement(
            "div[class^=\"sc-htp\"]:nth-of-type(1) > div[class^=ColumnWrapper] h3"
    );

    // Заголовок блока "От адреса до адреса"
    private static final TextElement titleOfDoorToDoor = new TextElement(
            "div[class^=\"sc-htp\"]:nth-of-type(2) > div[class^=ColumnWrapper] h3"
    );

    // Заголовок блока "Выделенный транспорт"
    private static final TextElement titleOfDedicatedTransport = new TextElement(
            "div[class^=\"sc-htp\"]:nth-of-type(3) > div[class^=ColumnWrapper] h3"
    );

    // Поле "Откуда"
    private static final DropdownField fieldDerival = new DropdownField(
            "div[class^=\"Route-sc\"] div[class^=\"Input-\"]:nth-of-type(1) input"
    );

    // Поле "Куда"
    private static final DropdownField fieldArrival = new DropdownField(
            "div[class^=\"Route-sc\"] div[class^=\"Input-\"]:nth-of-type(2) input"
    );

    // Стрелка перемещения значений откуда <-> куда
    public static final Image imageArrows = new Image(
            "span > svg[class^=Arrows]"
    );

    // Ссылка "некоторых грузов"
    private static final Link linkSomeCargo = new Link(
            "div[class^=\"Specials_\"] a"
    );

    // Анимированный прелоадер калькулятора
    private static final Item animatePreloader = new Item(
            "div[class^='AnimatePreloading']"
    );

    // Анимированный прелоадер в блоке "От адреса до адреса"
    private static final Item animatePreloaderBlock = new Item(
            "div[class^=\"sc-htp\"]:nth-of-type(2) div[class^='Preloader']:nth-of-type(1)"
    );

    // Кнопка "Между терминалами - Стандарт"
    private static final RadioButton radioTerminalStandard = new RadioButton(
            "button[id='ltl.terminal.terminals_standard']"
    );

    // Стоимость и срок в кнопке "Между терминалами - Стандарт"
    private static final TextElement priceAndPeriodTerminalStandard = new TextElement(
            "button[id='ltl.terminal.terminals_standard'] div[class^=Price] > em"
    );

    // Кнопка "Между терминалами - Документы"
    private static final RadioButton radioTerminalDocuments = new RadioButton(
            "button[id='ltl.terminal.terminals_documents']"
    );

    // Кнопка "Между терминалами - Экспресс"
    private static final RadioButton radioTerminalExpress = new RadioButton(
            "button[id='ltl.terminal.terminals_express']"
    );

    // Кнопка "Между терминалами - Авиа"
    private static final RadioButton radioTerminalAvia = new RadioButton(
            "button[id='ltl.terminal.terminals_avia']"
    );

    // Кнопка "От адреса до адреса - Стандарт"
    private static final RadioButton radioHomeStandard = new RadioButton(
            "button[id='ltl.door_to_door.door_to_door_standard']"
    );

    // Стоимость и срок в кнопке "От адреса до адреса - Стандарт"
    private static final TextElement priceAndPeriodHomeStandard = new TextElement(
            "button[id='ltl.door_to_door.door_to_door_standard'] div[class^=Price] > em"
    );

    // Кнопка "От адреса до адреса - Документы"
    private static final RadioButton radioHomeDocuments = new RadioButton(
            "button[id='ltl.door_to_door.door_to_door_documents']"
    );

    // Кнопка "От адреса до адреса - Посылка"
    private static final RadioButton radioHomePackage = new RadioButton(
            "button[id='ltl.door_to_door.door_to_door_parcel']"
    );

    // Кнопка "От адреса до адреса - Экспресс"
    private static final RadioButton radioHomeExpress = new RadioButton(
            "button[id='ltl.door_to_door.door_to_door_express']"
    );

    // Кнопка "От адреса до адреса - Авиа"
    private static final RadioButton radioHomeAvia = new RadioButton(
            "button[id='ltl.door_to_door.door_to_door_avia']"
    );

    // Кнопка "Выделенный транспорт - Прямая перевозка"
    private static final RadioButton radioTransportDelivery = new RadioButton(
            "button[id='other.truck_straight']"
    );

    // Кнопка "Выделенный транспорт - Еврофура"
    private static final RadioButton radioTransportTruck = new RadioButton(
            "button[id='other.truck_ftl']"
    );

    // Кнопка "Выделенный транспорт - Почасовая аренда"
    private static final RadioButton radioTransportRent = new RadioButton(
            "button[id='other.truck_rent']"
    );

    // Кнопка "Выделенный транспорт - Контейнер"
    private static final RadioButton radioTransportContainer = new RadioButton(
            "button[id='other.container']"
    );

    // Кнопка "Рассчитать"
    private static final Button buttonCalculate = new Button(
            "div[class^=\"SubmitWrapper\"] [class^=\"SubmitButton\"]"
    );

    // Сообщение 'Не осуществляется по этому направлению' для блока От адреса до адреса
    private static final TextElement titleDoorToDoorNotAvailable = new TextElement(
            "div[class^=sc-htp]:nth-of-type(2) div[class^=SelectedOption] div[class^=NotAvailable]"
    );

    // Сообщение 'Не осуществляется по этому направлению' для блока Между терминалами
    private static final TextElement titleTerminalsNotAvailable = new TextElement(
            "div[class^=sc-htp]:nth-of-type(1) div[class^=SelectedOption] div[class^=NotAvailable]"
    );

    // Сообщение 'Не осуществляется по этому направлению' для блока Выделенный транспорт
    private static final TextElement titleTransportNotAvailable = new TextElement(
            "div[class^=sc-htp]:nth-of-type(3) div[class^=SelectedOption] div[class^='Title']"
    );

    // Стоимость перевозки От адреса до адреса
    private static final TextElement textPriceDoorToDoor = new TextElement(
            "[class^=SelectedOption__StyledSelectedOption] [class^=Box] [class^=Price]"
    );

    // Кол-во дней перевозки От адреса до адреса
    private static final TextElement textPeriodDoorToDoor = new TextElement(
            "[class^=SelectedOption__StyledSelectedOption] [class^=Box] [class^=Period]"
    );

    // Стоимость перевозки Между терминалами
    private static final TextElement textPriceBetweenTerminals = new TextElement(
            "[class^=SelectedOption__StyledSelectedOption] [class^=Box] [class^=Price]"
    );

    // Кол-во дней перевозки Между терминалами
    private static final TextElement textPeriodBetweenTerminals = new TextElement(
            "[class^=SelectedOption__StyledSelectedOption] [class^=Box] [class^=Period]"
    );

    // Стоимость перевозки Выделенный транспорт
    private static final TextElement textPriceTransport = new TextElement(
            "[class^=SelectedOption__StyledSelectedOption] [class^=Box] [class^=Price]"
    );

    // Список городов при вводе пункта отправки/доставки
    private final ItemList listOfCities = new ItemList(
            "div[class^=Dropdown] ul"
    );

    // Подсказка 1
    private final TextElement textLintOfList1 = new TextElement(
            "div[class^=Dropdown] p[class^=Hint]"
    );

    // Подсказка 2
    private final TextElement textLintOfList2 = new TextElement(
            "div[class^=Dropdown] p[class^=Hint] ~ span"
    );

    // Заголовок для выбранного вида перевозки "Между терминалами"
    private final TextElement titleTerminalsSelected = new TextElement(
            "div:nth-of-type(1) div[class^=SelectedOption] span"
    );

    // Заголовок для выбранного вида перевозки "От адреса до адреса"
    private final TextElement titleHomeSelected = new TextElement(
            "div:nth-of-type(2) div[class^=SelectedOption] span"
    );

    // Заголовок для выбранного вида перевозки "Выделенный транспорт"
    private final TextElement titleTransportSelected = new TextElement(
            "div:nth-of-type(3) div[class^=SelectedOption] span"
    );

    // Текст "*Указаны минимальная стоимость..."
    private final TextElement titleUnderCalculator = new TextElement(
            "div[class^=Submit] > div[class^=SubmitInfo]"
    );

    // Текст "На этом направлении действуют специальные предложения"
    private static final TextElement titleMessageCalculator = new TextElement(
            "div[class*=Message]"
    );

    // Ссылка "специальные предложения"
    private static final Link linkMessageCalculator = new Link(
            "div[class*=Message] > a"
    );

    // Блок А вы знали?
    private final TextElement textYouKnow = new TextElement(
            "[class^=HomeTooltip]"
    );

    // Заголовок в блока А вы знали?
    private final TextElement titleYouKnow = new TextElement(
            "[class^=HomeTooltip] > div[class*='Title']"
    );


    // Ссылка в блоке А вы Знали?
    private final Link linkDidYouKnow = new Link(
            "[class^=HomeTooltip] p a"
    );

    /**
     * =================================================================================================================
     * FAQ
     * =================================================================================================================
     */

    // Список вопросов
    private static final ItemList listQuestions = new ItemList("div[class*=\"Guides\"] ul[class^=\"List-sc\"]");
}
