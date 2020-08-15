package pageelements;

import org.openqa.selenium.By;

public class DropdownField extends TextField {

    public DropdownField(By by) {
        super(by);
    }

    public DropdownField(String cssSelector) {
        super(cssSelector);
    }

    // Выбор опции из списка по индексу
    public DropdownField selectOptionsIndex(int index) {
        element.selectOption(index);
        return this;
    }

    // Выбор опции из списка по наименованию
    public DropdownField selectOptionsName(String name) {
        element.selectOption(name);
        return this;
    }

}
