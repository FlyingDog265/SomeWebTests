package helpers.contacts.condition;

import helpers.contacts.responsemodel.CityItem;

import java.util.Objects;
import java.util.function.Predicate;

public enum EnumTerminalCondition {
    IS_OFFICE(
            "Является офисом",
            cityItem -> cityItem.getItemService().getIsOffice()),
    IS_RECEIVE_AND_GIVEOUT(
            "Является транспортным терминалом",
            cityItem -> cityItem.getItemService().getIsReceiveGiveout()),
    IS_STORAGE(
            "Оказывается услуга ответхранения",
            cityItem -> cityItem.getItemService().getIsStorage()),
    IS_EXPRESS(
            "Оказывается услуга экспресс-перевозки",
            cityItem -> cityItem.getItemService().getIsExpress()),
    IS_ONLY_GIVEOUT(
            "Только выдача",
            cityItem -> cityItem.getItemService().getIsOnlyGiveout()),
    IS_ONLY_RECEIVE(
            "Только прием",
            cityItem -> cityItem.getItemService().getIsOnlyReceive()),
    NOT_OFFICE(
            "Не является офисом",
            cityItem -> !cityItem.getItemService().getIsOffice()),
    NOT_RECEIVE_AND_GIVEOUT(
            "Не является транспортным терминалом",
            cityItem -> !cityItem.getItemService().getIsReceiveGiveout()),
    NOT_STORAGE(
            "Не оказывается услуга ответхранения",
            cityItem -> !cityItem.getItemService().getIsStorage()),
    NOT_EXPRESS(
            "Не оказывается услуга экспресс-перевозки",
            cityItem -> !cityItem.getItemService().getIsExpress()),
    NOT_ONLY_GIVEOUT(
            "Только выдача - ложь",
            cityItem -> !cityItem.getItemService().getIsOnlyGiveout()),
    NOT_ONLY_RECEIVE(
            "Только прием - ложь",
            cityItem -> !cityItem.getItemService().getIsOnlyReceive()),
    NOT_TEST_TERMINAL(
            "Не является кастомным тестовым терминалом",
            cityItem -> !cityItem.getItemName().contains("Тест")),
    PVZ_TERMINAL(
            "Является терминалом ПВЗ",
            cityItem -> cityItem.getItemTerminal().isOrdersGiveoutPoint()),
    EMPTY_ADDRESS(
            "Содержит пустой адрес",
            cityItem -> Objects.equals(cityItem.getItemAddress(), "")),
    NOT_EMPTY_ADDRESS(
            "Адрес заполнен",
            cityItem -> !String.valueOf(cityItem.getItemAddress()).isEmpty()),
    NOT_EMPTY_COORDINATES(
            "Координаты заполнены",
            cityItem -> !String.valueOf(cityItem.getItemTerminal().getTerminalLongitude()).isEmpty()),
    SMALL_LENGTH(
            "Максимально возможная длина менее 1м",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getLength()), excepted = 1.0;
                    return actual < excepted;
                }
            }),
    LARGE_LENGTH(
            "Максимально возможная длина более 1м",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getLength()), excepted = 1.0;
                    return actual > excepted;
                }
            }),
    NO_LENGTH(
            "Максимально возможная длина равна нулю",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getLength()), excepted = 0.0;
                    return actual == excepted;
                }
            }),
    SMALL_WIDTH(
            "Максимально возможная ширина менее 1м",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getWidth()), excepted = 1.0;
                    return actual < excepted;
                }
            }),
    LARGE_WIDTH(
            "Максимально возможная ширина более 1м",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getWidth()), excepted = 1.0;
                    return actual > excepted;
                }
            }),
    NO_WIDTH(
            "Максимально возможная ширина равна нулю",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getWidth()), excepted = 0.0;
                    return actual == excepted;
                }
            }),
    SMALL_HEIGHT(
            "Максимально возможная высота менее 1м",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getHeight()), excepted = 1.0;
                    return actual < excepted;
                }
            }),
    LARGE_HEIGHT(
            "Максимально возможная высота более 1м",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getHeight()), excepted = 1.0;
                    return actual > excepted;
                }
            }),
    NO_HEIGHT(
            "Максимально возможная высота равна нулю",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getHeight()), excepted = 0.0;
                    return actual == excepted;
                }
            }),
    LIGHT_WEIGHT(
            "Максимально возможный вес менее 80кг",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getWeight()), excepted = 80.0;
                    return actual < excepted;
                }
            }),
    HEAVY_WEIGHT(
            "Максимально возможный вес более 80кг",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getWeight()), excepted = 80.0;
                    return actual > excepted;
                }
            }),
    NO_WEIGHT(
            "Максимально возможный вес равен нулю",
            cityItem -> {
                if (cityItem.getMaxCargoDimensions() == null) {
                    return false;
                } else {
                    double actual = Double.parseDouble(cityItem.getMaxCargoDimensions().getWeight()), excepted = 0.0;
                    return actual == excepted;
                }
            }),
    TIMESHIFT_ABOVE_ZERO(
            "Часовой пояс больше относительно москвского",
            cityItem -> cityItem.getContactCity().getTimeshift() > 0),
    TIMESHIFT_LESS_THAN_ZERO(
            "Часовой пояс меньше относительно москвского",
            cityItem -> cityItem.getContactCity().getTimeshift() < 0),
    TIMESHIFT_EQUAL_ZERO(
            "Часовой пояс равен московскому",
            cityItem -> cityItem.getContactCity().getTimeshift() == 0),
    PHONES_IS_EXIST(
            "У терминала присутсвуют телефоны",
            cityItem -> !cityItem.getShowPhones().isEmpty()),
    PHONES_IS_EMPTY(
            "У терминала отсутствуют телефоны",
            cityItem -> cityItem.getShowPhones().isEmpty()),
    EMAIL_IS_EXIST(
            "У терминала присутствует email",
            cityItem -> !cityItem.getItemMail().isEmpty()),
    WORKTABLES_IS_EMPTY(
            "У терминала отсутствует расписание",
            cityItem -> cityItem.getWorktables().isEmpty());


    EnumTerminalCondition(String description, Predicate<CityItem> predicate) {
        this.predicate = predicate;
        this.description = description;
    }

    private final Predicate<CityItem> predicate;
    private final String description;

    public Predicate<CityItem> getPredicate() {
        return predicate;
    }

    public String getDescription() {
        return description;
    }

}
