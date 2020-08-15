package pageelements;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@SuppressWarnings("ALL")
public abstract class BaseElement<T extends BaseElement> {
    private Condition clickable = and("can be clicked", visible, enabled);

    protected SelenideElement element;
    protected ElementsCollection elements;

    // Constructors
    BaseElement(By by) {
        element = $(by);
        elements = $$(by);
    }

    BaseElement(String cssSelector) {
        element = $(cssSelector);
        elements = $$(cssSelector);
    }

    // Methods
    public T waitUntilClickable(long timeoutMilliseconds) {
        element.waitUntil(clickable, timeoutMilliseconds);
        return (T) this;
    }

    public T waitClickable() {
        element.shouldBe(clickable);
        return (T) this;
    }

    public T waitVisible() {
        element.shouldBe(visible);
        return (T) this;
    }

    public T waitNotVisible() {
        element.shouldBe(not(visible));
        return (T) this;
    }

    public T click() {
        element.click();
        return (T) this;
    }

    public T waitUntil(Condition condition, long timeoutMilliseconds) {
        element.waitUntil(condition, timeoutMilliseconds);
        return (T) this;
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }

    public boolean notDisplayed() {
        return !element.isDisplayed();
    }

    public boolean notExist() {
        return !element.is(exist);
    }

    public boolean isClickable() {
        return element.is(clickable);
    }

    public SelenideElement getChild(By by) {
        return element.find(by);
    }

    public String getText() {
        return element.getText();
    }

    public T selectOption(String name) {
        element.selectOption(name);
        return (T) this;
    }

    public T selectOption(int position) {
        element.selectOption(position);
        return (T) this;
    }

    public String getValue() {
        return element.getValue();
    }

    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

    public String getAttribute(String name) {
        return element.getAttribute(name);
    }

    public T hover() {
        element.hover();
        return (T) this;
    }

    public T scrollTo() {
        element.scrollTo();
        return (T) this;
    }

    public T shouldNot(Condition... condition) {
        element.shouldNot(condition);
        return (T) this;
    }

    public T shouldHave(Condition... condition) {
        element.shouldHave(condition);
        return (T) this;
    }

    public boolean is(Condition condition) {
        return element.is(condition);
    }

    public boolean has(Condition condition) {
        return element.has(condition);
    }

    public SelenideElement get(int position) {
        return elements.get(position);
    }

    public ElementsCollection getElements() {
        return elements;
    }

}
