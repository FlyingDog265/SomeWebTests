package cases;

import com.codeborne.selenide.Selenide;
import helpers.expressCalculation.ExpressCalculationHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.openqa.selenium.html5.Location;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.homepage.HomePage;
import settings.driver.WebSettings;
import settings.utils.Retry;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helpers.LocationHelper.setLocation;
import static helpers.expressCalculation.ExpressCalculationHelper.TypeOfTransport.*;
import static helpers.expressCalculation.ExpressCalculationHelper.typeOfDelivery.*;
import static settings.driver.WebSettings.FEATURE_PUBLIC;
import static settings.driver.WebSettings.STORY_PUBLIC_MAINPAGE;

@Feature(FEATURE_PUBLIC)
@Story(STORY_PUBLIC_MAINPAGE)
public class MainPageCalculatorTest extends WebSettings {
    private HomePage homePage;
    private ExpressCalculationHelper calculation;

    @BeforeClass
    public void start() {
        homePage = new HomePage();
        calculation = new ExpressCalculationHelper();
    }

    String cladrMsk = "7700000000000000000000000",
            cladrSpb = "7800000000000000000000000",
            cladrOmsk = "5500000100000000000000000",
            cladrKalachinsk = "5500800100000000000000000";

    @Description("Калькулятор на главной. Отображение стоимости перевозки и возможности расчета при дефолтном направлении")
    @TmsLink("000028")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000029() {
        step("Step 1. Открыть главную страницу сайта. Дождаться прогрузки калькулятора.", () ->
                homePage
                        .openPage()
                        .waitFormLogoDellin()
                        .getCalculatorBlock()
                        .checkMatchCityDerival("Москва")
                        .checkMatchCityArrival("Санкт-Петербург")
                        .waitCalculate()
                        .checkTerminalPriceAndPeriodFromButton()
                        .checkHomePriceAndPeriodFromButton()
                        .checkButtonCalculateEnabled());
    }

