package pageelements;

import org.openqa.selenium.By;

public class Image extends BaseElement {

    public Image(By by) {
        super(by);
    }

    public Image(String cssSelector) {
        super(cssSelector);
    }

    // Получение src из картинки
    public String getSrc() {
        return element.getAttribute("src");
    }

    // Метод для загрузки изображений через hide element
    public Image sendImage(String urlToFile) {
        element.sendKeys(urlToFile);
        return this;
    }

}
