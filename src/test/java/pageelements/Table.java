package pageelements;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

public class Table extends BaseElement {

    // Класс для работы с таблицами
    // Корневой элемент должен иметь тэг "table"

    public Table(By by) {
        super(by);
    }

    public Table(String cssSelector) {
        super(cssSelector);
    }

    // Получить все заголовки колонок из таблицы Контрагентов
    public ElementsCollection getHeadersCA() {
        return element
                .find(By.tagName("thead"))
                .findAll(By.tagName("th"));
    }

    // Получить все заголовки колонок из таблицы
    public ElementsCollection getAllCellsFromHeaders() {
        return element
                .find(By.tagName("thead"))
                .findAll(By.tagName("td"));
    }

    // Получить все строки из таблицы
    public ElementsCollection getAllRaw() {
        return element
                .find(By.tagName("tbody"))
                .findAll(By.tagName("tr"));
    }

    // Получить все ячейки футера из таблицы
    public ElementsCollection getAllCellsFromFooters() {
        return element
                .find(By.tagName("tfoot"))
                .findAll(By.tagName("td"));
    }

}
