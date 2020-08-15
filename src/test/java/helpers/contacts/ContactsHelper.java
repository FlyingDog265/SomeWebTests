package helpers.contacts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import helpers.contacts.condition.EnumTerminalCondition;
import helpers.contacts.responsemodel.CityItem;
import io.restassured.response.Response;
import settings.utils.PropertyLoader;

import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ContactsHelper {

    /**
     * =================================================================================================================
     * Public Functions
     * =================================================================================================================
     */

    public static final String BASE_URL = PropertyLoader.loadProperty("site.url");

    /**
     * метод возвращающий адрес терминала с координатами по принципу отображения его на сайте в карточке терминала
     *
     * @param name терминал для которого необходимо вернуть адрес
     * @return адрес терминала
     */
    public static String getAddressOfTerminal(String name) {
        List<CityItem> cityItemList = new ArrayList<>(getAllCities());
        for (CityItem city : cityItemList) {
            if (city.getItemName().equals(name)) {
                // Дабл приводится к стринг из-за округления числа после точки: могут быть расхождения с сайтом.
                String address = city.getItemAddress(),
                        lat = String.valueOf(city.getItemTerminal().getTerminalLatitude()),
                        lon = String.valueOf(city.getItemTerminal().getTerminalLongitude());
                return MessageFormat.format("{0} (N{1}° E{2}°)", address, lat, lon);
            }
        }
        throw new RuntimeException("Терминал не найден!");
    }

    /**
     * метод возвращающий часовой пояс терминала относительно Москвы
     *
     * @param name терминала для которого необходимо вернуть таймзону
     * @return таймзона терминала в формате int
     */
    public static int getTimezone(String name) {
        List<CityItem> cityItemList = new ArrayList<>(getAllCities());
        for (CityItem city : cityItemList) {
            if (city.getItemName().equals(name)) return city.getContactCity().getTimeshift();
        }
        throw new RuntimeException("Терминал не найден!");
    }

    /**
     * метод возвращающий Email терминала относительно Москвы
     *
     * @param name терминала для которого необходимо вернуть таймзону
     * @return Email терминала в формате String
     */
    public static String getTerminalEmail(String name) {
        List<CityItem> cityItemList = new ArrayList<>(getAllCities());
        for (CityItem city : cityItemList) {
            if (city.getItemName().equals(name)) return city.getItemMail();
        }
        throw new RuntimeException("Терминал не найден!");
    }

    /**
     * метод возвращающий ID терминала
     *
     * @param name терминала для которого необходимо вернуть айдишник
     * @return ID терминала в формате int
     */
    public static int getTerminalID(String name) {
        List<CityItem> cityItemList = new ArrayList<>(getAllCities());
        for (CityItem city : cityItemList) {
            if (city.getItemName().equals(name)) return city.getItemId();
        }
        throw new RuntimeException("Терминал не найден!");
    }

    /**
     * метод возвращающий List c телефонами терминала
     *
     * @param name терминала для которого необходимо вернуть таймзону
     * @return list телефонов для терминала
     */
    public static List<String> getTerminalPhones(String name) {
        List<CityItem> cityItemList = new ArrayList<>(getAllCities());
        for (CityItem city : cityItemList) {
            if (city.getItemName().equals(name)) return city.getShowPhones();
        }
        throw new RuntimeException("Терминал не найден!");
    }

    /**
     * метод возвращающий имя термила в соответсвии с предикатом
     *
     * @param conditions предикат проверки
     * @return наименование терминала, подходящего всем проверкам
     */
    public static String getTerminalWith(EnumTerminalCondition... conditions) {
        List<CityItem> cityItemList = new ArrayList<>(getAllCities());
        for (CityItem city : cityItemList) {
            boolean isMatch = true;
            for (EnumTerminalCondition condition : conditions) {
                isMatch &= condition.getPredicate().test(city);
            }
            if (isMatch) return city.getItemName();
        }
        throw new RuntimeException("Терминал не найден! " + getConditionsDescriptions(conditions));
    }

    /**
     * =================================================================================================================
     * Private Functions
     * =================================================================================================================
     */

    private static Collection<CityItem> getAllCities() {
        Response response = given()
                .when()
                .get(BASE_URL + "api/contacts.json");

        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<CityItem>>() {
        }.getType();
        return gson.fromJson(response.asString(), collectionType);
    }

    private static List<String> getConditionsDescriptions(EnumTerminalCondition[] conditions) {
        List<String> result = new ArrayList<>();
        for (EnumTerminalCondition condition : conditions) {
            result.add(condition.getDescription());
        }
        return result;
    }

}