    @Description("Калькулятор на главной. Интерфейс и взаимодействие элементов.")
    @TmsLink("000030")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000030() {
        step("Step 1. Открыть главную страницу сайта. Дождаться прогрузки калькулятора.", () ->
                homePage
                        .openPage()
                        .waitFormLogoDellin()
                        .getCalculatorBlock()
                        .checkMatchCityDerival("Москва")
                        .checkMatchCityArrival("Санкт-Петербург")
                        .checkMatchMessageDirection("На этом направлении действуют специальные предложения")
                        .checkLinkMessageDirection("announcements?city_id=")
                        .checkMatchTextTitleOfTheCalculator("Рассчитать и оформить заявку*")
                        .checkTitleOfTheCalculator()
                        .checkElementsBlockTerminal()
                        .checkElementsBlockDoorToDoor()
                        .checkElementsBlockDedicatedTransport()
                        .checkTextUnderTheCalculator());
        step("Step 2. Ввести направление отличное от направления по умолчанию: например: Омск-Владивосток", () -> {
            String firstPrice = homePage.getCalculatorBlock().receivePriceDoorToDoor();
            String firstTime = homePage.getCalculatorBlock().receivePeriodDoorToDoor();
            homePage
                    .getCalculatorBlock()
                    .chooseCityDerival("Омск")
                    .chooseCityArrival("Владивосток")
                    .waitCalculate();
            String secondPrice = homePage.getCalculatorBlock().receivePriceDoorToDoor();
            String secondTime = homePage.getCalculatorBlock().receivePeriodDoorToDoor();
            Assert.assertNotEquals(firstPrice, secondPrice, "После смены населенных пунктов цены не обновились");
            Assert.assertNotEquals(firstTime, secondTime, "После смены населенных пунктов сроки доставки не обновились");
        });
        step("Step 3. Перезагрузить страницу, проверить направление в калькуляторе", () -> {
            refresh();
            homePage
                    .waitFormLogoDellin()
                    .getCalculatorBlock()
                    .scrollToTitleOfTheCalculator()
                    .checkMatchCityDerival("Омск г (Омская обл.)")
                    .checkMatchCityArrival("Владивосток г (Приморский край)");
        });
        step("Step 4. Навести курсор и кликнуть по виду перевозки для которого не пришла стоимость.", () ->
                homePage
                        .getCalculatorBlock()
                        .clickHomeExpress()
                        .checkHomeExpressSelected()
                        .checkHomeStandardIsNotSelected()
                        .checkButtonCalculateDisabled()
                        .checkDoorToDoorNotAvailable());
        step("Step 5. Навести курсор и кликнуть по виду перевозки для которого пришла стоимость.", () ->
                homePage
                        .getCalculatorBlock()
                        .clickHomeStandard()
                        .waitCalculate()
                        .checkHomeExpressNotSelected()
                        .checkHomeStandardIsSelected()
                        .checkPriceDoorToDoor()
                        .checkButtonCalculateEnabled());
        step("Step 6. Переключить фокус в один из видов перевозки вкладки \"Между терминалами\" и изменить направление на отличное от текущего.", () ->
                homePage
                        .getCalculatorBlock()
                        .clickTerminalStandard()
                        .chooseCityArrival("Новосибирск")
                        .waitCalculate()
                        .checkHomeStandardIsSelected());
        step("Step 7. Переключить фокус в один из видов перевозки вкладки \"Выделенный транспорт\" и изменить направление на отличное от текущего.", () ->
                homePage
                        .getCalculatorBlock()
                        .clickTransportTruck()
                        .chooseCityArrival("Тюмень")
                        .waitCalculate()
                        .checkHomeStandardIsSelected());
        step("Step 8. Установить фокус во вкладке \"Между терминалами\" - вид перевозки Стандарт", () ->
                homePage
                        .getCalculatorBlock()
                        .clickTerminalStandard()
                        .checkMatchTitleTerminalsSelected("Автотранспортная перевозка"));
        step("Step 9. Установить фокус во вкладке \"Между терминалами\" - вид перевозки Экспресс", () ->
                homePage
                        .getCalculatorBlock()
                        .clickTerminalExpress()
                        .checkMatchTitleTerminalsSelected("Срочная перевозка"));
        step("Step 10. Установить фокус во вкладке \"Между терминалами\" - вид перевозки Авиа", () ->
                homePage
                        .getCalculatorBlock()
                        .clickTerminalAvia()
                        .checkMatchTitleTerminalsSelected("Перевозка авиатранспортом"));
        step("Step 11. Установить фокус во вкладке \"От адреса до адреса\" - вид перевозки Стандарт", () ->
                homePage
                        .getCalculatorBlock()
                        .clickHomeStandard()
                        .checkMatchTitleHomeSelected("Автоперевозка с доставкой на адрес"));
        step("Step 12. Установить фокус во вкладке \"От адреса до адреса\" - вид перевозки Документы", () ->
                homePage
                        .getCalculatorBlock()
                        .clickHomeDocuments()
                        .checkMatchTitleHomeSelected("Доставка писем и документов"));
        step("Step 13. Установить фокус во вкладке \"От адреса до адреса\" - вид перевозки Малогабарит", () ->
                homePage
                        .getCalculatorBlock()
                        .clickHomePackage()
                        .checkMatchTitleHomeSelected("Доставка посылок весом до 10 кг"));
        step("Step 14. Установить фокус во вкладке \"От адреса до адреса\" - вид перевозки Экспресс", () ->
                homePage
                        .getCalculatorBlock()
                        .clickHomeExpress()
                        .checkMatchTitleHomeSelected("Срочная доставка на адрес"));
        step("Step 15. Установить фокус во вкладке \"От адреса до адреса\" - вид перевозки Авиа", () ->
                homePage
                        .getCalculatorBlock()
                        .clickHomeAvia()
                        .checkMatchTitleHomeSelected("Авиаперевозка с доставкой на адрес"));
        step("Step 16. Установить фокус во вкладке \"Выделенный транспорт\" - вид перевозки Малотоннажные ТС", () ->
                homePage
                        .getCalculatorBlock()
                        .clickTransportDelivery()
                        .checkMatchTitleTransportSelected("По вашему маршруту, машина  до 10 тонн"));
        step("Step 17. Установить фокус во вкладке \"Выделенный транспорт\" - вид перевозки Еврофура", () ->
                homePage
                        .getCalculatorBlock()
                        .clickTransportTruck()
                        .checkMatchTitleTransportSelected("По вашему маршруту, еврофура"));
        step("Step 18. Установить фокус во вкладке \"Выделенный транспорт\" - вид перевозки Почасовая аренда", () ->
                homePage
                        .getCalculatorBlock()
                        .clickTransportRent()
                        .checkMatchTitleTransportSelected("По городу и области, машина  до 10 тонн"));
        step("Step 19. Установить фокус во вкладке \"Выделенный транспорт\" - вид перевозки Контейнер", () ->
                homePage
                        .getCalculatorBlock()
                        .clickTransportContainer()
                        .checkMatchTitleTransportSelected("Контейнерная перевозка"));
    }

    @Description("Калькулятор на главной. Отображение стоимости по видам перевозки в соответствии с данными запроса к API. Между терминалами.")
    @TmsLink("000031")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000031() {
        homePage
                .openPage()
                .waitFormLogoDellin()
                .getCalculatorBlock()
                .chooseCityDerival("Москва")
                .chooseCityArrival("Санкт-Петербург")
                .waitCalculate()
                .checkButtonCalculateEnabled()
                .clickTerminalStandard()
                .checkTerminalPrices(calculation.getPriceLTL(
                        cladrMsk, cladrSpb, TERMINAL_STANDARD))
                .clickTerminalDocuments()
                .checkTerminalPrices(calculation.getPriceLTL(
                        cladrMsk, cladrSpb, TERMINAL_DOCUMENTS))
                .clickTerminalExpress()
                .checkTerminalPrices(calculation.getPriceLTL(
                        cladrMsk, cladrSpb, TERMINAL_EXPRESS))
                .clickTerminalAvia()
                .checkTerminalPrices(calculation.getPriceLTL(
                        cladrMsk, cladrSpb, TERMINAL_AVIA));
    }

