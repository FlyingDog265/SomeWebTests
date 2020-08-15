package pageelements;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

public class ItemList extends BaseElement {

    public ItemList(By by) {
        super(by);
    }

    public ItemList(String cssSelector) {
        super(cssSelector);
    }

    // Получение всех элементов списка
    public ElementsCollection getItems() {
        return element.findAll("li");
    }

    // Ожидание наполнения списка
    public ElementsCollection waitSizeGreaterThan(int value) {
        return this.getItems()
                .shouldHave(CollectionCondition.sizeGreaterThan(value));
    }

}
