package pageelements;

import org.openqa.selenium.By;

public class TextElement extends BaseElement {

    public TextElement(By by) {
        super(by);
    }

    public TextElement(String cssSelector) {
        super(cssSelector);
    }

    public String getColorOfText() {
        return element.getCssValue("color");
    }

}
