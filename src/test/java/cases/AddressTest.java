package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.homepage.HomePage;
import pages.mappage.MapPage;
import settings.driver.WebSettings;
import settings.utils.Retry;

import static helpers.contacts.ContactsHelper.getTerminalWith;
import static helpers.contacts.condition.EnumTerminalCondition.*;
import static pages.mappage.MapPage.KindOfTerminal.*;
import static settings.driver.WebSettings.FEATURE_PUBLIC;
import static settings.driver.WebSettings.STORY_PUBLIC_TERMINALS_MAP;

@Feature(FEATURE_PUBLIC)
@Story(STORY_PUBLIC_TERMINALS_MAP)
public class AddressTest extends WebSettings {
    private HomePage homePage;
    private MapPage mapPage;

    private final String omsk = "Омск",
            volgograd = "Волгоград",
            onlyGiveoutTerminal = getTerminalWith(IS_ONLY_GIVEOUT, NOT_ONLY_RECEIVE, NOT_TEST_TERMINAL), // Только Выдача(админка) = Только получение (сайт)
            onlyReceiveTerminal = getTerminalWith(IS_ONLY_RECEIVE, NOT_ONLY_GIVEOUT, NOT_TEST_TERMINAL), // Только Прием (админка) = Только отправка (сайт)
            transportTerminal = getTerminalWith(NOT_OFFICE, IS_RECEIVE_AND_GIVEOUT, NOT_STORAGE, NOT_ONLY_GIVEOUT, NOT_ONLY_GIVEOUT), // Транпортный терминал
            storageTerminal = getTerminalWith(NOT_OFFICE, NOT_RECEIVE_AND_GIVEOUT, IS_STORAGE, NOT_ONLY_GIVEOUT, NOT_ONLY_GIVEOUT), // Склад
            officeTerminal = getTerminalWith(IS_OFFICE, NOT_RECEIVE_AND_GIVEOUT, NOT_STORAGE, NOT_ONLY_GIVEOUT, NOT_ONLY_GIVEOUT); // Офис

    @BeforeClass
    public void start() {
        homePage = new HomePage();
        mapPage = new MapPage();
    }

    @Description("Представление раздела \"Адреса\"")
    @TmsLink("000012")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000012() {
        startTest();
        mapPage
                .checkBaseElements()
                .checkMatchDropdownFieldText("Терминалы отправки грузов")
                .clickKindOfTerminal()
                .checkDropdownItemsIsVisible()
                .checkMatchKindOfTerminals(0, "Терминалы отправки грузов")
                .checkMatchKindOfTerminals(1, "Терминалы получения грузов")
                .checkMatchKindOfTerminals(2, "Склады 3PL")
                .checkMatchKindOfTerminals(3, "Офисы")
                .checkFlagKindOfTerminals(0);
    }

    @Description("Позиционирование карты терминалов")
    @TmsLink("000013")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000013() {
        String
                parnas = "Парнас",
                balasiha = "Балашиха";
        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .checkAddressTermIsPresence(volgograd)
                .openPageCity(1)
                .waitBaseElements()
                .checkAddressTermIsPresence(parnas)
                .checkAddressTermIsAbsence(volgograd)
                .openPageCity(3)
                .waitBaseElements()
                .checkAddressTermIsPresence(balasiha)
                .checkAddressTermIsAbsence(volgograd)
                .checkAddressTermIsAbsence(parnas);
    }

    @Description("Всплывающее сообщение для чек-бокса \"Груз тяжелый или крупный\"")
    @TmsLink("000014")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000014() {
        startTest();
        String textPopup = "Прием грузов от 80 кг и более 1.0 х 1.0 х 1.0 м\n" +
                "Узнайте точные параметры в карточке терминала";
        mapPage
                .hoverTextHeavyOfLarge()
                .waitPopupHeavyOfLarge()
                .checkPopupHeavyOfLargeIsVisible()
                .checkMatchTextPopupHeavyOfLarge(textPopup);
    }

    @Description("Поиск по городу")
    @TmsLink("000015")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000015() {
        startTest();
        mapPage
                .findAddress("Омск")
                .waitBaseElements()
                .checkAddressTermIsPresence(omsk)
                .checkAddressTermIsAbsence(volgograd);
    }

    @Description("Поиск по району города")
    @TmsLink("000016")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000016() {
        startTest();
        mapPage
                .findAddress("Россия, Омская область, Большереченский район, село Ингалы")
                .checkAddressTermIsAbsence(omsk)
                .findAddress("Россия, Омск, Центральный округ")
                .waitBaseElements()
                .checkAddressTermIsPresence(omsk);
    }

    @Description("Поиск по улице города")
    @TmsLink("000017")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000017() {
        startTest();
        mapPage
                .findAddress("Россия, Омск, улица Крупской")
                .checkAddressTermIsAbsence(omsk)
                .findAddress("Россия, Омск, улица 10 лет Октября")
                .waitBaseElements()
                .checkAddressTermIsPresence(omsk);
    }

    @Description("Отобразить Терминалы отправки грузов")
    @TmsLink("000018")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000018() {
        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .chooseKindOfTerminal(CAN_RECEIVE)
                .checkMatchDropdownFieldText("Терминалы отправки грузов")
                .waitBaseElements()
                .checkAddressTermIsPresence(onlyReceiveTerminal)
                .checkAddressTermIsPresence(transportTerminal)
                .checkAddressTermIsAbsence(onlyGiveoutTerminal)
                .checkAddressTermIsAbsence(storageTerminal)
                .checkAddressTermIsAbsence(officeTerminal);
    }

