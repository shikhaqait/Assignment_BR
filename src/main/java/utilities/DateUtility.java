package utilities;

import logger.MainLogger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtility {

    public static String getDate(int numberOfFutureDate) {
        LocalDate futureDate = LocalDate.now().plusDays(numberOfFutureDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateStr = futureDate.format(formatter);
        return dateStr;
    }

    public static String getUpdatedDate(String date, int numberOfFutureDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate futureDate = LocalDate.parse(date, formatter).plusDays(numberOfFutureDate);
        MainLogger.logger().info("Date after " + numberOfFutureDate + " will be " + futureDate);
        return futureDate.format(formatter);
    }


}
