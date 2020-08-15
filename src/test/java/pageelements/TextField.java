package pageelements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

public class TextField extends BaseElement<TextField> {

    public TextField(By by) {
        super(by);
    }

    public TextField(String cssSelector) {
        super(cssSelector);
    }

    // Ввод текста в поле
    public TextField setText(String text) {
        element.sendKeys(text);
        return this;
    }

    // Проверка текста в поле
    public TextField checkValue(String expected) {
        String actual = element.getValue();
        Assert.assertEquals(actual, expected, "Некорректный текст в поле");
        return this;
    }

    // Очистка поля
    public TextField clear() {
        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        return this;
    }

    // Выделение всего текста в поле
    public TextField selectAllText() {
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        return this;
    }

    // Очистки поля через BackSpace
    public TextField manualClear() {
        long start = System.currentTimeMillis();
        while (!element.getValue().isEmpty() && (System.currentTimeMillis() - start) < 10000) {
            element.sendKeys(Keys.BACK_SPACE);
        }
        return this;
    }

    // Нажатие "Enter" в поле
    public TextField pressEnter() {
        element.pressEnter();
        return this;
    }

    // Нажатие "Tab" в поле
    public TextField pressTab() {
        element.pressTab();
        return this;
    }

}