    @Description("Отобразить Терминалы получения грузов")
    @TmsLink("000019")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000019() {
        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .chooseKindOfTerminal(CAN_GIVEOUT)
                .checkMatchDropdownFieldText("Терминалы получения грузов")
                .waitBaseElements()
                .checkAddressTermIsPresence(onlyGiveoutTerminal)
                .checkAddressTermIsPresence(transportTerminal)
                .checkAddressTermIsAbsence(onlyReceiveTerminal)
                .checkAddressTermIsAbsence(storageTerminal)
                .checkAddressTermIsAbsence(officeTerminal);
    }


    @Description("Отобразить Склады 3PL")
    @TmsLink("000020")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000020() {
        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .chooseKindOfTerminal(STORAGE)
                .checkMatchDropdownFieldText("Склады 3PL")
                .waitBaseElements()
                .checkAddressTermIsPresence(storageTerminal)
                .checkAddressTermIsAbsence(onlyGiveoutTerminal)
                .checkAddressTermIsAbsence(onlyReceiveTerminal)
                .checkAddressTermIsAbsence(transportTerminal)
                .checkAddressTermIsAbsence(officeTerminal);
    }

    @Description("Отобразить Офисы")
    @TmsLink("000021")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000021() {
        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .chooseKindOfTerminal(OFFICE)
                .checkMatchDropdownFieldText("Офисы")
                .waitBaseElements()
                .checkAddressTermIsPresence(officeTerminal)
                .checkAddressTermIsAbsence(onlyGiveoutTerminal)
                .checkAddressTermIsAbsence(onlyReceiveTerminal)
                .checkAddressTermIsAbsence(transportTerminal)
                .checkAddressTermIsAbsence(storageTerminal);
    }

    @Description("Фильтрация по габаритам груза")
    @TmsLink("000022")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000022() {
        String smallLengthTerminal = getTerminalWith(SMALL_LENGTH, HEAVY_WEIGHT),
                smallWidthTerminal = getTerminalWith(SMALL_WIDTH, HEAVY_WEIGHT),
                smallHeightTerminal = getTerminalWith(SMALL_HEIGHT, HEAVY_WEIGHT),
                lightWeightTerminal = getTerminalWith(LARGE_LENGTH, LARGE_WIDTH, LARGE_HEIGHT, LIGHT_WEIGHT),
                hugeTerminal = getTerminalWith(LARGE_LENGTH, LARGE_WIDTH, LARGE_HEIGHT, HEAVY_WEIGHT),
                nullTerminal = getTerminalWith(NO_LENGTH, NO_WIDTH, NO_HEIGHT, NO_WEIGHT);

        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .checkAddressTermIsPresence(smallLengthTerminal)
                .checkAddressTermIsPresence(smallWidthTerminal)
                .checkAddressTermIsPresence(smallHeightTerminal)
                .checkAddressTermIsPresence(lightWeightTerminal)
                .checkAddressTermIsPresence(hugeTerminal)
                .checkAddressTermIsPresence(nullTerminal)
                .clickCheckboxHeavyOrLarge()
                .waitBaseElements()
                .checkAddressTermIsAbsence(smallLengthTerminal)
                .checkAddressTermIsAbsence(smallWidthTerminal)
                .checkAddressTermIsAbsence(smallHeightTerminal)
                .checkAddressTermIsAbsence(lightWeightTerminal)
                .checkAddressTermIsPresence(hugeTerminal)
                .checkAddressTermIsAbsence(nullTerminal);
    }

    @Description("Терминал с меткой \"Партнер\" (ПВЗ)")
    @TmsLink("000023")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000023() {
        String terminal = getTerminalWith(IS_ONLY_GIVEOUT, NOT_ONLY_RECEIVE, PVZ_TERMINAL);
        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .chooseKindOfTerminal(CAN_GIVEOUT)
                .checkMatchDropdownFieldText("Терминалы получения грузов")
                .waitBaseElements()
                .checkAddressTermIsPresence(terminal)
                .checkPartnerBlockForTerminal(terminal);
    }

    @Description("Терминал с функцией \"Только отправка\"")
    @TmsLink("000024")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000024() {
        String
                terminal = getTerminalWith(IS_ONLY_GIVEOUT, NOT_ONLY_RECEIVE, NOT_TEST_TERMINAL);

        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .chooseKindOfTerminal(CAN_GIVEOUT)
                .checkMatchDropdownFieldText("Терминалы получения грузов")
                .waitBaseElements()
                .checkAddressTermIsPresence(terminal)
                .checkOnlyGiveoutBlockForTerminal(terminal);
    }

    @Description("Терминал с функцией \"Только выдача\"")
    @TmsLink("000025")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000025() {
        String terminal = getTerminalWith(NOT_ONLY_GIVEOUT, IS_ONLY_RECEIVE, NOT_TEST_TERMINAL);
        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .chooseKindOfTerminal(CAN_RECEIVE)
                .checkMatchDropdownFieldText("Терминалы отправки грузов")
                .waitBaseElements()
                .checkAddressTermIsPresence(terminal)
                .checkOnlyReceiveBlockForTerminal(terminal);
    }

    @Description("Список терминалов пуст")
    @TmsLink("000026")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000027() {
        String text = "На этом участке нет терминалов\n" +
                "Деловых Линий";
        mapPage
                .openPageCity(103)
                .waitBaseElements()
                .chooseKindOfTerminal(OFFICE)
                .checkNoTerminalBlock()
                .checkTextNoTerminalBlock(text);
    }

    public final void startTest() {
        homePage
                .openPage()
                .waitFormLogoDellin()
                .clickLinkAddresses();
        mapPage
                .waitBaseElements();
    }
}