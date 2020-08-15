package pageelements;

import org.openqa.selenium.By;

public class Link extends BaseElement {

    public Link(By by) {
        super(by);
    }

    public Link(String cssSelector) {
        super(cssSelector);
    }

    // Получение url из ссылки
    public String getUrl() {
        return element.getAttribute("href");
    }

}
