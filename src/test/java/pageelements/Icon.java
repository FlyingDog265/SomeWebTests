package pageelements;

import org.openqa.selenium.By;

public class Icon extends BaseElement {

    public Icon(By by) {
        super(by);
    }

    public Icon(String cssSelector) {
        super(cssSelector);
    }

    // Получение src из картинки
    public String getSrc() {
        return element.getAttribute("src");
    }

    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

}
