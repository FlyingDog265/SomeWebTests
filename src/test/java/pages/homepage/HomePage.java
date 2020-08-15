package pages.homepage;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.testng.Assert;
import pageelements.Button;
import pageelements.Icon;
import pageelements.ItemList;
import pageelements.Link;
import pages.BasePage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Главная страница сайта ДЛ
 */
@SuppressWarnings("UnusedReturnValue")
public class HomePage extends BasePage {
    private static final String PAGE_URL = BASE_URL;

    /**
     * =================================================================================================================
     * Список блоков главной страницы. При вызове в тесте позволяет работать с методами блока.
     * BannersBlock - баннеры
     * CalculatorBlock - Экспресс-калькулятор
     * CustomContentBlock - блок ссылок и контента под калькулятором
     * =================================================================================================================
     */

    public BannersBlock getBannersBlock() {
        return new BannersBlock();
    }

    public CalculatorBlock getCalculatorBlock() {
        return new CalculatorBlock();
    }

    public CustomContentBlock getCustomContentBlock() {
        return new CustomContentBlock();
    }

    @Override
    public HomePage openPage() {
        super.openPage();
        open(PAGE_URL);
        clickHideBannerButtonIfDisplayed();
        return HomePage.this;
    }

    @Override
    public BasePage checkHeader() {
        // У страницы нет заголовка
        return null;
    }

    @Step("Переход по ссылке из блока 'Личный кабинет' через футер: {0}")
    public void clickCabinetLinkByFooter(FooterCabinetLink footerLink) {
        listCabinetLinks.getItems()
                .find(text(footerLink.getTitle()))
                .find("a")
                .click();
    }

    @Step("Клик по ссылке 'Связаться'")
    public void clickLinkConnect() {
        linkConnect.waitClickable()
                .click();
        assertTrue(url().contains("help_desk/?main=true"), "Не верная ссылка при переходе на страницу Связаться");
    }

    @Step("Проверка состояния меню в футере сайта")
    public HomePage checkMenuItemFooter(FooterCabinetLink menuItem, boolean expected) {
        boolean actual = listCabinetLinks
                .waitSizeGreaterThan(5)
                .stream()
                .filter(item -> item.getText().contains(menuItem.getTitle()))
                .allMatch(item -> item.find("a").exists());

        Assert.assertEquals(actual, expected, "Не совпадает состояние: " + menuItem.getTitle());
        return this;
    }

    @Step("Проверка отображения логотипа ДЛ")
    public HomePage checkLogoDellin() {
        assertTrue(logoDellin.isDisplayed(), "Не отображается логотип ДЛ");
        return this;
    }

    @Step("Клик на логотип ДЛ и проверка, что переход осуществлен")
    public HomePage clickLogoDellin() {
        logoDellin.waitClickable()
                .click();
        assertTrue(url().contains(BASE_URL), "Не осуществлен переход на главную страницу");
        return this;
    }

    @Step("Проверка отображения ссылки Адреса")
    public HomePage checkLinkAddresses() {
        assertTrue(linkAddresses.isDisplayed(), "Не отображается ссылка Адреса");
        return this;
    }

    @Step("Проверка отображения ссылки Связаться")
    public HomePage checkLinkConnect() {
        assertTrue(linkConnect.isDisplayed(), "Не отображается ссылка Связаться");
        return this;
    }

    @Step("Клик по ссылке 'Адреса'")
    public HomePage clickLinkAddresses() {
        linkAddresses.waitVisible()
                .click();
        assertTrue(linkAddresses.getUrl().contains("contacts"), "Не верный url в Адреса");
        return this;
    }

    @Step("Клик на кнопку '3PL Комплексная логистика'")
    public HomePage clickButtonIntegratedLogistics() {
        linkStorageServices.waitClickable()
                .click();
        return this;
    }

    @Step("Клик на кнопку 'FPL Перевозки еврофурой'")
    public HomePage clickButtonEuroTruck() {
        linkTruck.waitClickable()
                .click();
        return this;
    }

    @Step("Проверка отображения кнопки скролла")
    public HomePage waitButtonScrollPage() {
        buttonScrollPage.waitVisible();
        return this;
    }

    @Step("Проверка не отображения кнопки скролла")
    public HomePage waitButtonScrollPageIsAbsent() {
        buttonScrollPage.shouldNot(visible);
        return this;
    }

    @Step("Клик на кнопку скролла страницы вверх")
    public HomePage clickButtonScrollPage() {
        buttonScrollPage.waitClickable()
                .click();
        return this;
    }

