package pages.mappage;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pageelements.Button;
import pageelements.Item;
import pageelements.Link;
import pageelements.TextElement;
import pages.BasePage;

import java.text.MessageFormat;
import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.*;
import static org.testng.Assert.assertTrue;

@SuppressWarnings("UnusedReturnValue")
public class TerminalCardPopup extends BasePage {

    @Override
    public TerminalCardPopup checkHeader() {
        // У окна хидер = наименованию терминала, т.е. всегда разный
        return null;
    }

    @Step("Проверка хирера окна на соответствие тексту : {excepted}")
    public TerminalCardPopup checkMatchHeader(String excepted) {
        assertEquals(header.getText(), excepted, "Текст хидера не соответствует ожидаемому значению!");
        return TerminalCardPopup.this;

    }

    @Step("Проверка таймзоны окна на соответствие тексту : {excepted} и параметру timeshift : {timezone}")
    public TerminalCardPopup checkMatchTimeZone(int timezone, String excepted) {
        String mess = MessageFormat.format("Для терминала {0} формат времени не соответсвует таймзоне {1}", excepted, timezone);
        String actual = terminalTimezone.getText();
        if (timezone > 0) {
            assertTrue(actual.startsWith("МСК+" + timezone), mess);
        } else if (timezone < 0) {
            assertTrue(actual.startsWith("МСК" + timezone), mess);
        } else {
            if (actual.contains(":")) {
                assertTrue(actual.matches("МСК " + "\\d{2}.\\d{2}"), mess);
            } else {
                assertTrue(actual.matches("МСК " + "\\d{4}"), mess);
            }
        }
        return TerminalCardPopup.this;
    }

    @Step("Ожидание отображения карточки терминала")
    public TerminalCardPopup waitTerminalCard() {
        terminalCard
                .waitVisible();
        sleep(500);
        header
                .waitVisible();
        return TerminalCardPopup.this;
    }

    @Step("Клик по Наименованию терминала модального окна")
    public TerminalCardPage clickNameOfTerminal() {
        header
                .waitVisible()
                .click();
        return new TerminalCardPage();
    }

    @Step("Проверка адреса терминала на соответствие {excepted}")
    public TerminalCardPopup checkMatchTerminalAddress(String excepted) {
        terminalAddress
                .waitVisible();
        assertEquals(terminalAddress.getText(),
                excepted,
                "Адрес терминала не соответствует ожидаемому значению!");
        return TerminalCardPopup.this;
    }

    @Step("Проверка телефонов на соответсвие API")
    public TerminalCardPopup checkMatchTerminalPhones(List<String> listFromAPI) {
        if (listFromAPI.isEmpty()) {
            fail("Список телефонов для терминала из api/contacts - пустой!");
        } else {
            for (int x = 0; x < listTerminalPhones.size(); x++) {
                assertEquals(listFromAPI.get(x),
                        listTerminalPhones.get(x).getText(),
                        "В списке телефонов обнаружены расхождения!");
            }
        }
        return TerminalCardPopup.this;
    }

    @Step("Проверка дефолтного значения телефона на сайте")
    public TerminalCardPopup checkDefaultTerminalPhone(List<String> listFromAPI) {
        if (listFromAPI.isEmpty()) {
            assertEquals(listTerminalPhones.get(0).getText(), "8 800 100–8000\n" +
                            "с мобильного 0520",
                    "Дефолтное значение теелфона на сайте не совпадает с ожидаемым результатом");
        } else {
            fail("Список телефонов для терминала в api/contacts должен быть пустой!");
        }
        return TerminalCardPopup.this;
    }

    @Step("Проверка соответствия Email терминала значению : {excepted}")
    public TerminalCardPopup checkMatchTerminalEmail(String excepted) {
        assertEquals(linkTerminalEmail.getText(), excepted,
                "Email терминала не соответствует ожидаемому значению!");
        return TerminalCardPopup.this;
    }

    @Step("Проверка отсутствия блока с расписанием для терминала")
    public TerminalCardPopup checkTerminalScheduleNotDisplayed() {
        assertTrue(blockTerminalSchedule.notDisplayed(), "Расписание для терминала отображается, а не должно!'");
        return TerminalCardPopup.this;
    }

    @Step("Проверка отсутствия заголовка расписания для терминала")
    public TerminalCardPopup checkScheduleTitleNotDisplayed() {
        assertTrue(titleTerminalSchedule.notDisplayed(), "Заголовок 'График работы' отображается, а не должен!'");
        return TerminalCardPopup.this;
    }

    @Step("Проверка, что в блоке ограничений пункт {name} имеет значение {value}")
    public TerminalCardPopup checkLimitationValue(String name, String value) {
        for (SelenideElement item : listOfTerminalLimitation) {
            if (getFirstDiv(item).getText().equals(name)) {
                assertTrue(getSecondDiv(item).getText().matches(value), MessageFormat.format("Значение в пункте {0} не равно: {1}", name, value));
                return TerminalCardPopup.this;
            }
        }
        throw new RuntimeException(MessageFormat.format("Пункт {0} в блоке ограничений не найден!", name));
    }

    public SelenideElement getFirstDiv(SelenideElement rootElement) {
        return rootElement.find("div:nth-of-type(1)");
    }

    public SelenideElement getSecondDiv(SelenideElement rootElement) {
        return rootElement.find("div:nth-of-type(2)");
    }

    /**
     * =================================================================================================================
     * Elements
     * =================================================================================================================
     */

    // Хидер модального окна
    private final TextElement header = new TextElement(
            "[class^='TerminalCard__Wrapper'] h1 a"
    );

    // Иконка закрытия модального окна
    private final Button iconCloseCard = new Button(
            "[class^='TerminalCard__CloseIcon']"
    );

    // Контейнер карточки терминала
    private final Item terminalCard = new Item(
            "[class^='TerminalCard__Wrapper']"
    );

    // Адрес терминала
    private final TextElement terminalAddress = new TextElement(
            "[class^='TerminalCard__Address']"
    );

    // Время и таймзона терминала
    private final TextElement terminalTimezone = new TextElement(
            "[class^='LocalTime__Time']"
    );

    // Email терминала
    private final Link linkTerminalEmail = new Link(
            "[class^='TerminalCard__Contacts'] > div > div > a"
    );

    // Заголовок "График работы"
    private final TextElement titleTerminalSchedule = new TextElement(
            "[class^='TerminalCard__ScheduleTitle']"
    );

    // Блок с расписанием работы терминала
    private final TextElement blockTerminalSchedule = new TextElement(
            "div[class^='TerminalCard__Columns']  > div:nth-of-type(1) > div[class^='TerminalCard__Schedule']"
    );

    // Список телефонов терминала
    private final ElementsCollection listTerminalPhones = new TextElement(
            "[class^='TerminalCard__Contacts'] > div > div > p"
    ).getElements();

    // Список ограничений по грузу
    private final ElementsCollection listOfTerminalLimitation = new TextElement(
            "div[class*='CargoDimensions'] > div"
    ).getElements();

}
