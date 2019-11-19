import java.time.ZonedDateTime;

public class ZonedDateTimeTest {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTimeStart = ZonedDateTime.now();
        ZonedDateTime zonedDateTimeEnd = ZonedDateTime.now();
        System.out.println(zonedDateTimeStart.isAfter(zonedDateTimeEnd));
        System.out.println(zonedDateTimeEnd.isAfter(zonedDateTimeStart));
        System.out.println(zonedDateTimeStart.isBefore(zonedDateTimeEnd));
        System.out.println(zonedDateTimeEnd.isBefore(zonedDateTimeStart));
    }
}
