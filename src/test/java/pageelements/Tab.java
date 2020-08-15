package pageelements;

import org.openqa.selenium.By;

public class Tab extends BaseElement {

    public Tab(By by) {
        super(by);
    }

    public Tab(String cssSelector) {
        super(cssSelector);
    }

}
