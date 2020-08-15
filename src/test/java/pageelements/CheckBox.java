package pageelements;

import org.openqa.selenium.By;

public class CheckBox extends BaseElement {

    public CheckBox(By by) {
        super(by);
    }

    public CheckBox(String cssSelector) {
        super(cssSelector);
    }
}
