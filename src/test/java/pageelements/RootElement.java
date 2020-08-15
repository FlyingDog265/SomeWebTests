package pageelements;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

public class RootElement extends BaseElement<RootElement> {

    public RootElement(By by) {
        super(by);
    }

    public RootElement(String cssSelector) {
        super(cssSelector);
    }

    public SelenideElement findChild(By by) {
        return element.find(by);
    }

    public ElementsCollection findAllChild(By by) {
        return element.findAll(by);
    }

}
