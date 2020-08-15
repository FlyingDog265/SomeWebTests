package pageelements;

import org.openqa.selenium.By;

public class RadioButton extends BaseElement {

    public RadioButton(By by) {
        super(by);
    }

    public RadioButton(String cssSelector) {
        super(cssSelector);
    }

    public String getCssValue(String propertyName) {
        return element.getCssValue(propertyName);
    }

}
