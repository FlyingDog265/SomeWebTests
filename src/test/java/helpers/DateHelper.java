package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    public static String getDate(String format, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    // Преобразование формата даты в "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" в "dd.MM.yyyy"
    public static String convertToDate(String dateTimeZone) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date newDate = formatter.parse(dateTimeZone);
        formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(newDate);
    }

}
