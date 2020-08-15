package cases;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.mappage.MapPage;
import pages.mappage.TerminalCardPopup;
import settings.driver.WebSettings;
import settings.utils.Retry;

import java.util.List;

import static helpers.contacts.ContactsHelper.*;
import static helpers.contacts.condition.EnumTerminalCondition.*;
import static settings.driver.WebSettings.FEATURE_PUBLIC;
import static settings.driver.WebSettings.STORY_PUBLIC_TERMINALS_MAP;

@Feature(FEATURE_PUBLIC)
@Story(STORY_PUBLIC_TERMINALS_MAP)
public class AddressTerminalTest extends WebSettings {
    private MapPage mapPage;
    private TerminalCardPopup terminalCard;

    @BeforeClass
    public void start() {
        mapPage = new MapPage();
        terminalCard = new TerminalCardPopup();
    }

    @Description("Наименование терминала")
    @TmsLink("000001")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000001() {
        String terminal = getTerminalWith();
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения наименования терминала", () ->
                terminalCard.checkMatchHeader(terminal));
    }

    @Description("Адрес терминала")
    @TmsLink("000002")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000002() {
        String
                terminal = getTerminalWith(NOT_EMPTY_ADDRESS),
                address = getAddressOfTerminal(terminal);
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения адреса терминала", () ->
                terminalCard.checkMatchTerminalAddress(address));
    }

    @Description("Адрес терминала (не заполнен)")
    @TmsLink("000003")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000003() {
        String
                terminal = getTerminalWith(EMPTY_ADDRESS),
                address = getAddressOfTerminal(terminal).trim(); // при пустом адресе пробел перед координатами отсутствует.
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения пустого адреса терминала", () ->
                terminalCard.checkMatchTerminalAddress(address));
    }

    @Description("Координаты")
    @TmsLink("000004")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000004() {
        String
                terminal = getTerminalWith(NOT_EMPTY_COORDINATES),
                address = getAddressOfTerminal(terminal);
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения адреса с координатами", () ->
                terminalCard.checkMatchTerminalAddress(address));
    }

    @Description("Текущее время (+4 по МСК)")
    @TmsLink("000005")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000005() {
        String terminal = getTerminalWith(TIMESHIFT_ABOVE_ZERO);
        int timezone = getTimezone(terminal);
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения таймзоны терминала", () ->
                terminalCard.checkMatchTimeZone(timezone, terminal));
    }

    @Description("Текущее время (+0 по МСК)")
    @TmsLink("000006")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000006() {
        String terminal = getTerminalWith(TIMESHIFT_EQUAL_ZERO);
        int timezone = getTimezone(terminal);
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения таймзоны терминала", () ->
                terminalCard.checkMatchTimeZone(timezone, terminal));
    }

    @Description("Текущее время (-1 по МСК)")
    @TmsLink("000007")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000007() {
        String terminal = getTerminalWith(TIMESHIFT_LESS_THAN_ZERO);
        int timezone = getTimezone(terminal);
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения таймзоны терминала", () ->
                terminalCard.checkMatchTimeZone(timezone, terminal));
    }

    @Description("Телефоны")
    @TmsLink("000008")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000008() {
        String terminal = getTerminalWith(PHONES_IS_EXIST);
        List<String> phones = getTerminalPhones(terminal);
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения телефонов для терминала", () ->
                terminalCard.checkMatchTerminalPhones(phones));
    }

    @Description("Телефон (не указан)")
    @TmsLink("000009")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000009() {
        String terminal = getTerminalWith(PHONES_IS_EMPTY);
        List<String> phones = getTerminalPhones(terminal);
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения дефолтного телефона для терминала", () ->
                terminalCard.checkDefaultTerminalPhone(phones));
    }

    @Description("Емейл")
    @TmsLink("000010")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000010() {
        String terminal = getTerminalWith(EMAIL_IS_EXIST);
        String email = getTerminalEmail(terminal);
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить корректность отображения email для терминала", () ->
                terminalCard.checkMatchTerminalEmail(email));
    }

    @Description("График работы терминала (не заполнен)")
    @TmsLink("000011")
    @Test(retryAnalyzer = Retry.class)
    public final void test_000011() {
        String terminal = getTerminalWith(WORKTABLES_IS_EMPTY);
        step("Step 0. Предусловия", () ->
                startTest(terminal));
        step("Step 1. Проверить отсутствие расписания для терминала", () ->
                terminalCard
                        .checkTerminalScheduleNotDisplayed()
                        .checkScheduleTitleNotDisplayed());
    }

    public final void startTest(String terminal) {
        mapPage
                .openPageCity(0)
                .waitBaseElements()
                .clickTerminalName(terminal)
                .waitTerminalCard();
    }
}