    @Step("Скролл главной страницы вниз")
    public HomePage scrollToFooter() {
        listCompanyLinks.scrollTo();
        return this;
    }


    @Step("Наведение курсора из блока 'Личный кабинет' через футер: {0}")
    public void hoveringFromBlockLKThroughFooter(FooterCabinetLink footerLink) {
        listCabinetLinks.getItems()
                .find(text(footerLink.getTitle()))
                .hover();
    }

    @Step("Клик на кнопку Активировать")
    public HomePage clickActivateButton() {
        buttonActivate.waitVisible()
                .click();
        assertTrue(url().contains("cabinet/account/access/"), "Не осуществлен переход на страницу Активации полного доступа");
        return this;
    }

    @Step("Ожидание отображения логотипа ДЛ")
    public HomePage waitFormLogoDellin() {
        logoDellin.waitVisible();
        return this;
    }


    public void clearCache() {
        String url = BASE_URL + "api/local/v1/clear_cache/";
        Response resp = given()
                .contentType(JSON)
                .when()
                .get(url);
        Assert.assertEquals(resp.jsonPath().
                get("result").toString(), "success", "Проблемы со сбросом кеша");
    }


    @Step("Клик на кнопку скрыть в баннере Деловые Линии работают на «выходной неделе» без изменения графика")
    public HomePage clickHideBannerButtonIfDisplayed() {
        if (buttonHideInfoSite.isDisplayed()) {
            buttonHideInfoSite.click();
        }
        return this;
    }

    @Step("Проверить буковку в иконочке")
    public HomePage checkLetterInIcon(String letter) {
        assertEquals(iconAuth.getText(), letter, "Символ в иконке авторизации не соответсвует ожидаемому!");
        return HomePage.this;
    }

    @Step("Проверка иконки и ссылки 'Доступно в Google Play'")
    public HomePage checkLinkValueGooglePlay(String excepted) {
        assertTrue(linkGoogle.isClickable(), "Иконка 'Доступно в Google Play' не кликабельна!");
        assertEquals(linkGoogle.getUrl(), excepted, "Ссылка на магазин Google не равна ожидаемому значению!");
        return HomePage.this;
    }

    @Step("Проверка иконки и ссылки 'Загрузите в App Store'")
    public HomePage checkLinkValueAppStore(String excepted) {
        assertTrue(linkApple.isClickable(), "Иконка 'Загрузите в App Store' не кликабельна!");
        assertEquals(linkApple.getUrl(), excepted, "Ссылка на магазин Apple не равна ожидаемому значению!");
        return HomePage.this;
    }

    @Step("Проверка иконки и ссылки 'BIA-Technologies'")
    public HomePage checkLinkValueBIA(String excepted) {
        assertTrue(iconBIA.isClickable(), "Иконка 'BIA-Technologies' не кликабельна!");
        assertEquals(iconBIA.getUrl(), excepted, "Ссылка 'BIA-Technologies' не равна ожидаемому значению!");
        return HomePage.this;
    }

    @Step("Проверка иконки 'Facebook'")
    public HomePage checkIconFacebook(String excepted) {
        assertTrue(iconFacebook.isClickable(), "Иконка 'Facebook' не кликабельна!");
        assertTrue(iconFacebook.getUrl().contains(excepted), "Ссылка на Facebook не содержит " + excepted + "!");
        return HomePage.this;
    }

    @Step("Проверка иконки 'VK'")
    public HomePage checkIconVK(String excepted) {
        assertTrue(iconVK.isClickable(), "Иконка 'VK' не кликабельна!");
        assertTrue(iconVK.getUrl().contains(excepted), "Ссылка на VK не содержит " + excepted + "!");
        return HomePage.this;
    }

    @Step("Проверка иконки 'Twitter'")
    public HomePage checkIconTwitter(String excepted) {
        assertTrue(iconTwitter.isClickable(), "Иконка 'Twitter' не кликабельна!");
        assertTrue(iconTwitter.getUrl().contains(excepted), "Ссылка на Twitter не содержит " + excepted + "!");
        return HomePage.this;
    }

    @Step("Проверка иконки 'Instagram'")
    public HomePage checkIconInstagram(String excepted) {
        assertTrue(iconInstagram.isClickable(), "Иконка 'Instagram' не кликабельна!");
        assertTrue(iconInstagram.getUrl().contains(excepted), "Ссылка на Instagram не содержит " + excepted + "!");
        return HomePage.this;
    }

    /**
     * =================================================================================================================
     * Header
     * =================================================================================================================
     */

