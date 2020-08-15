package pageelements;

import org.openqa.selenium.By;

public class Button extends BaseElement {

    public Button(By by) {
        super(by);
    }

    public Button(String cssSelector) {
        super(cssSelector);
    }

}
