package settings.driver;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import okhttp3.Response;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import settings.testrail.DataProviderHelper;
import settings.testrail.TestRailHelper;
import settings.testrail.TestStatus;
import settings.utils.PropertyLoader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import static com.google.common.io.Files.toByteArray;

@Listeners({WebSettings.class})
public class WebSettings implements ITestListener {

    protected String baseUrl;
    protected String hub;
    protected String selenoidStart;
    protected String materialUrl;

    public static final String FEATURE_PUBLIC = "Публичный сайт";

    public static final String STORY_PUBLIC_MAINPAGE = "Главная страница";
    public static final String STORY_PUBLIC_TERMINALS_MAP = "Карта терминалов";


    @BeforeClass
    public void init() {

        Configuration.timeout = 15000;

        baseUrl = PropertyLoader.loadProperty("site.url");
        hub = PropertyLoader.loadProperty("selenoid.hub");
        selenoidStart = PropertyLoader.loadProperty("selenoid");

        Configuration.baseUrl = baseUrl;

        WebDriverFactory.setUpDriver(selenoidStart, hub);
        Configuration.timeout = 15000;
        Configuration.pageLoadStrategy = "eager";

        materialUrl = new File("src/test/resources/material").getAbsolutePath() + "/";
    }

    @AfterMethod
    public void clearCookies() {
        Selenide.closeWebDriver();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private byte[] makeScreen() {
        try {
            File screenshot = Screenshots.takeScreenShotAsFile();
            return toByteArray(screenshot);
        } catch (Exception e) {
            Logger.getLogger("Ошибка в методе makeScreen");
        }
        return new byte[0];
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        // В данный момент не требует реализации
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        sendResult(iTestResult, TestStatus.AUTO_TEST_PASSED);
    }

    @Override
    public final void onTestFailure(ITestResult result) {
        makeScreen();
        sendResult(result, TestStatus.AUTO_TEST_FAILED);
    }

    private void makeLog(String message) {
        Logger logger = Logger.getLogger(this.getClass().getSimpleName());
        String log = "***** TestRail response *****\n\n" + message + "\n\n*****";
        logger.info(log);
    }

    private void sendResult(ITestResult result, TestStatus status) {
        String testRunId = PropertyLoader.loadTestRunId();

        if (testRunId != null && !testRunId.equals("null") && !testRunId.equals("")) {
            Method method = result.getMethod().getConstructorOrMethod().getMethod();
            String id;

            if (method.isAnnotationPresent(TmsLink.class)) {
                id = method.getAnnotation(TmsLink.class).value().replaceAll("[^0-9]+", "");
            } else if (method.isAnnotationPresent(TmsLinks.class)) {
                int annotationIndex = DataProviderHelper.getAnnotationIndex(method.getName());
                id = method.getAnnotation(TmsLinks.class).value()[annotationIndex].value();
            } else return;

            String comment;
            String defect = null;
            if (status.equals(TestStatus.AUTO_TEST_PASSED)) comment = "Passed";
            else {
                if (method.isAnnotationPresent(Issue.class)) {
                    defect = method.getAnnotation(Issue.class).value();
                    comment = "https://jira.bia-tech.ru/browse/" + defect;
                    status = TestStatus.FAILED;
                } else {
                    comment = result.getThrowable().getMessage();
                }
            }

            int resultId;
            try {
                Response response = TestRailHelper.sendTestResult(testRunId, id, status.getStatus(), comment, defect);
                if (response.body() != null) {
                    String stringBody = response.body().string();
                    makeLog(stringBody);
                    resultId = (int) new JSONObject(stringBody).get("id");

                    if (!status.equals(TestStatus.PASSED)) {
                        TestRailHelper.attachScreenshot(resultId, result);
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        // В данный момент не требует реализации
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        // В данный момент не требует реализации
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        // В данный момент не требует реализации
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        // В данный момент не требует реализации
    }

    protected boolean isAlertPresent() {
        try {
            Selenide.switchTo().alert();
            return true;
        } catch (Exception Ex) {
            return false;
        }
    }

    public void alertAccept() {
        if (isAlertPresent()) {
            Selenide.confirm();
        }
    }

    /**
     * Функциональный интерфейс для вызова в методе step
     */
    protected interface IStep {
        void doStep();
    }

    /**
     * Обозначение шага равного шагу в ТК
     *
     * @param description - описание шага (что происходит в шаге)
     * @param step        - тело шага, методы страниц и тд.
     */
    @Step("{description}")
    protected static void step(String description, IStep step) {
        try {
            step.doStep();
        } catch (Throwable t) {
            System.out.println(description + " FAILED");
            throw t;
        }
    }
}