    // Кнопка скрыть
    private static final Button buttonHideInfoSite = new Button(
            "button[class^=HelperInfoSite__StyledButtonHidden]"
    );

    // Логотип ДЛ
    private static final Icon logoDellin = new Icon(
            "a[class^=\"Logo\"]"
    );

    // Ссылка "Перевозки еврофурой"
    private static final Link linkTruck = new Link(
            "[href^='/truck-transportation/']"
    );

    // Ссылка "Сборные грузы"
    private static final Link linkCargo = new Link(
            "div[class^=\"Banners\"] > a:nth-of-type(1)"
    );

    // Ссылка "Комплексная логистика"
    private static final Link linkStorageServices = new Link(
            "[href^='/warehouse-services/']"
    );

    // Ссылка "Адреса"
    private static final Link linkAddresses = new Link(
            "div[class^=\"Header_\"] div[class^=\"Contacts\"] a:nth-of-type(1)"
    );

    // Ссылка "Связаться"
    private static final Link linkConnect = new Link(
            "div[class^=\"Header_\"] div[class^=\"Contacts\"] a:nth-of-type(2)"
    );

    // Иконка авторизации
    private static final Icon iconAuth = new Icon(
            "[class^=Header] [class*=uthorizedUserIcon]"
    );

    /**
     * =================================================================================================================
     * Footer
     * =================================================================================================================
     */

    // Список ссылок "Компания"
    private static final ItemList listCompanyLinks = new ItemList(
            "div[class^=Footer] div[class^=sc-htp]:nth-of-type(1) ul[class^=Menu]"
    );

    // Список ссылок "Личный кабинет"
    private static final ItemList listCabinetLinks = new ItemList(
            "div[class^=Footer] div[class^=sc-htp]:nth-of-type(2) ul[class^=Menu] > li > ul[class^=Submenu]"
    );

    // Ссылки название ссылок блока "Личный кабинет"
    public enum FooterCabinetLink {
        DASHBOARD("Лента событий"),
        PERSONAL_AREA("Личный кабинет"),
        ORDERS("Мои заказы"),
        ADDRESS_BOOK("Адресная книга"),
        MUTUAL_CALCULATION("Взаиморасчеты"),
        REPORTS_SERVICE_QUALITY("Отчет по качеству услуг"),
        REPORTS("Отчеты"),
        REQUEST_DOCS("Заявки на документы"),
        DIALOGUE("Переписка с менеджером"),
        CONTRACT_AGREEMENT("Согласование договоров"),
        CLAIMS("Претензии"),
        SETTINGS("Настройки"),
        REGISTRATION("Регистрация"),
        QUIT("Выход");
        private String title;

        FooterCabinetLink(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

    }

    // Список ссылок "Правовая информация"
    private static final ItemList listLegalInfoLinks = new ItemList(
            "div[class^=\"Footer\"] div[class^=\"sc-htp\"]:nth-of-type(3) ul[class^=\"Menu\"]"
    );
    // Кнопка скролла страницы вверх
    private static final Button buttonScrollPage = new Button(
            "[class^=ScrollPage]"
    );

    // Иконка "Доступно в Google Play"
    private final Link linkGoogle = new Link(
            "div[class*='Banners__BannerBox']:nth-of-type(2) a:nth-of-type(1)"
    );

    // Иконка "Загрузите в App Store"
    private final Link linkApple = new Link(
            "div[class*='Banners__BannerBox']:nth-of-type(2) a:nth-of-type(2)"
    );

    // Иконка Facebook
    private final Link iconFacebook = new Link(
            "div[class*='Banners__BannerBox']:nth-of-type(3) a:nth-of-type(1)"
    );

    // Иконка VK
    private final Link iconVK = new Link(
            "div[class*='Banners__BannerBox']:nth-of-type(3) a:nth-of-type(2)"
    );

    // Иконка Twitter
    private final Link iconTwitter = new Link(
            "div[class*='Banners__BannerBox']:nth-of-type(3) a:nth-of-type(3)"
    );

    // Иконка Instagram
    private final Link iconInstagram = new Link(
            "div[class*='Banners__BannerBox']:nth-of-type(3) a:nth-of-type(4)"
    );

    // Иконка BIA Technologies
    private final Link iconBIA = new Link(
            "div[class*='Banners__BannerBox']:nth-of-type(4) a:nth-of-type(1)"
    );

    // Кнопка Активировать
    private static final Button buttonActivate = new Button(
            "[title=Активировать]"
    );

}
