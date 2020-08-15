package pageelements;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

public class Item extends BaseElement {
    protected ElementsCollection elements;

    Item(By by) {
        super(by);
    }

    public Item(String cssSelector) {
        super(cssSelector);
    }

}