    @Description("Калькулятор на главной. Отображение стоимости по видам перевозки в соответствии с данными запроса к API. От адреса до адреса.")
    @TmsLink("000032")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000032() {
        homePage
                .openPage()
                .waitFormLogoDellin()
                .getCalculatorBlock()
                .chooseCityDerival("Москва")
                .chooseCityArrival("Санкт-Петербург")
                .waitCalculate()
                .checkButtonCalculateEnabled()
                .clickHomeStandard()
                .checkHomePrices(calculation.getPriceLTL(
                        cladrMsk, cladrSpb, HOME_STANDARD))
                .clickHomeDocuments()
                .checkHomePrices(calculation.getPriceLTL(
                        cladrMsk, cladrSpb, HOME_DOCUMENT))
                .clickHomePackage()
                .checkHomePrices(calculation.getPriceLTL(
                        cladrMsk, cladrSpb, HOME_PACKAGE))
                .clickHomeExpress()
                .checkHomePrices(calculation.getPriceLTL(
                        cladrMsk, cladrSpb, HOME_EXPRESS))
                .clickHomeAvia()
                .checkHomePrices(calculation.getPriceLTL(
                        cladrMsk, cladrSpb, HOME_AVIA));
    }

    @Description("Калькулятор на главной. Отображение стоимости по видам перевозки в соответствии с данными запроса к API. Выделенный транспорт.")
    @TmsLink("000033")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000033() {
        homePage
                .openPage()
                .waitFormLogoDellin()
                .getCalculatorBlock()
                .chooseCityDerival("Омск")
                .chooseCityArrival("Калачинск")//изменил, т.к. в списке городов при вводе "Тара" первым в списке отображается, а после и выбирается "Тараз"
                .waitCalculate()
                .clickTransportDelivery()
                .checkTransportPrices(calculation.getPriceFTL(
                        cladrOmsk, cladrKalachinsk, TRANSPORT_DELIVERY))
                .clickTransportTruck()
                .checkTransportPrices(calculation.getPriceFTL(
                        cladrOmsk, cladrKalachinsk, TRANSPORT_TRUCK))
                .clickTransportRent()
                .checkTransportPrices(calculation.getPriceFTL(
                        cladrOmsk, cladrKalachinsk, TRANSPORT_RENT))
                .clickTransportContainer()
                .checkTransportPrices(calculation.getPriceFTL(
                        cladrOmsk, cladrKalachinsk, TRANSPORT_CONTAINER));
    }

    @Description("Калькулятор на главной. Отображение текста 'По запросу' для НП удаленных друг от друга, и не принадлежащих одной области.")
    @TmsLink("000034")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000034() {
        homePage
                .openPage()
                .waitFormLogoDellin()
                .getCalculatorBlock()
                .chooseCityDerival("Омск")
                .chooseCityArrival("Тихвин")
                .waitCalculate()
                .clickTransportDelivery()
                .checkTransportDifferentSubj();
    }

    @Description("Калькулятор на главной. Выпадающий список в полях ввода направления.")
    @TmsLink("000035")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000035() {
        homePage
                .openPage()
                .waitFormLogoDellin()
                .getCalculatorBlock()
                .waitCalculate()
                .checkMatchCityDerival("Москва")
                .checkMatchCityArrival("Санкт-Петербург")
                .fillCityDerival("Мос")
                .checkHintInItemList()
                .checkFirstItemList("Москва")
                .fillCityDerival("Тюм")
                .clickHomeDocuments()
                .checkMatchCityDerival("Москва")
                .waitCalculate()
                .chooseCityDerivalManually("Тюмень")
                .clickHomeStandard()
                .checkMatchCityDerival("Тюмень г (Тюменская обл.)");
    }

    @Description("Калькулятор на главной. Определение города отправки по геопозиции")
    @TmsLink("000036")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000036() {
        try {
            Location yarovoe = new Location(52.919704, 78.563568, 0);
            setLocation(yarovoe);
            homePage
                    .openPage()
                    .waitFormLogoDellin()
                    .getCalculatorBlock()
                    .scrollToTitleOfTheCalculator();
            Selenide.sleep(4000); // Потому что Геолокация применяется не сразу.
            homePage
                    .getCalculatorBlock()
                    .checkMatchCityDerival("Яровое")
                    .checkMatchCityArrival("Санкт-Петербург");
            refresh();
            homePage
                    .getCalculatorBlock()
                    .scrollToTitleOfTheCalculator()
                    .checkMatchCityDerival("Яровое")
                    .checkMatchCityArrival("Санкт-Петербург");
        } finally {
            closeWebDriver();
        }
    }

}
